module Lib
     where

-- TODO move everything to Parsing

import           Ast                                    as A
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
parens = Token.parens     lexer
braces = Token.braces     lexer
integer = Token.integer    lexer
whiteSpace = Token.whiteSpace lexer
float = Token.float lexer
stringLiteral = Token.stringLiteral lexer

moneyT :: Parser FieldType
moneyT = do
      reserved "money"
      return Money <?> "money type"

integerT :: Parser FieldType
integerT = do
         reserved "integer"
         return Integer <?> "integer type"

boolT :: Parser FieldType
boolT =  do
         reserved "bool"
         return Boolean <?> "bool type"

stringT  :: Parser FieldType
stringT = do
        reserved "string"
        return String <?> "string type"

fieldT :: Parser FieldType
fieldT = moneyT <|> integerT <|> stringT <|> boolT <?> "field type"

field :: Parser Stmnt
--field = Field <$> (P.try calculatedField <|> simpleField)
field =   do
  a <- P.try calculatedField <|> simpleField
  return  (Field a)

field_ :: Parser (String, String, FieldType)
field_ = do
      text <- stringLiteral
      name <- identifier
      reserved ":"
      t <-fieldT
      return (text, name, t)

simpleField :: Parser Field
simpleField = do
 (text, name, t) <- field_
 return (SimpleField text name t) <?> "field"

calculatedField :: Parser Field
calculatedField = do
      (text, name, t) <- field_
      reserved "="
      e <- expr
      return (CalculatedField text name t e ) <?> "field"

block :: Parser Block
block = braces (many stmnt) <?> "block"

expr :: Parser Expr
expr = buildExpressionParser table term <?> "expression"

binop_ :: SourcePos -> SourcePos -> (Expr -> Expr -> Expr') -> Expr -> Expr -> Expr
binop_ s e f lhs rhs  = (f lhs rhs, s,e)

uop_ :: SourcePos -> SourcePos -> (Expr -> Expr') -> Expr -> Expr
uop_ s e f rhs  = (f rhs, s,e)

table :: [[Operator Char a Expr]]
table = [ [unary "not" (UnOp A.Neg)]
        , [binary ">=" (BinOp A.GTE) AssocLeft, binary "<=" (BinOp A.LTE) AssocLeft, binary ">" (BinOp A.GT) AssocLeft, binary "<" (BinOp A.LT) AssocLeft]
        , [binary "==" (BinOp A.EQ) AssocLeft, binary "!=" (BinOp A.NEQ) AssocLeft]
        , [binary "and" (BinOp A.And) AssocLeft, binary "or" (BinOp A.Or) AssocLeft]
        , [binary "*" (BinOp A.Mul) AssocLeft, binary "/" (BinOp A.Div) AssocLeft ]
        , [binary "+" (BinOp A.Add) AssocLeft, binary "-" (BinOp A.Sub) AssocLeft ]
        , [binary "++" (BinOp A.SConcat) AssocLeft]
        ]
  where binary name f = Infix (do
                                  {
                                    s <- getPosition;
                                    reservedOp name;
                                    e <- getPosition;
                                    return (binop_ s e f)
                                  })
        unary name f = Prefix (do
                                  {
                                    s <- getPosition;
                                    reservedOp name;
                                    e <- getPosition;
                                    return (uop_ s e f)
                                  })

var :: Parser Expr
var = do
    s <- getPosition;
    name <- identifier 
    e <- getPosition;
    return (Var name, s, e) <?> "variable"

term :: Parser Expr
term = parens expr
     <|> var 
     <|> lit
     <?> "term"

lit :: Parser Expr
lit = do
    s <- getPosition;
    literal <- slit <|> P.try mlit <|> ilit <|> blit
    e <- getPosition;
    return (Lit literal,s,e)   <?> "literal"


slit :: Parser Lit
slit = SLit <$> stringLiteral <?> "string literal"

mlit :: Parser Lit
mlit = MLit <$> float <?> "money literal"

ilit :: Parser Lit
ilit = ILit <$> integer <?> "integer literal"

blit :: Parser Lit
blit = BLit <$>  (true <|> false) <?> "bool literal"
     where
     true = reserved "true" >> return True
     false = reserved "false" >> return False

stmnt :: Parser Stmnt
stmnt = conditional <|> field <?> "statement"

conditional :: Parser Stmnt
conditional = P.try ifElseStmnt <|> ifStmnt <?> "conditional"

if_ :: Parser (Expr, Block)
if_ =  do
        reserved "if"
        cond <- parens expr
        body <- block
        return (cond, body)

ifStmnt :: Parser Stmnt
ifStmnt = do
        (cond, body) <- if_
        return (If cond body) <?> "if statement"

ifElseStmnt :: Parser Stmnt
ifElseStmnt = do
        (cond, firstBody) <- if_
        reserved "else"
        secondBody <- block
        return (IfElse cond firstBody secondBody) <?> "if else statement"

form :: Parser Form
form = do
     reserved "form"
     name <- identifier
     body <- block
     return (Form name body)

parse :: String -> String -> Either ParseError Form
parse = P.parse form

-- When values are changed check all variables in the environment to make sure if their value must be recomputed and then rerender the UI
-- E.G:
--lookup :: FiniteMap -> id  -> Maybe Value
-- eval :: Expr -> a
-- eval AExpr



