module GUIError
  where

import           Graphics.UI.WX
import           System.Environment
import           System.Exit
import           System.IO

argumentErrorMsg :: String
argumentErrorMsg = "Incorrect number of arguments. Must provide a fileName"

argumentErrorTitle :: String
argumentErrorTitle = "ArgumentError"

loadingErrorTitle :: String
loadingErrorTitle = "File Loading Error"

showStdError :: Show a => a -> IO ()
showStdError = hPrint stderr

showStdWarning :: Show a => a -> IO ()
showStdWarning = showStdWarning

showFatalErrorDialog :: String -> String ->  IO ()
showFatalErrorDialog title msg = do
  f <- frame [ visible:= False]
  showStdError title
  showStdError msg
  errorDialog f title msg
  exitFailure
  return ()

showUserInputErrorDialog :: Frame () -> String -> IO ()
showUserInputErrorDialog f msg = do
  errorDialog f "Input Error" msg
  return ()

showWarningDialog :: Frame () -> String -> IO ()
showWarningDialog f msg = do
  warningDialog f title msg
  return ()
    where title = "Warning"
