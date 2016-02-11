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
  loc <- getPosition
  a <- P.try calculatedField <|> simpleField
  return ( Field a, loc)

simpleField :: Parser Field
simpleField = do
      text <- stringLiteral
      name <- identifier
      reserved ":"
      t <-fieldT
      return (SimpleField text name t) <?> "field"

calculatedField :: Parser Field
calculatedField = do
      text <- stringLiteral
      name <- identifier
      reserved ":"
      t <-fieldT
      reserved "="
      e <- expr
      return (CalculatedField text name t e ) <?> "field"

block :: Parser Block
block = braces (many stmnt) <?> "block"

expr :: Parser Expr
expr = buildExpressionParser table term <?> "expression"

table :: [[Operator Char a Expr]]
table = [ [unary "not" (UnOp Neg)]
        , [binary "and" (BinOp And) AssocLeft, binary "or" (BinOp Or) AssocLeft]
        , [binary "==" (BinOp A.EQ) AssocLeft, binary "!=" (BinOp A.NEQ) AssocLeft]
        , [binary ">" (BinOp A.GT) AssocLeft, binary "<" (BinOp A.LT) AssocLeft]
        , [binary ">=" (BinOp A.GTE) AssocLeft, binary "<=" (BinOp A.LTE) AssocLeft]
        , [binary "*" (BinOp Mul) AssocLeft, binary "/" (BinOp Div) AssocLeft ]
        , [binary "+" (BinOp Add) AssocLeft, binary "-" (BinOp Sub) AssocLeft ]
        , [binary "++" (BinOp SConcat) AssocLeft]
        ]
  where binary name f = Infix ( reservedOp name >> return f)
        unary name f = Prefix ( reservedOp name >> return f)

term :: Parser Expr
term = parens expr
       <|> fmap Var identifier
       <|> lit
       <?> "term"

lit :: Parser Expr
lit = Lit <$> (slit <|> P.try mlit <|> ilit <|> blit) <?> "literal"

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

ifStmnt :: Parser Stmnt
ifStmnt = do
        loc <- getPosition
        reserved "if"
        cond <- parens expr
        body <- block
        return ((If cond body), loc) <?> "if statement"

ifElseStmnt :: Parser Stmnt
ifElseStmnt = do
        loc <- getPosition
        reserved "if"
        cond <- parens expr
        firstBody <- block
        reserved "else"
        secondBody <- block
        return ((IfElse cond firstBody secondBody), loc) <?> "if else statement"

form :: Parser Form
form = do
     reserved "form"
     name <- identifier
     body <- block
     return (Form name body)

parse :: String
parse = undefined

-- When values are changed check all variables in the environment to make sure if their value must be recomputed and then rerender the UI
-- E.G:
--lookup :: FiniteMap -> id  -> Maybe Value
-- eval :: Expr -> a
-- eval AExpr

run :: String -> String
run input = case P.parse form "ql" input of
          Left err ->  "Error: " ++ show err
          Right val -> show val



