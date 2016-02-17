module TypeChecker
     where

import           AnnotatedAst as A
import Ast as S (FieldType, FieldType(Integer), FieldType(Money), FieldType(String), FieldType(Boolean))
import Simplify

data TypeError
     = UndeclaredVariable Location
     | TypeMismatch S.FieldType  S.FieldType Location
       deriving (Eq,Show)


type TypeMap = (String, S.FieldType)
type CalcFieldEnv = (String, Expression Location)

getType :: [TypeMap] -> Expression Location -> Either [TypeError] S.FieldType
getType _ (Literal _ (IntegerLiteral _ _)) = Right S.Integer
getType _ (Literal _ (MoneyLiteral _ _)) = Right S.Money
getType _ (Literal _ (StringLiteral _ _)) = Right S.String
getType _ (Literal _ (BooleanLiteral _ _ )) = Right S.Boolean
getType types (Variable loc name) = case lookup name types of
                                 Nothing -> Left [UndeclaredVariable loc]
                                 Just a -> Right a
getType types (UnaryOperation _ _ rhs) = case getType types rhs of
                                   Right a -> Right a
                                   Left  b -> Left b
getType types (BinaryOperation loc t lhs rhs) = case aux oExp rType lType of
                                        Left a -> Left a ---Todo?
                                        Right b -> Right b
                         where
                           rType =  getType types rhs
                           lType = getType types lhs
                           oExp  = BinaryOperation loc t lhs rhs

aux :: Expression Location -> Either [TypeError] S.FieldType -> Either [TypeError] S.FieldType -> Either [TypeError] S.FieldType
aux _ (Left lhs) (Left rhs) =  Left (rhs ++ lhs)
aux _ (Left lhs) _ = Left lhs
aux _ _ (Left rhs) = Left rhs
aux (BinaryOperation loc op _ _) (Right lhs) (Right rhs) = if checkIfValid op lhs rhs then Right lhs else Left [TypeMismatch lhs rhs loc]
aux _ (Right _) (Right rhs) = Right rhs

checkIfValid :: BinaryOperation Location -> S.FieldType -> S.FieldType -> Bool
checkIfValid (A.Equals _) lhs rhs = lhs == rhs
checkIfValid (A.NotEquals _) lhs rhs = lhs == rhs
checkIfValid op lhs rhs =  lhs `elem` ops && rhs `elem` ops
                           where ops = table op

table :: BinaryOperation a -> [S.FieldType]
table (A.Addition _ )= [S.Integer, S.Money]
table (A.Subtraction _)= [S.Integer, S.Money]
table (A.Multiplication _) =  [S.Integer, S.Money]
table (A.Division _) = [S.Integer, S.Money]
table (A.And _) = [S.Boolean]
table (A.Or _) = [S.Boolean]
table (A.GreaterThanOrEquals _) =  [S.Integer, S.Money]
table (A.GreaterThan _ ) =  [S.Integer, S.Money]
table (A.LesserThanOrEquals _) = [S.Integer, S.Money]
table (A.LesserThan _) = [S.Integer,S.Money]
table (A.StringConcatenation _ ) = [S.String]
table (A.NotEquals _ )= [S.Integer, S.Money, S.Boolean, S.String]
table (A.Equals _ )= [S.Integer, S.Money, S.Boolean, S.String]


collectTypeMap :: [FieldInformation a] -> [TypeMap]
collectTypeMap = map (\y -> (A.id y, sFieldType $ A.fieldType y))

collectFieldInfo :: A.Form a -> [FieldInformation a]
collectFieldInfo (A.Form _  _ stmts) = concatMap getFieldInfo stmts
  where getFieldInfo st = case st of
          Field _ (SimpleField _ f) -> [f]
          Field _ (CalculatedField _ f _) -> [f]
          If _ _ ss -> concatMap getFieldInfo ss
          IfElse _ _ xs ys ->  concatMap getFieldInfo xs ++  concatMap getFieldInfo ys
