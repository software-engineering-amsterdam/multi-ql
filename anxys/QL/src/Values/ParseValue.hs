module ParseValue where

import ValueTypes
import           Money 
import           Text.ParserCombinators.Parsec          as P
import           Text.ParserCombinators.Parsec.Language
import qualified Text.ParserCombinators.Parsec.Token    as Token

intValue :: Parser Value
intValue = do
  val <- integer
  return (IntValue val) <?> "int val"

moneyValue :: Parser Value
moneyValue = do
  val <- dec
  return (MoneyValue val) <?> "money val"

stringValue :: Parser Value
stringValue = do
  val <- ParseValue.string
  return (StringValue val) <?> "string val"

value :: Parser Value
value = P.try moneyValue <|> intValue  <?> "value"

parse' :: Parser Value -> String -> Value 
parse' p input = case P.parse p "user input" input of
  Right val -> val
  Left  _ -> Undefined

parseValue :: String -> Value
parseValue = parse' value  

parseStringValue :: String -> Value
parseStringValue = parse' stringValue 

languageDef :: LanguageDef st
languageDef = emptyDef

lexer :: Token.TokenParser st
lexer = Token.makeTokenParser languageDef

integer :: Parser Integer
integer = Token.integer lexer

float :: Parser Double
float = Token.float lexer

string :: Parser String
string = many anyChar <?> "string"

dec :: Parser Money 
dec = do
  val <- float
  return (realFracToDecimal (decimalMantissa 2) val) <?> "decimal"
