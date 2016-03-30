module Main where

import Control.Monad as C
import Graphics.UI.WX
import GUIElement
import GUIError
import GUIContext
import QL.Language.Syntax.Ast as A
import QL.Language.Parser
import System.Environment

gui :: Show a => [a] -> A.Form -> IO ()
gui warnings astForm = do
  f <- frame [text:= formName astForm, visible:= True]
  unless (null warnings) $ showWarningDialog f (show warnings)
  context <- initializeGUIContext f astForm
  _ <- addToLayout f (guiElements context)
  return ()
    where formName (A.Form name _) = name

-- For some reason wx tries to consumes the args supplied at the command line so we have to clear them first
loadUI :: IO a -> IO ()
loadUI = withArgs []. start

main :: IO ()
main = do
  arg <- getArgs
  C.when (length arg /= 1) $ loadUI (showFatalErrorDialog argumentErrorTitle argumentErrorMsg)
  result <- parseFile (getFileName arg)
  case result of
    Left e -> loadUI (showFatalErrorDialog loadingErrorTitle (show e))
    Right (x,y) -> loadUI $ gui x y
  return ()
    where getFileName = head

