module Lib
    ( someFunc
      , run
    ) where

-- TODO move everyting to Parsing

import Text.ParserCombinators.Parsec as P
import Text.ParserCombinators.Parsec.Language
import qualified Text.ParserCombinators.Parsec.Token as Token
import Text.Parsec.Expr
import Ast

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
                     , Token.reservedOpNames = ["+", "-", "*", "/", "and", "or", "not", ":"]
                     , Token.caseSensitive = True
                   }

lexer = Token.makeTokenParser languageDef

identifier = Token.identifier lexer
reserved   = Token.reserved   lexer
reservedOp = Token.reservedOp lexer
parens     = Token.parens     lexer
braces     = Token.braces     lexer
stringLiteral = Token.stringLiteral lexer
integer    = Token.integer    lexer
whiteSpace = Token.whiteSpace lexer

stringL :: Parser String
stringL = do
         char '"'
         v <- many anyChar 
         char '"'
         return v

money :: Parser FieldType
money = do
      reserved "money"
      return Money

integerT :: Parser FieldType
integerT = do
         reserved "integer"
         return Integer

stringT  :: Parser FieldType
stringT = do
        reserved "string"   
        return String

fieldT :: Parser FieldType
fieldT = money <|> integerT <|> stringT

field :: Parser Stmnt
field = do
      label <- stringLiteral
      name <- identifier
      reserved ":"
      t <-fieldT
      return (Field label name t "None")

defaultField :: Parser Stmnt
defaultField = return (Field "DisplayText" "IdTest" Money "ValuesTest")

block :: Parser Block
block =  do
      body  <- braces $ many stmnt
      return body

expr :: Parser Expr  --Todo
expr = do
     v <- lit
     return v

lit :: Parser Expr
lit = do
    r <- slit <|> ilit <|> mlit <|> blit
    return (Lit r)

slit :: Parser Lit
slit = do
     v <- stringLiteral
     return (SLit v)

mlit :: Parser Lit -- TODO
mlit = do
     val <- many1 digit
     return (ILit $ read val)

ilit :: Parser Lit
ilit = do
     val <- many1 digit
     return (ILit $ read val)

blit :: Parser Lit
blit = do
     r <- (true <|> false)
     return (BLit  r )

true :: Parser Bool
true = do
     reserved "true"
     return True 

false :: Parser Bool
false = do 
      reserved "false"
      return False

stmnt :: Parser Stmnt
stmnt = do
      r <- ifStmnt <|> field
      return r

ifStmnt :: Parser Stmnt
ifStmnt = do
        reserved "if"
        cond <- parens expr
        body <- block
        return (If cond body)

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

someFunc :: String ->  String
someFunc  = run

