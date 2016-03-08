module Ast where

import Identifier

type Money = Double

type Block = [Stmnt]

data Lit
  = ILit Integer
  | MLit Money
  | SLit String
  | BLit Bool
  deriving (Eq,Show)

data FieldType
  = Money
  | Integer
  | String
  | Boolean
  deriving (Eq,Show)

data Expr
  = Var Identifier
  | Lit Lit
  | BinOp BinOp
          Expr
          Expr
  | UnOp UnOp
         Expr
  deriving (Eq,Show)

data UnOp =
  Not
  deriving (Eq,Show)

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
  deriving (Eq,Show)

data Stmnt
  = Field Field
  | If Expr
       Block
  deriving (Eq,Show)

data Field
  = SimpField FieldInfo
  | CalcField FieldInfo
              Expr
  deriving (Eq,Show)

data FieldInfo =
  FieldInfo { label :: String
            , id :: Identifier
            , fieldType :: FieldType
            }
  deriving (Eq,Show)

data Form =
  Form Identifier
       Block
  deriving (Eq, Show)
