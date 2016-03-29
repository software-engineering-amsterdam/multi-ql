module Main where

import Graphics.UI.WX
import GUIContext
import Value
import Identifier
import GUIElement
import Ast as A
import Data.Maybe
import Interpreter
import QL
import System.Environment
import System.IO
import System.Exit
import Control.Monad as C

displayError :: Show a => a -> IO () 
displayError = hPrint stderr

gui :: A.Form -> IO ()
gui astForm = do
  f <- frame [text := formName astForm, visible:= True]
  context <- initializeGUIContext f astForm
  _ <- addToLayout f (guiElements context)
  return ()
    where formName (A.Form name _) = name

main :: IO ()
main = do
  arg <- getArgs
  C.when (length arg /= 1) $ do
    displayError "Incorrect number of arguments. Must provide a fileName"
    exitFailure
  result <- parseFile (getFileName arg) 
  case result of
    Left e -> do
      displayError  e
      exitFailure
    Right (x,y) -> do
      mapM_ displayError x
      withArgs [] (start (gui y)) -- For some reason wx tries to consumes the args so we have to clear them first
  return ()
    where getFileName = head

