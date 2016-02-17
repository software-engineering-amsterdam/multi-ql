{-# LANGUAGE DeriveDataTypeable #-}

module Ast where
import Text.ParserCombinators.Parsec.Pos

import Data.Data

type Money = Double --Datatype for money E.G. 10.50

type Name = String

type Block = [Stmnt]

data Lit
  = ILit Integer
  | MLit Money
  | SLit String
  | BLit Bool
  deriving (Eq,Show, Typeable, Data)

data FieldType
  = Money
  | Integer
  | String
  | Boolean
  deriving (Eq,Enum,Show)

data Region =
  Region {start :: SourcePos
         ,end :: SourcePos}
  deriving (Eq,Show)

data Expr
  = Var Name
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
  | NEQ
  | GT
  | GTE
  | LT
  | LTE
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
  FieldInfo {label :: String
            ,id :: String
            ,fieldType :: FieldType}
  deriving (Eq,Show)

data Form =
  Form String
       Block
  deriving (Eq, Show)
