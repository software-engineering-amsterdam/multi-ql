{-# LANGUAGE DeriveDataTypeable #-}
module Value
       where

import           Ast
import           Data.Decimal
import           Text.ParserCombinators.Parsec          as P
import           Text.ParserCombinators.Parsec.Language
import qualified Text.ParserCombinators.Parsec.Token    as Token
import Data.Typeable
import Data.Data

data Value = IntValue Integer
           | BoolValue Bool
           | StringValue String
           | MoneyValue Decimal
           | Undefined
  deriving (Eq, Show, Data, Typeable)

haveSameValueType :: Value -> Value -> Bool
haveSameValueType x y = toConstr x == toConstr y

defaultVal :: FieldType -> Value
defaultVal Integer = IntValue 0
defaultVal Boolean = BoolValue False
defaultVal String = StringValue ""
defaultVal Money = MoneyValue (Decimal 2 0)

toDisplay :: Value -> String
toDisplay (IntValue x) = show x
toDisplay (StringValue x) = x
toDisplay (MoneyValue x) = show x
toDisplay (BoolValue x) = show x 
toDisplay Undefined = "Undefined"

fromDisplay :: String -> Value
fromDisplay = parseValue 

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
    val <- Value.string
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
string = do
      val <- many anyChar
      return val

dec :: Parser Decimal
dec = do
       val <- float
       return (realFracToDecimal (decimalMantissa 2) val) <?> "decimal"
