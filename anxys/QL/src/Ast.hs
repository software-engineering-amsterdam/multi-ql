module Ast
       where

-- TODO: Origin tracking (How do I test this?)
import           Text.ParserCombinators.Parsec.Pos

type Money = Double --Datatype for money E.G. 10.50

type Name = String

type Value = String

type Block = [Stmnt]

data Lit
     = ILit Integer
     | MLit Money
     | SLit String
     | BLit Bool
       deriving (Eq, Show)

data FieldType
     = Money
     | Integer
     | String
     | Boolean
       deriving (Eq, Enum, Show)

--type Expr = (Expr', SourcePos)

data Expr
     = Var Name
     | Lit Lit
     | BinOp BinOp Expr Expr
     | UnOp UnOp Expr
       deriving (Eq, Show)

data UnOp = Neg deriving (Eq, Show)

data BinOp
     = Add
     | Sub
     | Div
     | Mul
     | And
     | Or
     | Not
     | SConcat
     | EQ
     | NEQ
     | GT
     | GTE
     | LT
     | LTE
       deriving (Eq, Show)

type Stmnt = (Stmnt', SourcePos)

data Stmnt'
     = Field Field
     | If Expr Block
     | IfElse Expr Block Block
       deriving (Eq, Show)


data Field
     = SimpleField { label     :: String
                   , id        :: String
                   , fieldType :: FieldType
                   }
     | CalculatedField { label     :: String
                       , id        :: String
                       , fieldType :: FieldType
                       , exp       :: Expr
                       }
       deriving (Eq, Show)

-- TODO: Solve above duplication with this
--data FieldInformation = FieldInformation { label     :: String
--                   , id        :: String
--                   , fieldType :: FieldType
--                   }

data Form = Form String Block deriving Show
