module TypeChecker
     where

import Ast as A

data TypeError = TypeError Integer String

type TypeEnv = (String, FieldType)

class CheckAble a where
  validate :: [TypeEnv] ->  a -> [TypeError]

instance CheckAble Form where
  --validate types  (Form _ block) = concatMap (validate types) block

  validate types  (Form _ block) = undefined
instance CheckAble Stmnt' where
  validate  _ _ = []

instance CheckAble Expr where
  validate t (BinOp Add lhs rhs) = case (getT lhs, getT rhs) of
                                   (Just Integer, Just Integer) -> []
                                   (Just Money, Just Money) -> []
                                   (Just String, Just String) -> []
                                   (Just Boolean, Just Boolean) -> []
                                   (Nothing, _) -> [TypeError 2 ""]
                                   (_, Nothing) -> [TypeError 2 ""]
                                   (_,_)       -> []
                                   where getT = getType t
  validate t (BinOp Sub lhs rhs) = case (getT lhs, getT rhs) of
                                   (Just Integer, Just Integer) -> []
                                   (Just Money, Just Money) -> []
                                   (Just String, Just String) -> []
                                   (Just Boolean, Just Boolean) -> []
                                   (Nothing, _) -> [TypeError 2 ""]
                                   (_, Nothing) -> [TypeError 2 ""]
                                   (_,_)       -> []
                                   where getT = getType t
  validate t (BinOp Mul lhs rhs) = case (getT lhs, getT rhs) of
                                   (Just Integer, Just Integer) -> []
                                   (Just Money, Just Money) -> []
                                   (Just String, Just String) -> []
                                   (Just Boolean, Just Boolean) -> []
                                   (Nothing, _) -> [TypeError 2 ""]
                                   (_, Nothing) -> [TypeError 2 ""]
                                   (_,_)       -> []
                                   where getT = getType t
  validate t (BinOp Div lhs rhs) = case (getT lhs, getT rhs) of
                                   (Just Integer, Just Integer) -> []
                                   (Just Money, Just Money) -> []
                                   (Just String, Just String) -> []
                                   (Just Boolean, Just Boolean) -> []
                                   (Nothing, _) -> [TypeError 2 ""]
                                   (_, Nothing) -> [TypeError 2 ""]
                                   (_,_)       -> []
                                   where getT = getType t
  validate _ _ = []

instance CheckAble Lit where
  validate _  _ = [];

getType :: [TypeEnv] -> Expr -> Maybe FieldType
getType _ (Lit (ILit _)) = Just Integer
getType _ (Lit (MLit _)) = Just Money
getType _ (Lit (SLit _)) = Just String
getType _ (Lit (BLit _)) = Just Boolean
getType types (Var name) = lookup name types
getType types (UnOp _ lhs) = getType types lhs
getType types (BinOp  _ lhs rhs) = let getT = getType types in if getT lhs == getT rhs then getT lhs else Nothing

instance CheckAble Field where
  validate = undefined
