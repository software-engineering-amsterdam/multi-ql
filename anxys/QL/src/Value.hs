module Value
       where

import           Ast

data Value = IntValue Integer
           | BoolValue Bool
           | StringValue String
           | MoneyValue Double
  deriving (Eq, Show)

defaultVal :: FieldType -> Value
defaultVal Integer = IntValue 0
defaultVal Boolean = BoolValue False
defaultVal String = StringValue ""
defaultVal Money = MoneyValue 0.0
