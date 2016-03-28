module Interpreter (exec)
       where

import           Ast                 as A
import           Control.Applicative
import           Control.Monad
import           Environment
import           Prelude             hiding (EQ, GT, lookup)
import           Value
import           Identifier
import           Data.Decimal

type InterpreterError = String

newtype Interpreter a = Interpreter { runInterp :: Environment Value -> Either InterpreterError (a, Environment Value) }

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
eval (BinOp op lhs rhs) = do
  left <- eval lhs
  right <- eval rhs
  evalBinOp op right left
eval (UnOp Not rhs) = do
  right <- eval rhs
  return (neg right)

toDecimal :: Integer -> Decimal
toDecimal = Decimal 0 

evalBinOp :: BinOp -> Value -> Value -> Interpreter Value
-- Undefined
evalBinOp _ Undefined _ = return Undefined
evalBinOp _ _ Undefined = return Undefined
-- Ints
evalBinOp Add (IntValue x) (IntValue y) = return (IntValue $ x + y)
evalBinOp Sub (IntValue x) (IntValue y) = return (IntValue $ x - y)
evalBinOp Div (IntValue _) (IntValue 0) = return Undefined -- Divide by Zero
evalBinOp Div (IntValue x) (IntValue y) = return (IntValue $ x `div` y)
evalBinOp Mul (IntValue x) (IntValue y) = return (IntValue $ x * y)
evalBinOp GT (IntValue x) (IntValue y) =  return (BoolValue $ x > y)
evalBinOp GTE (IntValue x) (IntValue y) =  return (BoolValue $ x >= y)
--Money + Ints
evalBinOp Add (IntValue x) (MoneyValue y) = return (MoneyValue $ toDecimal x + y)
evalBinOp Add (MoneyValue x) (IntValue y) = return (MoneyValue $ x + toDecimal y)
evalBinOp Sub (IntValue x) (MoneyValue y) = return (MoneyValue $ toDecimal x - y)
evalBinOp Sub (MoneyValue x) (IntValue y) = return (MoneyValue $ x - toDecimal y)
evalBinOp Mul (IntValue x) (MoneyValue y) = return (MoneyValue $ toDecimal x * y)
evalBinOp Mul (MoneyValue x) (IntValue y) = return (MoneyValue $ x * toDecimal y)
evalBinOp GT (IntValue x) (MoneyValue y) = return (BoolValue $ toDecimal x > y)
evalBinOp GT (MoneyValue x) (IntValue y) = return (BoolValue $ x > toDecimal y)
evalBinOp GTE (IntValue x) (MoneyValue y) = return (BoolValue $ toDecimal x >= y)
evalBinOp GTE (MoneyValue x) (IntValue y) = return (BoolValue $ x >= toDecimal y)
--Money
evalBinOp Add (MoneyValue x) (MoneyValue y) = return (MoneyValue $ x + y)
evalBinOp Sub (MoneyValue x) (MoneyValue y) = return (MoneyValue $ x - y)
evalBinOp GT (MoneyValue x) (MoneyValue y) = return (BoolValue $ x > y)
evalBinOp GTE (MoneyValue x) (MoneyValue y) = return (BoolValue $ x >= y)
--String
evalBinOp SConcat (StringValue x) (StringValue y) = return (StringValue $ x ++ y)
--Bool
evalBinOp And (BoolValue x) (BoolValue y) = return (BoolValue $ x && y)
evalBinOp Or (BoolValue x) (BoolValue y) = return (BoolValue $ x || y)
--EQ
evalBinOp EQ (StringValue x) (StringValue y) = return (BoolValue $ x == y)
evalBinOp EQ (IntValue x) (IntValue y) = return (BoolValue $ x == y)
evalBinOp EQ (MoneyValue x) (MoneyValue y) = return (BoolValue $ x == y)
evalBinOp EQ (BoolValue x) (BoolValue y) = return (BoolValue $ x == y)
evalBinOp _ _ _ = fail "Not supported"

neg :: Value -> Value
neg (BoolValue v) = BoolValue $ not v
neg _ = error "Not supported on a non boolean value"

set :: Identifier -> Value -> Interpreter ()
set x v = Interpreter $ \r -> Right ((), declare r x v)

exec :: Form -> Environment Value -> Either InterpreterError (Environment Value)
exec ast r =
  case runInterp (execForm ast) emptyEnv of
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
