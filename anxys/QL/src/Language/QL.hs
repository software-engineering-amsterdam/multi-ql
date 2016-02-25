module QL (parseFile) where

import qualified Parsing  as P (parse)
import Simplify (simplify)
import TypeChecker (SemanticError, analyze')
import Ast as S

data Error = PError ParseError
           | SError [SemanticError]

parse' :: String  -> String -> Either Error Form
parse' n s = case P.parse n s of
             Left x -> Left (PError x)
             Right form -> analyze form
             where
               analyze x =
                 let y = analyze' x in
                 if null y then Right (simplify x) else Left (SError y)


parseFile :: String -> IO (Either Error Form)
parseFile p = do
  contents <- readFile p
  return (parse' p contents)
