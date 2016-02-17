module TypeChecker where

import           AnnotatedAst as A
import           Ast as S (FieldType, FieldType(Integer), FieldType(Money), FieldType(String),
                           FieldType(Boolean))
import           Simplify

data TypeError = UndeclaredVariable Location
               | TypeMismatch S.FieldType S.FieldType Location
  deriving (Eq, Show)

data CyclicDependencyError = CyclicDependencyError Location

type TypeMap = (String, S.FieldType)

type CalcFieldEnv = (String, Expression Location)

type CalculatedValue = (String, Expression Location)

hasCycles :: [CalculatedValue] -> [CyclicDependencyError]
hasCycles [] =
  []
hasCycles (c:cs) =
  rs c ++ hasCycles cs
  where
    rs (varName, Variable loc name) =
      [CyclicDependencyError loc | varName == name]
    rs (_, Literal _ _) =
      []
    rs (varName, UnaryOperation _ _ rhs) =
      rs (varName, rhs)
    rs (varName, BinaryOperation _ _ lhs rhs) =
      rs (varName, lhs) ++ rs (varName, rhs)

mapBlock :: (Statement a -> b) -> Block a -> [b]
mapBlock _ [] =
  []
mapBlock f (s:ss) =
  f s : mapBlock f ss

collectCalculatedValuesFromForm :: Form Location -> [CalculatedValue]
collectCalculatedValuesFromForm (Form _ _ stmnts) =
  getCalcFieldStmnts stmnts
  where
    getCalcFieldStmnts' (If _ _ ifblock) =
      getCalcFieldStmnts ifblock
    getCalcFieldStmnts' (IfElse _ _ ifblock elseblock) =
      getCalcFieldStmnts ifblock ++ getCalcFieldStmnts elseblock
    getCalcFieldStmnts' (Field _ (SimpleField _ _)) =
      []
    getCalcFieldStmnts' (Field _ (CalculatedField _ info expr)) =
      [(A.id info, expr)]
    getCalcFieldStmnts =
      concatMap getCalcFieldStmnts'

typecheckForm :: [TypeMap] -> Form Location -> [TypeError]
typecheckForm types (Form _ _ ss) =
  typeCheckStatement ss
  where
    typeCheckStatement' (If _ expr ifblock) =
      case getType types expr of
        Left e ->
          e
        Right _ ->
          [] ++ typeCheckStatement ifblock
    typeCheckStatement' (IfElse _ expr ifblock elseblock) =
      case getType types expr of
        Left e ->
          e
        Right _ ->
          [] ++ typeCheckStatement ifblock ++ typeCheckStatement elseblock
    typeCheckStatement' (Field _ (SimpleField _ _)) =
      []
    typeCheckStatement' (Field _ (CalculatedField _ _ expr)) =
      case getType types expr of
        Left e ->
          e
        Right _ ->
          []
    typeCheckStatement =
      concatMap typeCheckStatement'

getType :: [TypeMap] -> Expression Location -> Either [TypeError] S.FieldType
getType _ (Literal _ (IntegerLiteral _ _)) =
  Right S.Integer
getType _ (Literal _ (MoneyLiteral _ _)) =
  Right S.Money
getType _ (Literal _ (StringLiteral _ _)) =
  Right S.String
getType _ (Literal _ (BooleanLiteral _ _)) =
  Right S.Boolean
getType types (Variable loc name) =
  case lookup name types of
    Nothing ->
      Left [UndeclaredVariable loc]
    Just a ->
      Right a
getType types (UnaryOperation _ _ rhs) =
  case getType types rhs of
    Right a ->
      Right a
    Left b ->
      Left b
getType types (BinaryOperation loc t lhs rhs) =
  case getBinType oExp rType lType of
    Left a ->
      Left a ---Todo?
    Right b ->
      Right b
  where
    rType =
      getType types rhs
    lType =
      getType types lhs
    oExp =
      BinaryOperation loc t lhs rhs

getBinType :: Expression Location -> Either [TypeError] S.FieldType -> Either [TypeError] S.FieldType -> Either [TypeError] S.FieldType
getBinType _ (Left lhs) (Left rhs) =
  Left (rhs ++ lhs)
getBinType _ (Left lhs) _ =
  Left lhs
getBinType _ _ (Left rhs) =
  Left rhs
getBinType (BinaryOperation loc op _ _) (Right lhs) (Right rhs) =
  if isValidBinOp op lhs rhs
    then Right lhs
    else Left [TypeMismatch lhs rhs loc]
getBinType _ (Right _) (Right rhs) =
  Right rhs

isValidBinOp :: BinaryOperation Location -> S.FieldType -> S.FieldType -> Bool
isValidBinOp (A.Equals _) lhs rhs =
  lhs == rhs
isValidBinOp (A.NotEquals _) lhs rhs =
  lhs == rhs
isValidBinOp op lhs rhs =
  lhs `elem` ops && rhs `elem` ops
  where
    ops =
      table op

table :: BinaryOperation a -> [S.FieldType]
table (A.Addition _) =
  [S.Integer, S.Money]
table (A.Subtraction _) =
  [S.Integer, S.Money]
table (A.Multiplication _) =
  [S.Integer, S.Money]
table (A.Division _) =
  [S.Integer, S.Money]
table (A.And _) =
  [S.Boolean]
table (A.Or _) =
  [S.Boolean]
table (A.GreaterThanOrEquals _) =
  [S.Integer, S.Money]
table (A.GreaterThan _) =
  [S.Integer, S.Money]
table (A.LesserThanOrEquals _) =
  [S.Integer, S.Money]
table (A.LesserThan _) =
  [S.Integer, S.Money]
table (A.StringConcatenation _) =
  [S.String]
table (A.NotEquals _) =
  [S.Integer, S.Money, S.Boolean, S.String]
table (A.Equals _) =
  [S.Integer, S.Money, S.Boolean, S.String]

collectFormTypeMap :: Form Location -> [TypeMap]
collectFormTypeMap =
  collectTypeMap . collectFieldInfo

collectTypeMap :: [FieldInformation a] -> [TypeMap]
collectTypeMap =
  map (\y -> (A.id y, sFieldType $ A.fieldType y))

collectFieldInfo :: A.Form a -> [FieldInformation a]
collectFieldInfo (A.Form _ _ stmts) =
  concatMap getFieldInfo stmts
  where
    getFieldInfo st =
      case st of
        Field _ (SimpleField _ f) ->
          [f]
        Field _ (CalculatedField _ f _) ->
          [f]
        If _ _ ss ->
          concatMap getFieldInfo ss
        IfElse _ _ xs ys ->
          concatMap getFieldInfo xs ++ concatMap getFieldInfo ys
