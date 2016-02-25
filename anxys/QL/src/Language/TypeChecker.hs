{-# LANGUAGE PatternSynonyms #-}

module TypeChecker where

import           AnnotatedAst as A
import           Ast as S (FieldType(Integer, Money, String, Boolean))
import qualified Data.List as L
import           Location (Location) 

type LeftType = S.FieldType

type RightType = S.FieldType

type Identifier = String

type TypeMap = (Identifier, S.FieldType)

data SemanticResult =
       SemanticResult
         { typeErrors :: [TypeError]
         , cycleErrors :: [DependencyError]
         , duplicationErrors :: [DuplicationIssue]
         }
  deriving (Eq, Show)

data SemanticError = DuplicationIssue DuplicationIssue
                   | TypeError TypeError
                   | DependencyError DependencyError

data DuplicationIssue = DuplicateIdentifier Identifier [Location]
                      | RedeclarationError Identifier Location [Location]
  deriving (Eq, Show)

data TypeError = UndeclaredVariable Location
               | TypeMismatch LeftType RightType Location
  deriving (Eq, Show) -- Maybe use an instance declaration for show

data DependencyError = CyclicDependencyError Identifier Location
  deriving (Eq, Show)

hasNoErrors :: SemanticResult -> Bool
hasNoErrors a = (null . cycleErrors) a && (null . typeErrors) a && (null . duplicationErrors) a

analyze' :: Form Location -> [SemanticError]
analyze' x =
  let res = analyze x
  in map DuplicationIssue (duplicationErrors res)
     ++ map DependencyError (cycleErrors res)
        ++ map TypeError (typeErrors res)

analyze :: Form Location -> SemanticResult
analyze x = SemanticResult typeErrors cycleErrors duplicationErrors
  where
    typeErrors = typecheckForm (collectFormTypeMap x) x
    cycleErrors = findCycles ((transitiveClosure . getIdentifierRelation) calcFields) calcFields
    calcFields = collectCalculatedFields x
    fields = collectFields x
    duplicationErrors = checkForDuplicates fields

checkForDuplicates :: [Field Location] -> [DuplicationIssue]
checkForDuplicates xs = map warning groups
  where
    dups = findDuplicates xs
    warning p = if sameType p
                  then DuplicateIdentifier ((A.id . fst . head) p) (map getLoc p)
                  else RedeclarationError ((A.id . fst . head) p) ((getLoc . head) p) (map getLoc p)
    sameType ys = all (== getT ((fst . head) ys)) (map (getT . fst) (tail ys))
    getT = getSimpleType . A.fieldType
    groups = L.groupBy sameIden fieldMap
    sameIden (x, _) (y, _) = A.id x == A.id y
    fieldMap = map (\x -> (getFieldInfo x, x)) dups
    getFieldInfo (CalculatedField _ i _) = i
    getFieldInfo (SimpleField _ i) = i
    getLoc (_, CalculatedField l _ _) = l
    getLoc (_, SimpleField l _) = l

findDuplicates :: [Field Location] -> [Field Location]
findDuplicates xs = map snd (concat (getDups group))
  where
    idmap = map (\x -> (A.id (getFieldInfo x), x)) xs
    sameIden (x, _) (y, _) = x == y
    group = L.groupBy sameIden idmap
    getDups = filter (\x -> length x > 1)
    getFieldInfo (CalculatedField _ i _) = i
    getFieldInfo (SimpleField _ i) = i

findCycles :: [(Identifier, Identifier)] -> [Field Location] -> [DependencyError]
findCycles [] _ = []
findCycles (x:xs) env = checkForCycles x ++ findCycles xs env
  where
    checkForCycles (y, z) = if (y, y) `elem` (x : xs)
                              then map (uncurry CyclicDependencyError) (findIdent (y, z))
                              else []
    env' = map (\(CalculatedField loc info _) -> (A.id info, loc)) env
    findIdent q = filter (\(i, _) -> i == fst q) env'

transitiveClosure :: Eq a => [(a, a)] -> [(a, a)]
transitiveClosure closure
  | closure == closureUntilNow = closure
  | otherwise = transitiveClosure closureUntilNow
  where
    closureUntilNow =
      L.nub $ closure ++ [(a, c) | (a, b) <- closure
                                 , (b', c) <- closure
                                 , b == b']

getIdentifierRelation :: [Field Location] -> [(Identifier, Identifier)]
getIdentifierRelation [] = []
getIdentifierRelation (x:xs) =
  rs (aux x) ++ getIdentifierRelation xs
  where
    rs (varName, Variable _ name) = [(varName, name)]
    rs (_, Literal _ _) =
      []
    rs (varName, UnaryOperation _ _ rhs) =
      rs (varName, rhs)
    rs (varName, BinaryOperation _ _ lhs rhs) =
      rs (varName, lhs) ++ rs (varName, rhs)
    aux (CalculatedField _ info expr) = (A.id info, expr)
    aux (SimpleField _ _) = error "Attempted to get values for SimpleFields"

mapBlock :: (Statement a -> b) -> Block a -> [b]
mapBlock _ [] =
  []
mapBlock f (s:ss) =
  f s : mapBlock f ss

collectFields :: Form Location -> [Field Location]
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

collectCalculatedFields :: Form Location -> [Field Location]
collectCalculatedFields form = filter isCalc (collectFields form)
  where
    isCalc x =
      case x of
        CalculatedField{} -> True
        SimpleField{}     -> False

typecheckForm :: [TypeMap] -> Form Location -> [TypeError]
typecheckForm types (Form _ _ ss) =
  typeCheckStatement ss
  where
    typeCheckStatement =
      concatMap typeCheckStatement'
    typeCheckStatement' (If _ expr ifblock) =
      case getType types expr of
        Left e ->
          e
        Right _ -> -- Gotta be bools
          [] ++ typeCheckStatement ifblock
    typeCheckStatement' (IfElse _ expr ifblock elseblock) =
      case getType types expr of
        Left e ->
          e
        Right _ -> -- Gotta be bools
          [] ++ typeCheckStatement ifblock ++ typeCheckStatement elseblock
    typeCheckStatement' (Field _ (SimpleField _ _)) =
      []
    typeCheckStatement' (Field _ (CalculatedField _ _ expr)) =
      case getType types expr of
        Left e ->
          e
        Right _ ->
          []

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
getType types (UnaryOperation _ _ rhs) = getType types rhs
getType types (BinaryOperation loc t lhs rhs) =
  case getBinType oExp rType lType of
    Left a ->
      Left a
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
  Right rhs -- TODO: Maybe not do this.

isValidBinOp :: BinaryOperation Location -> S.FieldType -> S.FieldType -> Bool
isValidBinOp (A.Equals _) lhs rhs =
  lhs == rhs
isValidBinOp (A.NotEquals _) lhs rhs =
  lhs == rhs
isValidBinOp op lhs rhs =
  lhs `elem` ops && rhs `elem` ops
  where
    ops =
      allowedOps op

-- Simpler to just do this
allowedOps :: BinaryOperation a -> [S.FieldType]
allowedOps (A.Addition _) = [S.Integer, S.Money]
allowedOps (A.Subtraction _) = [S.Integer, S.Money]
allowedOps (A.Multiplication _) = [S.Integer, S.Money]
allowedOps (A.Division _) = [S.Integer, S.Money]
allowedOps (A.And _) = [S.Boolean]
allowedOps (A.Or _) = [S.Boolean]
allowedOps (A.GreaterThanOrEquals _) = [S.Integer, S.Money]
allowedOps (A.GreaterThan _) = [S.Integer, S.Money]
allowedOps (A.LesserThanOrEquals _) = [S.Integer, S.Money]
allowedOps (A.LesserThan _) = [S.Integer, S.Money]
allowedOps (A.StringConcatenation _) = [S.String]
allowedOps (A.NotEquals _) = [S.Integer, S.Money, S.Boolean, S.String]
allowedOps (A.Equals _) = [S.Integer, S.Money, S.Boolean, S.String]

collectFormTypeMap :: Form Location -> [TypeMap]
collectFormTypeMap =
  collectTypeMap . collectFieldInfo

collectTypeMap :: [FieldInformation a] -> [TypeMap]
collectTypeMap =
  map (\y -> (A.id y, getSimpleType $ A.fieldType y))


collectFieldInfo :: A.Form a -> [FieldInformation a]
collectFieldInfo (A.Form _ _ stmts) =
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

getSimpleType :: A.FieldType a -> S.FieldType
getSimpleType (A.Money _) = S.Money
getSimpleType (A.Integer _) = S.Integer
getSimpleType (A.String _) = S.String
getSimpleType (A.Boolean _) = S.Boolean
