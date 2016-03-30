module Value (module Value, module ValueTypes )
       where

import           Ast
import           ParseValue
import           ValueTypes

haveSameValueType :: Value -> Value -> Bool
haveSameValueType x y = toConstr x == toConstr y

andValues :: [Value] -> Bool
andValues vs = and (map toBoolValue vs)
  where toBoolValue (BoolValue x) = x
        toBoolValue _ = False 

defaultVal :: FieldType -> Value

defaultVal Integer = IntValue 0
defaultVal Boolean = BoolValue False
defaultVal String = StringValue ""
defaultVal Money = mkMoney 0

toDisplay :: Value -> String
toDisplay (IntValue x) = show x
toDisplay (StringValue x) = x
toDisplay (MoneyValue x) = show x
toDisplay (BoolValue x) = show x 
toDisplay Undefined = "Undefined"

fromDisplay :: FieldType -> String -> Value
fromDisplay String = parseStringValue 
fromDisplay _  = parseValue 

