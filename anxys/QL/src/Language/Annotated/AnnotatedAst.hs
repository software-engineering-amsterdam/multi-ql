module AnnotatedAst where

import Data.Decimal
import Prelude hiding (id)
import Identifier
--Todo use special datatype for this. Or at the very least don't do arithmethic with it
type Money = Decimal


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


collectFields :: Form a -> [Field a]
collectFields (Form _ _ stmnts) =
  getFieldStmnts stmnts
  where
    getFieldStmnts =
      concatMap getFieldStmnts'
    getFieldStmnts' (If _ _ ifblock) =
      getFieldStmnts ifblock
    getFieldStmnts' (IfElse _ _ ifblock elseblock) =
      getFieldStmnts ifblock ++ getFieldStmnts elseblock
    getFieldStmnts' (Field _ field) =
      [field]

getIdentifier :: Field a -> Identifier
getIdentifier (CalculatedField _ info _) = id info
getIdentifier (SimpleField _ info) = id info

collectFieldInformation :: Form a -> [FieldInformation a]
collectFieldInformation (Form _ _ stmts) =
  getFieldInfo stmts
  where
    getFieldInfo = concatMap getFieldInfo'
    getFieldInfo' st =
      case st of
        Field _ (SimpleField _ f) ->
          [f]
        Field _ (CalculatedField _ f _) ->
          [f]
        If _ _ ss ->
          getFieldInfo ss
        IfElse _ _ xs ys ->
          getFieldInfo xs ++ getFieldInfo ys

extractAnnotationFromField :: Field a -> a
extractAnnotationFromField (CalculatedField anno _ _) = anno
extractAnnotationFromField (SimpleField anno _) = anno

collectCalculatedFields :: Form a -> [Field a]
collectCalculatedFields form = filter isCalc (collectFields form)
  where
    isCalc x =
      case x of
        CalculatedField{} -> True
        SimpleField{}     -> False
