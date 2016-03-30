{-# LANGUAGE DeriveDataTypeable #-}
{-# LANGUAGE StandaloneDeriving #-}
{-# LANGUAGE TypeSynonymInstances #-}
{-# LANGUAGE FlexibleInstances #-}

module Ast where
import Identifier
import Data.Generics.Uniplate.Data
import Money
import Data.Typeable
import Data.Data

type Block = [Stmnt]

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

fieldConditionalDependencies :: Form -> [(Field, [Expr])]
fieldConditionalDependencies (Form _ stmnts) =
  getFieldStmnts stmnts []
  where
    getFieldStmnts stmnts deps =
      concatMap ( flip getFieldStmnts' deps) stmnts
    getFieldStmnts' (If expr ifblock) deps =
      getFieldStmnts ifblock (expr:deps)
    getFieldStmnts' (Field field) deps =
      [(field, deps)]
