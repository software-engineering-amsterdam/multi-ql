module Interpreter
       where

import           Ast                 as A
import           Control.Applicative
import           Control.Monad
import           Environment
import           Prelude             hiding (EQ, GT, lookup)
import           Value

--TODO: Get rid of the either. Typechecking catches almost everything
newtype Interpreter a = Interpreter { runInterp :: Environment Value -> Either String (a, Environment Value) }

instance Functor Interpreter where
  fmap f p = Interpreter $ \s -> case runInterp p s of
    Left v          -> Left v
    Right (a, rest) -> Right (f a, rest)

instance Applicative Interpreter where
  pure a = Interpreter $ \s -> Right (a, s)
  p1 <*> p2 = Interpreter $ \s -> do
    (f, rest) <- runInterp p1 s
    (a, rest2) <- runInterp p2 rest
    Right (f a, rest2)

instance Monad Interpreter where
  return x = Interpreter $ \r -> Right (x, r)
  i >>= k = Interpreter $ \r -> case runInterp i r of
    Left msg      -> Left msg
    Right (x, r') -> runInterp (k x) r'
  fail msg = Interpreter $ \_ -> Left msg

eval :: Expr -> Interpreter Value
eval (Lit (ILit lit)) = return (IntValue lit)
eval (Lit (BLit lit)) = return (BoolValue lit)
eval (Lit (SLit lit)) = return (StringValue lit)
eval (Lit (MLit lit)) = return (MoneyValue lit)
eval (Var name) = Interpreter $ \r -> case lookup name r of
  Just v  -> Right (v, r)
  Nothing -> Left ("Unbound variable '" ++ name ++ "'")
eval (BinOp op lhs rhs) = liftM2 (evalBinOp op) (eval lhs) (eval rhs)
eval (UnOp Not rhs) = neg <$> eval rhs

--TODO Intervalue stuff
evalBinOp :: BinOp -> Value -> Value -> Value
-- Ints
evalBinOp Add (IntValue x) (IntValue y) = IntValue $ x + y
evalBinOp Sub (IntValue x) (IntValue y) = IntValue $ x - y
evalBinOp Div (IntValue x) (IntValue y) = IntValue $ x `div` y
evalBinOp Mul (IntValue x) (IntValue y) = IntValue $ x * y
evalBinOp GT (IntValue x) (IntValue y) = BoolValue $ x > y
evalBinOp GTE (IntValue x) (IntValue y) = BoolValue $ x >= y
--Money
evalBinOp Add (MoneyValue x) (MoneyValue y) = MoneyValue $ x + y
evalBinOp Sub (MoneyValue x) (MoneyValue y) = MoneyValue $ x - y
evalBinOp GT (MoneyValue x) (MoneyValue y) = BoolValue $ x > y
evalBinOp GTE (MoneyValue x) (MoneyValue y) = BoolValue $ x >= y
--String
evalBinOp SConcat (StringValue x) (StringValue y) = StringValue $ x ++ y
--Bool
evalBinOp And (BoolValue x) (BoolValue y) = BoolValue $ x && y
evalBinOp Or (BoolValue x) (BoolValue y) = BoolValue $ x || y
--EQ
evalBinOp EQ (StringValue x) (StringValue y) = BoolValue $ x == y
evalBinOp EQ (IntValue x) (IntValue y) = BoolValue $ x == y
evalBinOp EQ (MoneyValue x) (MoneyValue y) = BoolValue $ x == y
evalBinOp EQ (BoolValue x) (BoolValue y) = BoolValue $ x == y
evalBinOp _ _ _ = error "Not supported"

neg :: Value -> Value
neg (BoolValue v) = BoolValue $ not v
neg _ = error "Not supported"

set :: String -> Value -> Interpreter ()
set x v = Interpreter $ \r -> Right ((), declare r x v)

exec :: Form -> Environment Value -> Either String (Environment Value)
exec p r =
  case runInterp (execForm p) emptyEnv of
    Left msg      -> Left msg
    Right (_, r') -> Right r'
    where
        execForm (Form _ block) = mapM_ ex' block
        ex' (If cond block) = do
            c <- eval cond
            when (c == BoolValue True) $ mapM_ ex' block
            return ()
        ex' (Field (CalcField info expr)) = do
            res <- eval expr
            set (A.id info) res
            return ()
        ex' (Field (SimpField info)) =
            let name = A.id info
                t = A.fieldType info
            in Interpreter $ \c -> case lookup name r of
                Nothing -> Right ((), declare c name (defaultVal t))
                Just v  -> Right ((), declare c name v)
