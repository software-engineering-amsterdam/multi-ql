{-# LANGUAGE DeriveDataTypeable #-}
{-# LANGUAGE StandaloneDeriving #-}
{-# LANGUAGE TypeSynonymInstances #-}
{-# LANGUAGE FlexibleInstances #-}
module AnnotatedAst where

import Data.Decimal
import Data.Generics.Uniplate.Data
import Prelude hiding (id)
import Data.Typeable
import Data.Data
import Money
import Identifier

type Block a = [Statement a]

data FieldType a = Money a
                 | Integer a
                 | String a
                 | Boolean a
  deriving (Eq, Show, Typeable, Data)

data Literal a = IntegerLiteral a Integer
               | MoneyLiteral a Money
               | StringLiteral a String
               | BooleanLiteral a Bool
  deriving (Eq, Show, Typeable, Data)

data Expression a = Variable a Identifier
                  | Literal a (Literal a)
                  | BinaryOperation a (BinaryOperation a) (Expression a) (Expression a)
                  | UnaryOperation a (UnaryOperation a) (Expression a)
  deriving (Eq, Show, Typeable, Data)

data UnaryOperation a = Not a
  deriving (Eq, Show, Typeable, Data)

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
  deriving (Eq, Show, Typeable, Data)

data Statement a = Field a (Field a)
                 | If a (Expression a) (Block a)
                 | IfElse a (Expression a) (Block a) (Block a)
  deriving (Eq, Show, Typeable, Data)

data Field a = SimpleField a (FieldInformation a)
             | CalculatedField a (FieldInformation a) (Expression a)
  deriving (Eq, Show, Typeable, Data)

data FieldInformation a =
       FieldInformation
         { label :: String
         , id :: Identifier
         , fieldType :: FieldType a
         }
  deriving (Eq, Show, Typeable, Data)

data Form a = Form a String (Block a)
  deriving (Eq, Show, Typeable, Data)


collectFields :: Data a => Form a -> [Field a]
collectFields (Form _ _ stmnts) = concatMap fields' stmnts
  where fields' x = [y | Field _  y <- universe x]

getIdentifier :: Field a -> Identifier
getIdentifier (CalculatedField _ info _) = id info
getIdentifier (SimpleField _ info) = id info

collectFieldInformation :: Data a => Form a -> [FieldInformation a]
collectFieldInformation form = map getFieldInfo (collectFields form)
  where
    getFieldInfo (SimpleField _ f) = f
    getFieldInfo (CalculatedField _ f _) = f

extractFieldInfo :: Field a -> FieldInformation a
extractFieldInfo (CalculatedField _ i _) = i
extractFieldInfo (SimpleField _ i) = i

extractAnnotationFromField :: Field a -> a
extractAnnotationFromField (CalculatedField anno _ _) = anno
extractAnnotationFromField (SimpleField anno _) = anno

collectCalculatedFields :: Data a => Form a -> [Field a]
collectCalculatedFields form = filter isCalc (collectFields form)
  where
    isCalc x =
      case x of
        CalculatedField{} -> True
        SimpleField{}     -> False
