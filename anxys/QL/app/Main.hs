module Main where

import Graphics.UI.WX
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
import Control.Monad
import qualified Environment as E


data GUIContext = GUIContext {appFrame :: Frame (),
                              form :: A.Form,
                              guiElements :: [GUIElement],
                              environment      :: Var (E.Environment Value) 
                             }

createElems :: Frame () ->  A.Form -> IO GUIContext
createElems f astForm = do
  envRef <- varCreate initialEnv
  elems <- mapM (createElem (ctx [] envRef)) questionFields
  context <- createContext elems envRef
  _ <- mapM_ (addHandler context) elems
  updateGUI context
  return context
  where initialEnv = case exec astForm E.emptyEnv of
          Left x -> error x
          Right r -> r
        questionFields = A.fields astForm
        ctx = GUIContext f astForm
        createContext elems env = return (ctx elems env)

createElem :: GUIContext -> A.Field -> IO GUIElement
createElem ctx (SimpField info) = createElem' ctx info True
createElem ctx (CalcField info _) = createElem' ctx info False

createElem':: GUIContext -> A.FieldInfo -> Bool -> IO  GUIElement
createElem' ctx info isNotReadOnly = case fieldType info of
     Money -> standardControl
     Integer -> standardControl
     String -> standardControl
     Boolean -> do
        qLabel <- createStaticText f (A.label info)
        val <- getVal
        control <- createCheckBox f val
        configureReadOnly $ Checkbox eInfo qLabel control
     where getVal = do
                    env <- varGet (environment ctx)
                    return (getValue (A.id info) env)
           f = appFrame ctx
           eInfo = createElemInfo info isReadOnly
           standardControl = do
               qLabel <- createStaticText f (A.label info)
               val <- getVal
               textControl <- createTextControl f val
               configureReadOnly $ Text eInfo qLabel textControl
           configureReadOnly = setReadOnly isReadOnly
           isReadOnly = not isNotReadOnly

getValue :: Identifier -> E.Environment Value -> Value
getValue e env = fromMaybe Undefined (E.lookup e env)

main :: IO ()
main = do
  [fileName]  <- getArgs
  res <- parseFile fileName
  case res of
    Left e -> do
      hPrint stderr  e
      exitFailure
    Right (x,y) -> do
      mapM_ (hPrint stderr) x
      withArgs [] (start (gui y))
  return ()

gui :: A.Form -> IO ()
gui astForm = do
  print (show astForm)
  f <- frame [text := formName astForm, visible:= True]
  context <- createElems f astForm
  _ <- addToLayout f (guiElements context)
  return ()
    where defaultForm = A.Form "defaultForm" [Field (SimpField FieldInfo {A.label = "Display Text One", A.id = "idTest1", A.fieldType = A.Money}),Field (SimpField FieldInfo {A.label = "DisplayText2",
   A.id = "idTest2", fieldType = A.Integer})]
          formName (A.Form name _) = name


getNewEnv :: A.Form -> E.Environment Value -> E.Environment Value
getNewEnv astForm env = case exec astForm env of
           Left x -> error x
           Right r -> r


setFieldsVisibility :: GUIContext -> IO ()
setFieldsVisibility context = do
  env <- varGet (environment context)
  _ <- mapM_ (setVis env) (guiElements context)
  _ <- addToLayout (appFrame context) (guiElements context)
  return ()
  where isVisible env (Text info _ _) = case E.lookup (identifier info) env of
                                             Nothing -> False
                                             Just _ -> True
        isVisible env (Checkbox info _ _) = case E.lookup  (identifier info) env of
                                              Nothing -> False
                                              Just _ -> True
        setVis env e = setVisibility e (isVisible env e)

computeCalculatedFieldValues :: GUIContext -> IO ()
computeCalculatedFieldValues context = do
   env <- varGet (environment context)
   _ <- mapM_ (setValueFromEnv env) (guiElements context)
   return ()

updateGUI :: GUIContext -> IO ()
updateGUI context = do
    setFieldsVisibility context
    computeCalculatedFieldValues context 
    return ()

setValueFromEnv :: E.Environment Value -> GUIElement -> IO ()
setValueFromEnv env (Text info _ control) = Control.Monad.when (readOnly info) $  set control [text := toDisplay(getValue (identifier info) env )]
setValueFromEnv env (Checkbox info _ control) = Control.Monad.when (readOnly info) $ set control [checked := aux (getValue (identifier info) env )]
    where aux (BoolValue x) = x
          aux _ = error "None boolean value supplied"

addHandler :: GUIContext -> GUIElement -> IO ()
addHandler ctx elem@(Checkbox info _ cBox) = do
     _ <- set cBox [ on command := (do
                         result <- getElementValue elem 
                         env <- varGet (environment ctx)
                         case result of
                           Left e -> error (show e) -- This should not happen with checkboxes
                           Right newValue -> do 
                             unless (readOnly info) $ varSet (environment ctx) (getNewEnv astForm (E.declare env fieldIdentifier newValue))
                             updateGUI ctx
                             return ())
                      ]
     return ()
    where fieldIdentifier = identifier info
          astForm = form ctx
addHandler ctx elem@(Text info _ textField) = do
  set textField [ on leave := (\_ -> do
                                           env <- varGet (environment ctx)
                                           result <- getElementValue elem 
                                           case result of
                                             Left err -> do
                                               print err
                                               unless (readOnly info) $ do
                                                 setValueFromEnv env elem
                                                 errorDialog (appFrame ctx) "Input Error" (show err)
                                               return ()
                                             Right newValue -> do 
                                               print (readOnly info)
                                               unless (readOnly info) $  varSet (environment ctx) (getNewEnv astForm (E.declare env fieldIdentifier newValue))
                                               updateGUI ctx
                                               return ())]
  return ()
   where fieldIdentifier = identifier info
         astForm = form ctx
