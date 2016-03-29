{-# LANGUAGE DeriveDataTypeable #-}
{-# LANGUAGE StandaloneDeriving #-}
{-# LANGUAGE TypeSynonymInstances #-}
{-# LANGUAGE FlexibleInstances #-}

module Ast where
import Identifier
import Data.Generics.Uniplate.Data
import Data.Maybe
import Data.Decimal
import Data.Typeable
import Data.Data

type Money = Decimal

type Block = [Stmnt]

deriving instance Data Money 
deriving instance Typeable Money

data Lit
  = ILit Integer
  | MLit Money
  | SLit String
  | BLit Bool
  deriving (Eq,Show,Typeable,Data)

data FieldType
  = Money
  | Integer
  | String
  | Boolean
  deriving (Eq,Show,Typeable,Data)

data Expr
  = Var Identifier
  | Lit Lit
  | BinOp BinOp
          Expr
          Expr
  | UnOp UnOp
         Expr
  deriving (Eq,Show,Typeable,Data)

data UnOp =
  Not
  deriving (Eq,Show,Typeable,Data)

data BinOp
  = Add
  | Sub
  | Div
  | Mul
  | And
  | Or
  | SConcat
  | EQ
  | GT
  | GTE
  deriving (Eq,Show,Typeable,Data)

data Stmnt
  = Field Field
  | If Expr
       Block
  deriving (Eq,Show,Typeable,Data)

data Field
  = SimpField FieldInfo
  | CalcField FieldInfo
              Expr
  deriving (Eq,Show,Typeable,Data)

data FieldInfo =
  FieldInfo { label :: String
            , id :: Identifier
            , fieldType :: FieldType
            }
  deriving (Eq,Show,Typeable,Data)

data Form =
  Form Identifier
       Block
  deriving (Eq,Show,Typeable,Data)

fields :: Form -> [Field]
fields (Form _ block) = concatMap fields' block
  where fields' x = [y | Field y <- universe x]
