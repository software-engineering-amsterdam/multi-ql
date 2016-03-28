module TypeChecker where

import           AnnotatedAst as A
import           Ast as S (FieldType(Integer, Money, String, Boolean))
import qualified Data.List as L
import           Location (Location)
import           Identifier

type LeftType = S.FieldType

type RightType = S.FieldType

type TypeMap = (Identifier, S.FieldType)

data SemanticResult =
       SemanticResult
         { typeErrors :: [TypeError]
         , cycleErrors :: [DependencyError]
         , duplicationErrors :: [DuplicationIssue]
         , duplicationWarnings :: [DuplicationIssue]
         }
  deriving (Eq, Show)

data SemanticError = DuplicationIssue DuplicationIssue
                   | TypeError TypeError
                   | DependencyError DependencyError

instance Show DuplicationIssue
 where
   show = showDuplicationIssue

instance Show SemanticError
 where
   show (DuplicationIssue x) = show x
   show (TypeError x) = showIssue x
   show (DependencyError x) = showIssue x

class HumanReadableIssue a
 where showIssue :: a -> String

instance HumanReadableIssue DuplicationIssue
 where
  showIssue = showDuplicationIssue

instance HumanReadableIssue TypeError
 where
  showIssue = showTypeError

instance HumanReadableIssue DependencyError
  where
  showIssue = showDependencyError

data DuplicationIssue = DuplicateIdentifier Identifier [Location]
                      | RedeclarationError Identifier Location [Location]
  deriving (Eq)

showDuplicationIssue :: DuplicationIssue -> String
showDuplicationIssue (DuplicateIdentifier identifier duplicateLocations) = "The identifier: " ++ show identifier ++ "," ++ " has been declared multiple times at the following locations:" ++ show duplicateLocations
showDuplicationIssue (RedeclarationError identifier _ duplicateLocations) = "The identifier: " ++ show identifier ++ " has been redeclared with different types at the following locations: " ++ show duplicateLocations

data TypeError = UndeclaredVariable Location
               | TypeMismatch LeftType RightType Location
  deriving (Eq, Show) --TODO: Maybe use an instance declaration for show

showTypeError :: TypeError -> String
showTypeError (UndeclaredVariable location) = "There is a reference to an undeclared variable at " ++ show location
showTypeError (TypeMismatch lhs rhs location) = "There is a type error at " ++ show location ++ ":" ++ " (" ++ show lhs ++ " and " ++ show rhs ++ ")."

data DependencyError = CyclicDependencyError Identifier Location
                     | PostDependencyError (Identifier, Location) (Identifier, [Location])
  deriving (Eq, Show) --TODO: Maybe use an instance declaration for show

showDependencyError :: DependencyError -> String
showDependencyError (CyclicDependencyError identifier location) = "There is a cyclic dependency for identifier: " ++ show identifier ++ " at " ++ show location ++ "."
showDependencyError (PostDependencyError (dependant, dependantLoc) (dependency, dependencyLoc)) = "The identifier: " ++ show dependant ++ " at " ++ show dependantLoc ++ " relies on the identifier: " ++ show dependency ++ " defined after it at " ++ show dependencyLoc ++ "."

hasNoErrors :: SemanticResult -> Bool
hasNoErrors a = (null . cycleErrors) a && (null . typeErrors) a && (null . duplicationErrors) a

toSemanticError :: SemanticResult -> [SemanticError]
toSemanticError x = map DuplicationIssue (duplicationErrors x)
     ++ map DependencyError (cycleErrors x)
        ++ map TypeError (typeErrors x)

semanticCheck::Form Location -> SemanticResult 
semanticCheck = analyze

analyze :: Form Location -> SemanticResult
analyze x = uncurry (SemanticResult tErrors depErrors) dIssues
  where
    tErrors = typecheckForm (collectFormTypeMap x) x
    depErrors = cErrors ++ checkForPostDependencies x
    cErrors = findCycles ((transitiveClosure . getIdentifierRelation) calcFields) calcFields
    calcFields = collectCalculatedFields x
    fields = collectFields x
    dErrors = checkDuplicates fields
    dIssues = L.partition (not.isWarning) dErrors
    isWarning issue = case issue of
        DuplicateIdentifier{} -> True
        RedeclarationError{} -> False 

checkDuplicates :: [Field Location] -> [DuplicationIssue]
checkDuplicates xs = map issue groups
  where
    dups = findDuplicates xs
    issue p = if sameType p
                  then DuplicateIdentifier ((A.id . fst . head) p) (map getLoc p)
                  else RedeclarationError ((A.id . fst . head) p) ((getLoc . head) p) (map getLoc p)
    sameType ys = all (== getT ((fst . head) ys)) (map (getT . fst) (tail ys))
    getT = getSimpleType . A.fieldType
    groups = L.groupBy sameIden fieldMap
    sameIden (x, _) (y, _) = A.id x == A.id y
    fieldMap = map (\x -> (extractFieldInfo x, x)) dups
    getLoc (_, CalculatedField l _ _) = l
    getLoc (_, SimpleField l _) = l

extractFieldInfo :: Field Location -> FieldInformation Location
extractFieldInfo (CalculatedField _ i _) = i
extractFieldInfo (SimpleField _ i) = i

findDuplicates :: [Field Location] -> [Field Location]
findDuplicates xs = map snd (concat (getDups group))
  where
    idmap = map (\x -> (A.id (extractFieldInfo x), x)) xs
    sameIden (x, _) (y, _) = x == y
    group = L.groupBy sameIden idmap
    getDups = filter (\x -> length x > 1)

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

checkForPostDependencies :: Form Location -> [DependencyError]
checkForPostDependencies form = L.nub $ concatMap (\x -> map (PostDependencyError (getIdentifier x, head (getLocations (getIdentifier x))).getDeclarationLocations) (postDeclarations x))  calcFields
  where extractLocationFromField = extractAnnotationFromField
        calcFields = collectCalculatedFields form
        fields = collectFields form
        getDeclarationLocations y = (y, getLocations y)
        calcIdentifiers = map getIdentifier calcFields
        getLocations x = map extractLocationFromField (filter ((x ==).getIdentifier) fields) 
        identifiers = map getIdentifier fields 
        dependencies x = map snd (dependencyRelation (toIdentifierExpr x))
        postDeclarations x = filter (isPostDeclaration (getIdentifier x)) (dependencies x)
        isPostDeclaration x y = minimum (L.elemIndices x identifiers) < minimum (L.elemIndices y identifiers)

dependencyRelation :: (Identifier, Expression a) -> [(Identifier, Identifier)]
dependencyRelation (varName, Variable _ name) = [(varName, name)]
dependencyRelation (_, Literal _ _) =
  []
dependencyRelation (varName, UnaryOperation _ _ rhs) =
  dependencyRelation (varName, rhs)
dependencyRelation (varName, BinaryOperation _ _ lhs rhs) =
  dependencyRelation (varName, lhs) ++ dependencyRelation (varName, rhs)

toIdentifierExpr :: Field a -> (Identifier, Expression a)
toIdentifierExpr (CalculatedField _ info expr) = (A.id info, expr)
toIdentifierExpr (SimpleField _ _) = error "Attempted to get values for SimpleFields"

getIdentifierRelation :: [Field Location] -> [(Identifier, Identifier)]
getIdentifierRelation  = foldr ((++) . dependencyRelation .toIdentifierExpr) []  

typecheckForm :: [TypeMap] -> Form Location -> [TypeError]
typecheckForm types (Form _ _ ss) =
  typeCheckStatement ss
  where
    typeCheckStatement =
      concatMap typeCheckStatement'
    typeCheckStatement' (If loc expr ifblock) =
      case getType types expr of
        Left e ->
          e
        Right x -> if x == S.Boolean      -- Gotta be bools
                     then [] ++ typeCheckStatement ifblock
                     else [TypeMismatch x S.Boolean loc]
    typeCheckStatement' (IfElse loc expr ifblock elseblock) =
      case getType types expr of
        Left e ->
          e
        Right x -> if x == S.Boolean      -- Gotta be bools
                     then [] ++ typeCheckStatement ifblock ++ typeCheckStatement elseblock
                     else [TypeMismatch x S.Boolean loc]
    typeCheckStatement' (Field _ (SimpleField _ _))
     =
      []
    typeCheckStatement' (Field _ (CalculatedField loc info expr))
     =
      case getType types expr of
        Left e ->
          e
        Right x -> if x == getT info then
          [] else [TypeMismatch x (getT info) loc]
    getT = getSimpleType . A.fieldType

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
getType types exp =
  getBinType exp rType lType 
  where
    (BinaryOperation _ _ lhs rhs) = exp 
    rType =
      getType types rhs
    lType =
      getType types lhs

getBinType :: Expression Location -> Either [TypeError] S.FieldType -> Either [TypeError] S.FieldType -> Either [TypeError] S.FieldType
getBinType _ (Left lhs) (Left rhs) =
  Left (rhs ++ lhs)
getBinType _ (Left lhs) _ =
  Left lhs
getBinType _ _ (Left rhs) =
  Left rhs
getBinType (BinaryOperation loc op _ _) (Right lhs) (Right rhs) =
  if isValidBinOp op lhs rhs
    then Right rhs -- TODO: This depends on the subexpressions
    else Left [TypeMismatch lhs rhs loc]
getBinType _ (Right _) (Right _) = error "Called with something that isn't a binary expression"

isValidBinOp :: BinaryOperation Location -> S.FieldType -> S.FieldType -> Bool
isValidBinOp op lhs rhs =
  (lhs, rhs) `elem` ops
  where
    ops =
      allowedOps op

-- Simpler to just do this
allowedOps :: BinaryOperation a -> [(S.FieldType, S.FieldType)]
allowedOps (A.Addition _) = [ (S.Integer, S.Money)
                            , (S.Integer, S.Integer)
                            , (S.Money, S.Integer)
                            , (S.Money, S.Money)
                            ]
allowedOps (A.Subtraction _) = [ (S.Integer, S.Money)
                               , (S.Integer, S.Integer)
                               , (S.Money, S.Integer)
                               , (S.Money, S.Money)
                               ]
allowedOps (A.Multiplication _) = [ (S.Integer, S.Money)
                                  , (S.Integer, S.Integer)
                                  , (S.Money, S.Integer)
                                  ]
allowedOps (A.Division _) = [(S.Integer, S.Money), (S.Integer, S.Integer), (S.Money, S.Integer)]
allowedOps (A.And _) = [(S.Boolean, S.Boolean)]
allowedOps (A.Or _) = [(S.Boolean, S.Boolean)]
allowedOps (A.GreaterThanOrEquals _) = [ (S.Integer, S.Money)
                                       , (S.Integer, S.Integer)
                                       , (S.Money, S.Integer)
                                       , (S.Money, S.Money)
                                       ]
allowedOps (A.GreaterThan _) = [ (S.Integer, S.Money)
                               , (S.Integer, S.Integer)
                               , (S.Money, S.Integer)
                               , (S.Money, S.Money)
                               ]
allowedOps (A.LesserThanOrEquals _) = [ (S.Integer, S.Money)
                                      , (S.Integer, S.Integer)
                                      , (S.Money, S.Integer)
                                      , (S.Money, S.Money)
                                      ]
allowedOps (A.LesserThan _) = [ (S.Integer, S.Money)
                              , (S.Integer, S.Integer)
                              , (S.Money, S.Integer)
                              , (S.Money, S.Money)
                              ]
allowedOps (A.StringConcatenation _) = [(S.String, S.String)]
allowedOps (A.NotEquals _) = [ (S.Integer, S.Integer)
                             , (S.Money, S.Money)
                             , (S.Boolean, S.Boolean)
                             , (S.String, S.String)
                             ]
allowedOps (A.Equals _) = [ (S.Integer, S.Integer)
                          , (S.Money, S.Money)
                          , (S.Boolean, S.Boolean)
                          , (S.String, S.String)
                          ]

collectFormTypeMap :: Form Location -> [TypeMap]
collectFormTypeMap =
  collectTypeMap . collectFieldInformation

collectTypeMap :: [FieldInformation a] -> [TypeMap]
collectTypeMap =
  map (\y -> (A.id y, getSimpleType $ A.fieldType y))

getSimpleType :: A.FieldType a -> S.FieldType
getSimpleType (A.Money _) = S.Money
getSimpleType (A.Integer _) = S.Integer
getSimpleType (A.String _) = S.String
getSimpleType (A.Boolean _) = S.Boolean
