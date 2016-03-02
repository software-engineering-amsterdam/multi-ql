module Environment where

import qualified Data.Map as M
import Identifier

newtype Environment a = Environment (M.Map Identifier a) deriving (Show, Eq)

assocs :: Environment a -> [(Identifier, a)]
assocs (Environment env) = M.assocs env

emptyEnv :: Environment a
emptyEnv = Environment M.empty

declare :: Environment a -> Identifier -> a -> Environment a
declare (Environment env) i v = Environment (M.insert i v env)

lookup :: Identifier -> Environment a -> Maybe a
lookup i (Environment m) = M.lookup i m

map :: (a -> b) -> Environment a -> Environment b
map f (Environment env) = Environment (M.map f env)

get :: Environment a -> Identifier -> Maybe a
get (Environment env) i = M.lookup i env
