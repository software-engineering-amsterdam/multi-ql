module GUIContext where

import Graphics.UI.WX
import Value
import Identifier
import GUIElement
import GUIError
import Ast as A
import Data.Maybe
import Interpreter
import qualified Environment as E
import Control.Monad
import Control.Monad.Reader
import Prelude hiding (elem)

data GUIContext = GUIContext {appFrame    :: Frame (),
                              form        :: A.Form,
                              guiElements :: [GUIElement],
                              environment :: Var (E.Environment Value)
                             }

initializeGUIContext :: Frame () ->  A.Form -> IO GUIContext
initializeGUIContext f astForm = do
  envRef  <- varCreate initialEnv
  elems   <- mapM (uncurry(createElem (ctx [] envRef))) questionFields
  context <- createContext elems envRef
  mapM_ (addHandler context) elems
  updateGUI context
  return context
  where initialEnv = case exec astForm E.emptyEnv of
          Left x -> error x
          Right r -> r
        questionFields = A.fieldConditionalDependencies astForm
        ctx = GUIContext f astForm
        createContext elems env = return (ctx elems env)

createElem :: GUIContext -> A.Field -> [A.Expr] -> IO GUIElement
createElem ctx (SimpField info) deps = createElem' ctx info True deps
createElem ctx (CalcField info _) deps = createElem' ctx info False deps

setValueFromEnv :: E.Environment Value -> GUIElement -> IO ()
setValueFromEnv env (Text info _ control) = Control.Monad.when (readOnly info) $  set control [text := toDisplay(getValue (info) env )]
setValueFromEnv env (Checkbox info _ control) = Control.Monad.when (readOnly info) $ set control [checked := aux (getValue (info) env )]
    where aux (BoolValue x) = x
          aux _ = error "None boolean value supplied"

getNewEnv :: A.Form -> E.Environment Value -> E.Environment Value
getNewEnv astForm env = case exec astForm env of
           Left x  -> error x
           Right r -> r

getValue :: ElemInfo -> E.Environment Value -> Value
getValue e env = fromMaybe (defaultVal (valueType e)) (E.lookup (identifier e) env)

setFieldsVisibility :: GUIContext -> IO ()
setFieldsVisibility context = do
  env <- varGet (environment context)
  _ <- mapM_ (setVis env) (guiElements context)
  _ <- addToLayout (appFrame context) (guiElements context)
  return ()
  where isVisible env (Text info _ _) = evalConditions env (conditions info) 
        isVisible env (Checkbox info _ _) = evalConditions env ( conditions info) 
        evalConditions env conds = andValues (map (evalExpr env) conds) 
        setVis env e = setVisibility e (isVisible env e)

computeCalculatedFieldValues :: GUIContext -> IO ()
computeCalculatedFieldValues context = do
   env <- varGet (environment context)
   _ <- mapM_ (setValueFromEnv env) (guiElements context)
   return ()

updateGUI :: GUIContext -> IO ()
updateGUI context = do
    computeCalculatedFieldValues context 
    setFieldsVisibility context
    return ()

type TestCtx a = ReaderT GUIContext IO a

test :: TestCtx String
test = do
  ctx <- asks (varGet.environment)
 -- liftIO $ print.show  ctx
  return ("fd")

createElem':: GUIContext -> A.FieldInfo -> Bool -> [A.Expr] -> IO  GUIElement
createElem' ctx info isNotReadOnly deps = case fieldType info of
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
                    return (getValue (eInfo) env)
           f = appFrame ctx
           eInfo = createElemInfo info isReadOnly deps
           standardControl = do
               qLabel <- createStaticText f (A.label info)
               val <- getVal
               textControl <- createTextControl f val
               configureReadOnly $ Text eInfo qLabel textControl
           configureReadOnly = setReadOnly isReadOnly
           isReadOnly = not isNotReadOnly

updateGUIContext :: GUIContext -> Identifier -> Value -> IO ()
updateGUIContext ctx fieldIdentifier newValue = do
   oldEnv <- varGet envRef 
   varSet envRef (getNewEnv astForm (E.declare oldEnv fieldIdentifier newValue))
   return ()
 where astForm = form ctx
       envRef = environment ctx

handleNewValue :: GUIContext -> Bool -> Identifier -> Value -> IO ()
handleNewValue ctx isReadOnly fieldIdentifier newValue = do
  unless isReadOnly $ updateGUIContext ctx fieldIdentifier newValue 
  updateGUI ctx
  return ()

addHandler :: GUIContext -> GUIElement -> IO ()
addHandler ctx elem@(Checkbox info _ cBox) = do
    set cBox [ on command := 
                    (
                     do
                       result <- getElementValue elem 
                       case result of
                         Left e -> error (show e) -- This should not happen with checkboxes
                         Right newValue -> handleNewValue ctx (readOnly info)  fieldIdentifier newValue 
                    )
             ]
    return ()
    where fieldIdentifier = identifier info
addHandler ctx elem@(Text info _ textField) = do
  set textField [ on leave := 
                 (
                  \_ -> 
                    do
                      env <- varGet (environment ctx)
                      result <- getElementValue elem 
                      case result of
                        Left err -> 
                          unless (readOnly info) $ do
                            setValueFromEnv env elem
                            showUserInputErrorDialog (appFrame ctx) (show err)
                            return ()
                        Right newValue -> handleNewValue ctx (readOnly info)  fieldIdentifier newValue 
                  )
                 ]
  return ()
    where fieldIdentifier = identifier info
