{-# LANGUAGE DeriveDataTypeable #-}
module ValueTypes (module ValueTypes, toConstr)
  where

import           Data.Typeable
import           Data.Data
import Money

data Value = IntValue Integer
           | BoolValue Bool
           | StringValue String
           | MoneyValue Money
           | Undefined
  deriving (Eq, Show, Data, Typeable)

mkMoney :: Integer -> Value
mkMoney x = MoneyValue (Decimal 2 x)
