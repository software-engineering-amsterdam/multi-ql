module AnnotatedAst where

import Identifier
--Todo use special datatype for this. Or at the very least don't do arithmethic with it
type Money = Double


type Block a = [Statement a]

data FieldType a = Money a
                 | Integer a
                 | String a
                 | Boolean a
  deriving (Eq, Show)

data Literal a = IntegerLiteral a Integer
               | MoneyLiteral a Money
               | StringLiteral a String
               | BooleanLiteral a Bool
  deriving (Eq, Show)

data Expression a = Variable a Identifier
                  | Literal a (Literal a)
                  | BinaryOperation a (BinaryOperation a) (Expression a) (Expression a)
                  | UnaryOperation a (UnaryOperation a) (Expression a)
  deriving (Eq, Show)

data UnaryOperation a = Not a
  deriving (Eq, Show)

data BinaryOperation a = Addition a
                       | Subtraction a
                       | Division a
                       | Multiplication a
                       | And a
                       | Or a
                       | StringConcatenation a
                       | Equals a
                       | NotEquals a
                       | GreaterThan a
                       | GreaterThanOrEquals a
                       | LesserThan a
                       | LesserThanOrEquals a
  deriving (Eq, Show)

data Statement a = Field a (Field a)
                 | If a (Expression a) (Block a)
                 | IfElse a (Expression a) (Block a) (Block a)
  deriving (Eq, Show)

data Field a = SimpleField a (FieldInformation a)
             | CalculatedField a (FieldInformation a) (Expression a)
  deriving (Eq, Show)

data FieldInformation a =
       FieldInformation
         { label :: String
         , id :: Identifier
         , fieldType :: FieldType a
         }
  deriving (Eq, Show)

data Form a = Form a String (Block a)
  deriving Show
