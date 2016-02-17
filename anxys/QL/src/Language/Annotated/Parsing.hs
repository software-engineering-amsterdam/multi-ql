module Parsing
     where

-- TODO move everything to Parsing

import           AnnotatedAst as A
import           Text.Parsec.Prim                       as S
import           Text.ParserCombinators.Parsec          as P
import           Text.ParserCombinators.Parsec.Expr
import           Text.ParserCombinators.Parsec.Language
import qualified Text.ParserCombinators.Parsec.Token    as Token

languageDef =
            emptyDef { Token.commentStart    = "/*"
                     , Token.commentEnd      = "*/"
                     , Token.commentLine     = "//"
                     , Token.identStart      = letter <|> char '_'
                     , Token.identLetter     = alphaNum
                     , Token.reservedNames   = [ "if"
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
                                               ]
                     , Token.reservedOpNames = ["+"
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
                                               , "<="]
                     , Token.caseSensitive = True
                   }

lexer = Token.makeTokenParser languageDef
identifier = Token.identifier lexer
reserved = Token.reserved   lexer
reservedOp = Token.reservedOp lexer
parens = Token.parens lexer
braces = Token.braces lexer
integer = Token.integer lexer
whiteSpace = Token.whiteSpace lexer
float = Token.float lexer
stringLiteral = Token.stringLiteral lexer

moneyT :: Parser ( FieldType Location )
moneyT = do
      s <- getPosition
      reserved "money"
      e <- getPosition
      return ( Money (newLoc s e) ) <?> "money type"

integerT :: Parser ( FieldType Location )
integerT = do
         s <- getPosition
         reserved "integer"
         e <- getPosition
         return ( Integer (newLoc s e) ) <?> "integer type"

boolT :: Parser ( FieldType Location )
boolT =  do
         s <- getPosition
         reserved "bool"
         e <- getPosition
         return ( Boolean (newLoc s e) ) <?> "bool type"

stringT  :: Parser ( FieldType Location )
stringT = do
        s <- getPosition
        reserved "string"
        e <- getPosition
        return (String  (newLoc s e)) <?> "string type"

fieldT :: Parser (FieldType Location)
fieldT = moneyT <|> integerT <|> stringT <|> boolT <?> "field type"

field :: Parser (Statement Location)
--field = Field <$> (P.try calculatedField <|> simpleField)
field =   do
  start <- getPosition
  a <- P.try calculatedField <|> simpleField
  end <- getPosition
  return  (Field  (newLoc start end) a)

field_ :: Parser (FieldInformation Location)
field_ = do
      text <- stringLiteral
      name <- identifier
      reserved ":"
      t <- fieldT
      return (FieldInformation text name t)

simpleField :: Parser (Field Location)
simpleField = do
      start <- getPosition
      v <- field_
      end <- getPosition
      return (SimpleField (newLoc start end) v) <?> "field"

calculatedField :: Parser (Field Location)
calculatedField = do
      start <- getPosition
      v <- field_
      reserved "="
      e <- expr
      end <- getPosition
      return (CalculatedField (newLoc start end) v e ) <?> "field"

block :: Parser (Block Location)
block = braces (many stmnt) <?> "block"

expr :: Parser (Expression Location)
expr = buildExpressionParser table term <?> "expression"

binop_ :: Location -> (Location -> BinaryOperation Location) -> Expression Location -> Expression Location -> Expression Location
binop_ l op  lhs rhs  = BinaryOperation l (op l) lhs rhs

uop_ :: Location ->  (Location -> UnaryOperation Location) ->  Expression Location -> Expression Location
uop_ l op rhs  = UnaryOperation l (op l) rhs 

table :: [[Operator Char a (Expression Location)]]
table = [ [unary "not" A.Not]
        , [binary ">=" A.GreaterThanOrEquals AssocLeft, binary "<=" A.LesserThanOrEquals AssocLeft, binary ">" A.GreaterThan AssocLeft, binary "<" A.LesserThan AssocLeft]
        , [binary "==" A.Equals AssocLeft, binary "!=" A.NotEquals AssocLeft]
        , [binary "and" And AssocLeft, binary "or" A.Or AssocLeft]
        , [binary "*" Multiplication AssocLeft, binary "/" A.Division AssocLeft ]
        , [binary "+" Addition AssocLeft, binary "-" A.Subtraction AssocLeft ]
        , [binary "++" StringConcatenation AssocLeft]
        ]
  where binary name op = Infix (do
                                  {
                                    s <- getPosition;
                                    reservedOp name;
                                    e <- getPosition;
                                    return (binop_ (newLoc s e) op)
                                  })
        unary name op = Prefix (do
                                  {
                                    s <- getPosition;
                                    reservedOp name;
                                    e <- getPosition;
                                    return (uop_ (newLoc s e) op)
                                  })

var :: Parser (Expression Location)
var = do
    s <- getPosition;
    name <- identifier 
    e <- getPosition;
    return (Variable  (newLoc s e) name) <?> "variable"

term :: Parser (Expression Location)
term = parens expr
     <|> var
     <|> lit
     <?> "term"

lit :: Parser (Expression Location)
lit = do
    s <- getPosition;
    literal <- slit <|> P.try mlit <|> ilit <|> blit
    e <- getPosition;
    return (Literal (newLoc s e) literal)   <?> "literal"


slit :: Parser ( Literal Location )
slit = do
     s <- getPosition
     v <- stringLiteral
     e <- getPosition
     return (StringLiteral (newLoc s e) v) <?> "string literal"

mlit :: Parser ( Literal Location )
mlit = do
     s <- getPosition
     v <- float
     e <- getPosition
     return (MoneyLiteral (newLoc s e) v) <?> "money literal"

ilit :: Parser (Literal Location)
ilit = do
     s <- getPosition
     v <- integer
     e <- getPosition
     return (IntegerLiteral (newLoc s e) v) <?> "integer literal"

blit :: Parser (Literal Location)
blit = do
     s <- getPosition
     v <- true <|> false
     e <- getPosition
     return (BooleanLiteral (newLoc s e) v) <?> "bool literal"
     where
     true = reserved "true" >> return True
     false = reserved "false" >> return False

-- wrapParser :: Parser (a Location) -> Parser a
-- wrapParser p = do
--             start <- getPosition
--             val <- p
--             end <- getPosition
--             return (val Location start end)

stmnt :: Parser (Statement Location)
stmnt = conditional <|> field <?> "statement"

conditional :: Parser (Statement Location)
conditional = P.try ifElseStmnt <|> ifStmnt <?> "conditional"


if_ :: Parser (Expression Location, Block Location)
if_ =  do
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
newLoc = Location 

form :: Parser (Form Location)
form = do
     s <- getPosition
     reserved "form"
     name <- identifier
     body <- block
     e <- getPosition
     return (Form (newLoc s e) name body)

parse :: String -> String -> Either ParseError (Form Location)
parse = P.parse form

-- When values are changed check all variables in the environment to make sure if their value must be recomputed and then rerender the UI
-- E.G:
--lookup :: FiniteMap -> id  -> Maybe Value
-- eval :: Expr -> a
-- eval AExpr



