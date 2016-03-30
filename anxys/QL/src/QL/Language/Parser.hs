module QL.Language.Parser (parseFile) where

import           Data.List                            as L
import           Prelude                              hiding (id)
import qualified QL.Language.Syntax.Annotated.Parsing as P (ParseError, parse)
import           QL.Language.Syntax.Simplify          (Form, simplify)
import           QL.SemanticAnalysis.SemanticAnalysis (DuplicationIssue,
                                                       SemanticError,
                                                       duplicationWarnings,
                                                       hasNoErrors,
                                                       semanticCheck,
                                                       toSemanticError)

data Error = PError P.ParseError
           | SError [SemanticError]

instance Show Error
 where
   show (PError x) = show x
   show (SError x) = L.intercalate "\n"  (map show x)

parse :: String -> String -> Either Error ([DuplicationIssue], Form)
parse n s =
  case P.parse n s of
    Left x     -> Left (PError x)
    Right form -> analyze form
  where
    analyze x =
      let y = semanticCheck x
      in if hasNoErrors y
           then Right (duplicationWarnings y, simplify x)
           else Left (SError (toSemanticError y))

parseFile :: String -> IO (Either Error ([DuplicationIssue], Form))
parseFile p = do
  contents <- readFile p
  return (parse p contents)
