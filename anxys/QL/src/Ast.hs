module Ast
       where

type Money = Float -- Todo datatype for money E.G. 10.50

type Name = String

type Value = String

type Block = [Stmnt] 

data Lit
     = ILit Int
     | MLit Money
     | SLit String
     | BLit Bool
       deriving Show

data FieldType
     = Money
     | Integer
     | String
     | Boolean
       deriving Show

data Expr
     = Var Name
     | Lit Lit
     | Op PrimOp [Expr]
       deriving Show

data PrimOp
     = Add
     | Sub
     | Div
     | Mul
     | And
     | Or
     | Not
     deriving Show

data Stmnt
     = Field { label :: String,
               id :: String,
               fieldType :: FieldType,
               value :: String }
     | If Expr Block
       deriving Show

data Form = Form String Block deriving Show
