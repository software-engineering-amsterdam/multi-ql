module AnnotatedAst where

import Text.ParserCombinators.Parsec.Pos (SourcePos)


type Money = Double --Datatype for money E.G. 10.50

type Name = String

type Value = String

type Block a = [Statement a]

data Location = Location { start :: SourcePos
                         , end :: SourcePos
                         } deriving (Eq,Show)
data FieldType a
  = Money a
  | Integer a
  | String a
  | Boolean a
  deriving (Eq,Show)

data Literal a
  = IntegerLiteral a
                   Integer
  | MoneyLiteral a
                 Money
  | StringLiteral a
                  String
  | BooleanLiteral a
                   Bool
  deriving (Eq,Show)

data Expression a
  = Variable a
            Name
  | Literal a
            (Literal a)
  | BinaryOperation a
                    (BinaryOperation a)
                    (Expression a)
                    (Expression a)
  | UnaryOperation a
                   (UnaryOperation a)
                   (Expression a)
  deriving (Eq, Show)

data UnaryOperation a = Not a deriving (Eq, Show)

data BinaryOperation a
     = Addition a
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

data Statement a
     = Field a (Field a)
     | If a (Expression a) (Block a)
     | IfElse a (Expression a) (Block a) (Block a)
       deriving (Eq, Show)

data Field a
     = SimpleField a (FieldInformation a)
     | CalculatedField a (FieldInformation a) (Expression a)
       deriving (Eq, Show)

data FieldInformation a
     = FieldInformation { label     :: String
                        , id        :: String
                        , fieldType :: FieldType a
                        }
       deriving (Eq, Show)

data Form a =
  Form a String (Block a) deriving Show
