{-# LANGUAGE DeriveDataTypeable #-}
module QL.Value.ValueTypes (module QL.Value.ValueTypes, toConstr)
  where

import           Data.Data
import           Data.Typeable
import           QL.Money

data Value = IntValue Integer
           | BoolValue Bool
           | StringValue String
           | MoneyValue Money
           | Undefined
  deriving (Eq, Show, Data, Typeable)

mkMoney :: Integer -> Value
mkMoney x = MoneyValue (Decimal 2 x)
