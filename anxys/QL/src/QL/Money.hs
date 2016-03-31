{-# LANGUAGE DeriveDataTypeable   #-}
{-# LANGUAGE FlexibleInstances    #-}
{-# LANGUAGE StandaloneDeriving   #-}
{-# LANGUAGE TypeSynonymInstances #-}
module QL.Money (module QL.Money, module Data.Decimal)
  where

import           Data.Data
import           Data.Decimal
import           Data.Typeable

type Money = Decimal

deriving instance Data Money -- This is necessary for the usage of uniplate
deriving instance Typeable Money
