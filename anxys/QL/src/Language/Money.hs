{-# LANGUAGE DeriveDataTypeable #-}
{-# LANGUAGE StandaloneDeriving #-}
{-# LANGUAGE TypeSynonymInstances #-}
{-# LANGUAGE FlexibleInstances #-}
module Money (module Money, module Data.Decimal) 
  where

import Data.Typeable
import Data.Data
import Data.Decimal

type Money = Decimal

deriving instance Data Money -- This is necessary for the usage of uniplate
deriving instance Typeable Money
