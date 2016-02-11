module TypeChecker
     where

import           Ast as A

data TypeError
     = UndeclaredVariable Region
     | TypeMismatch FieldType FieldType Region
       deriving (Eq,Show)

type TypeEnv = (String, FieldType)

getType :: [TypeEnv] -> Expr -> Either [TypeError] FieldType
getType _ (Lit (ILit _),_,_) = Right Integer
getType _ (Lit (MLit _),_,_) = Right Money
getType _ (Lit (SLit _),_,_) = Right String
getType _ (Lit (BLit _),_,_) = Right Boolean
getType types ((Var name),s,e) = case lookup name types of
                                 Nothing -> Left [UndeclaredVariable (Region s e)]
                                 Just a -> Right a
getType types ((UnOp _ rhs),_,_) = case getType types rhs of
                                   Right a -> Right a
                                   Left  b -> Left b
getType types ((BinOp t lhs rhs),s,e) = case aux oExp rType lType of
                                        Left a -> Left a ---Todo?
                                        Right b -> Right b
                         where
                           rType =  getType types rhs
                           lType = getType types lhs
                           oExp  = ((BinOp t lhs rhs),s,e)

aux :: Expr -> Either [TypeError] FieldType -> Either [TypeError] FieldType -> Either [TypeError] FieldType
aux _ (Left lhs) (Left rhs) =  Left (rhs ++ lhs)
aux _ (Left lhs) _ = Left lhs
aux _ _ (Left rhs) = Left rhs
aux ((BinOp op _ _),s,e) (Right lhs) (Right rhs) = if checkIfValid op lhs rhs then Right lhs else Left [TypeMismatch lhs rhs (Region s e)]

checkIfValid :: BinOp -> FieldType -> FieldType -> Bool
checkifValid A.EQ lhs rhs = lhs == rhs
checkifValid A.NEQ lhs rhs = lhs == rhs
checkIfValid op lhs rhs = case result of
  Nothing -> False
  Just ops -> lhs `elem` ops && rhs `elem` ops
  where result = lookup op table

table :: [(BinOp,[FieldType])]
table =  [(A.Add, [Integer, Money])
         , (A.Sub, [Integer, Money])
         , (A.Mul, [Integer, Money])
         , (A.Div, [Integer, Money])
         , (A.And, [Boolean])
         , (A.Or, [Boolean])
         , (A.GTE, [Integer, Money])
         , (A.GT, [Integer, Money])
         , (A.LTE, [Integer, Money])
         , (A.LT, [Integer, Money])
         , (A.SConcat, [String])
         ]
