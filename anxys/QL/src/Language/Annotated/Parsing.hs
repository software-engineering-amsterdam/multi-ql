module Parsing (
    Parsing.parse,
    form,
    expr,
    ifStmnt,
    ifElseStmnt,
    conditional,
    simpleField,
    calculatedField,
    ParseError
    ) where

import           AnnotatedAst                           as A
import           Location
import           Text.Parsec.Prim                       as S
import           Text.ParserCombinators.Parsec          as P
import           Text.ParserCombinators.Parsec.Expr
import           Text.ParserCombinators.Parsec.Language
import qualified Text.ParserCombinators.Parsec.Token    as Token

languageDef =
  emptyDef
    { Token.commentStart = "/*"
    , Token.commentEnd = "*/"
    , Token.commentLine = "//"
    , Token.identStart = letter <|> char '_'
    , Token.identLetter = alphaNum
    , Token.reservedNames = [ "if"
                            , "else"
                            , "form"
                            , "true"
                            , "false"
                            , "not"
                            , "and"
                            , "or"
                            , "money"
                            , "integer"
                            , "string"
                            , "bool"
                            ]
    , Token.reservedOpNames = [ "+"
                              , "-"
                              , "*"
                              , "/"
                              , "and"
                              , "or"
                              , "not"
                              , ":"
                              , "++"
                              , ">"
                              , "<"
                              , "=="
                              , "!="
                              , ">="
                              , "<="
                              ]
    , Token.caseSensitive = True
    }

lexer = Token.makeTokenParser languageDef

identifier :: Parser String
identifier = Token.identifier lexer

reserved = Token.reserved lexer

reservedOp = Token.reservedOp lexer

parens = Token.parens lexer

braces = Token.braces lexer

integer :: Parser Integer
integer = Token.integer lexer

whiteSpace = Token.whiteSpace lexer

float :: Parser Double
float = Token.float lexer

stringLiteral :: Parser String
stringLiteral = Token.stringLiteral lexer

moneyT :: Parser (FieldType Location)
moneyT = addLoc0 Money (reserved "money") <?> "money type"

integerT :: Parser (FieldType Location)
integerT = addLoc0 Integer (reserved "integer") <?> "integer type"

boolT :: Parser (FieldType Location)
boolT = addLoc0  Boolean (reserved "bool") <?> "bool type"

stringT :: Parser (FieldType Location)
stringT = addLoc0  String (reserved "string") <?> "string type"

fieldT :: Parser (FieldType Location)
fieldT = moneyT <|> integerT <|> stringT <|> boolT <?> "field type"

addLoc0 :: (Location -> b Location) -> Parser () -> Parser (b Location)
addLoc0 f p = do
  s <- getPosition
  p
  e <- getPosition
  return (f (newLoc s e) )

addLoc :: (Location -> a -> b Location) -> Parser a -> Parser (b Location)
addLoc f p = do
  s <- getPosition
  v <- p
  e <- getPosition
  return (f (newLoc s e) v)

addLoc2 :: (Location -> a -> c -> b Location) -> Parser a  -> Parser c-> Parser (b Location)
addLoc2 f p1 p2 = do
  s <- getPosition
  v <- p1
  c <- p2
  e <- getPosition
  return (f (newLoc s e) v c)

field :: Parser (Statement Location)
field = addLoc Field (P.try calculatedField <|> simpleField)

fieldInfo :: Parser (FieldInformation Location)
fieldInfo = do
  text <- stringLiteral
  name <- identifier
  reserved ":"
  t <- fieldT
  return (FieldInformation text name t)

simpleField :: Parser (Field Location)
simpleField = addLoc SimpleField fieldInfo <?> "simple field"

calculatedField :: Parser (Field Location)
calculatedField = addLoc2 CalculatedField  (fieldInfo <* reserved "=") expr <?> "calculated field"

block :: Parser (Block Location)
block = braces (many stmnt) <?> "block"

expr :: Parser (Expression Location)
expr = buildExpressionParser opTable term <?> "expression"

binop_ :: Location -> (Location -> BinaryOperation Location) -> Expression Location -> Expression Location -> Expression Location
binop_ l op = BinaryOperation l (op l)

uop_ :: Location -> (Location -> UnaryOperation Location) -> Expression Location -> Expression Location
uop_ l op = UnaryOperation l (op l)

opTable :: [[Operator Char a (Expression Location)]]
opTable = [
          [ unary "not" A.Not ]
        , [ binary ">=" A.GreaterThanOrEquals AssocLeft
          , binary "<=" A.LesserThanOrEquals AssocLeft
          , binary ">" A.GreaterThan AssocLeft
          , binary "<" A.LesserThan AssocLeft
          ]
        , [ binary "==" A.Equals AssocLeft
          , binary "!=" A.NotEquals AssocLeft
          ]
        , [ binary "and" And AssocLeft
          , binary "or" A.Or AssocLeft
          ]
        , [ binary "*" Multiplication AssocLeft
          , binary "/" A.Division AssocLeft
          ]
        , [ binary "+" Addition AssocLeft
          , binary "-" A.Subtraction AssocLeft
          ]
        , [ binary "++" StringConcatenation AssocLeft ]
        ]
  where
    binary name op = Infix
                       ( do
                         s <- getPosition
                         reservedOp name
                         e <- getPosition
                         return (binop_ (newLoc s e) op)
                       )
    unary name op = Prefix
                      ( do
                        s <- getPosition
                        reservedOp name
                        e <- getPosition
                        return (uop_ (newLoc s e) op)
                      )

--- Dis breaks everything
lOp :: String -> Parser (SourcePos, SourcePos)
lOp name = do
            s <- getPosition
            reservedOp name
            e <- getPosition
            return (s,e)

var :: Parser (Expression Location)
var = addLoc Variable identifier <?> "variable"

term :: Parser (Expression Location)
term = parens expr
       <|> var
       <|> lit
       <?> "term"

lit :: Parser (Expression Location)
lit = addLoc Literal (slit <|> P.try mlit <|> ilit <|> blit) <?> "literal"

slit :: Parser (Literal Location)
slit = addLoc StringLiteral stringLiteral <?> "string literal"

mlit :: Parser (Literal Location)
mlit = addLoc MoneyLiteral  float <?> "money literal"

ilit :: Parser (Literal Location)
ilit = addLoc IntegerLiteral integer <?> "integer literal"

blit :: Parser (Literal Location)
blit = addLoc BooleanLiteral (true <|> false) <?> "bool literal"
  where
    true = reserved "true" >> return True
    false = reserved "false" >> return False

stmnt :: Parser (Statement Location)
stmnt = conditional <|> field <?> "statement"

conditional :: Parser (Statement Location)
conditional = P.try ifElseStmnt <|> ifStmnt <?> "conditional"

if_ :: Parser (Expression Location, Block Location)
if_ = do
  reserved "if"
  cond <- parens expr
  body <- block
  return (cond, body)

ifStmnt :: Parser (Statement Location)
ifStmnt = do
  s <- getPosition
  (cond, body) <- if_
  e <- getPosition
  return (If (newLoc s e) cond body) <?> "if statement"

ifElseStmnt :: Parser (Statement Location)
ifElseStmnt = do
  s <- getPosition
  (cond, firstBody) <- if_
  reserved "else"
  secondBody <- block
  e <- getPosition
  return (IfElse (newLoc s e) cond firstBody secondBody) <?> "if else statement"

newLoc :: SourcePos -> SourcePos -> Location
newLoc s e = Location (newPos s) (newPos e)

newPos :: SourcePos -> Position
newPos s = Position (sourceLine s)  (sourceColumn s)

form :: Parser (Form Location)
form = addLoc2 Form (reserved "form" *> identifier) block <?> "form"

parse :: String -> String -> Either ParseError (Form Location)
parse = P.parse form
