module GUIElement where

import Graphics.UI.WX
import Ast
import Identifier
import Value

data ElemInfo = ElemInfo { identifier   :: String
                         , label        :: String
                         , valueType    :: FieldType
                         , readOnly     :: Bool
                         }

data GUIElement = Text ElemInfo  (StaticText ()) (TextCtrl () )
                | Checkbox ElemInfo (StaticText ()) (CheckBox ())

data UserInputError = UserInputError FieldType

instance Show UserInputError 
  where show (UserInputError x) = "Invalid value. Expected " ++ show x

createElemInfo :: FieldInfo -> Bool -> ElemInfo
createElemInfo fieldInfo  = ElemInfo (Ast.id fieldInfo) (Ast.label fieldInfo) (Ast.fieldType fieldInfo)

addToLayout :: Frame a -> [GUIElement] -> IO ()
addToLayout f ls = do
   set f [layout := grid 10 10 (foldr (\x m -> elements x:m) [] ls)]
   return ()
   where elements (Text _ t control) = [widget t, widget control]
         elements (Checkbox _ t control) = [widget t, widget control]

defaultFont :: FontStyle
defaultFont = fontFixed

setReadOnly :: Bool -> GUIElement -> IO GUIElement
setReadOnly b (Text info sText control)  = return (Text newInfo sText control)
  where newInfo = ElemInfo (identifier info) (GUIElement.label info) (valueType info) b
setReadOnly b (Checkbox info sText control)  = return (Checkbox newInfo sText control)
    where newInfo = ElemInfo (identifier info) (GUIElement.label info) (valueType info) b

getElementValue :: GUIElement -> IO (Either UserInputError Value)
getElementValue (Text info _ control) = do
  val <- get control text
  if validate vType (fromDisplay' val)
    then return (Right (fromDisplay' val))
  else
   return (Left (UserInputError vType))
     where fromDisplay' = fromDisplay (valueType info) 
           vType = valueType info
getElementValue (Checkbox _ _ control) = do
  val <- get control checked
  return (Right (BoolValue val))

validate :: FieldType -> Value -> Bool
validate x = haveSameValueType (defaultVal x)  

setVisibility' :: (Visible a, Visible b) => a -> b -> Bool -> IO ()
setVisibility' c1 c2 b = do
  _ <- set c1 [visible := b]
  _ <- set c2 [visible := b]
  return ()

createStaticText :: Frame a -> String -> IO (StaticText ())
createStaticText f questionLabel = staticText f [text := questionLabel]

createTextControl :: Frame a -> Value -> IO (TextCtrl ())
createTextControl f v = textEntry f [font:= defaultFont, text := toDisplay v]

createCheckBox :: Frame a -> Value -> IO (CheckBox ())
createCheckBox f (BoolValue v) = checkBox f [checked := v ]
createCheckBox _ _ = error "None boolean value supplied"

setVisibility :: GUIElement -> Bool -> IO ()
setVisibility (Text _ s c) b = setVisibility' c s b
setVisibility (Checkbox _ s c) b = setVisibility' c s b
