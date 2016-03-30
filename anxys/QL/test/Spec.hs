import           Parsing
import Ast as A
import qualified AnnotatedAst as AA
import           Simplify
import           Test.Hspec
import           Text.ParserCombinators.Parsec as P
import           SemanticAnalysis
import           Data.Either
import           Location

testParseForm :: String -> String
testParseForm input =  case P.parse form "ql" input of
          Left err ->  "Error: " ++ show err
          Right val -> show $ simplify val

testParseSemanticCheck :: String -> AA.Form Location
testParseSemanticCheck input = case P.parse form "ql" input of
        Right val -> val
        Left e ->  error (show e)

testParser :: Parser a -> String -> Either ParseError a
testParser p = P.parse p "ql"

getRight :: Either a b -> b
getRight (Right x) = x
getRight (Left _) = error "Function should have returned a Right value"

canParse :: Either a b -> Bool
canParse = isRight 

main :: IO ()
main = hspec $
  describe "Lib. parse" $ do
  describe "Parsing Forms" $ do

    it "can parse an empty form" $
      testParseForm "form name { }" `shouldBe` show (Form "name" [])

    it "can parse a Form with two fields " $
      testParseForm "form taxOfficeExample { \"Display Text One\" idTest1: money \"DisplayText2\" \t idTest2: integer}" `shouldBe` show (Form "taxOfficeExample" [Field (SimpField FieldInfo {A.label = "Display Text One", A.id = "idTest1", A.fieldType = A.Money}),Field (SimpField FieldInfo {A.label = "DisplayText2",
 A.id = "idTest2", fieldType = A.Integer})])

    it "can parse a Form with an if statement" $
       simplify (getRight (testParser form "form name { if (false) {} }")) `shouldBe` Form "name" [If (Lit (BLit False)) []]

    it "can parse a Form with an if statement containing fields" $
      testParser form "form name { if (false) {\"Display Text One\" idTest1: money} }" `shouldSatisfy` canParse

  describe "Parsing Expressions" $ do
    it "can parse an addition expression" $
      testParser expr "1 + 2" `shouldSatisfy` canParse

    it "can parse a GT comparison" $
      testParser expr "1 > 2" `shouldSatisfy` canParse

    it "can parse a LT comparison" $
      testParser expr "1 < 2" `shouldSatisfy` canParse

    it "can parse an and expression" $
      testParser expr "true and false" `shouldSatisfy` canParse

    it "can parse an or expression" $
      testParser expr "true or false" `shouldSatisfy` canParse

    it "can parse a literal" $
      testParser expr "1" `shouldSatisfy` canParse

    it "can parse a var addition" $
      testParser expr "testValue + 2" `shouldSatisfy` canParse

    it "can parse string literal" $
      testParser expr "\"TestString\"" `shouldSatisfy` canParse

    it "can parse money literal" $
      testParser expr "12.22" `shouldSatisfy` canParse


  describe "Parsing control flow" $ do
    it "can parse an if statement" $
      testParser ifStmnt "if(false) {}" `shouldSatisfy` canParse

    it "can parse an if else statement" $
      testParser ifElseStmnt "if(true) {} else {}" `shouldSatisfy` canParse

    it "can parse control flow " $
      testParser conditional "if(true) {} else {}" `shouldSatisfy` canParse

    it "can parse control flow" $
      testParser conditional "if(true) {\n}" `shouldSatisfy` canParse


  describe "Parsing fields" $ do
    it "can parse a simple field" $
      testParser simpleField "\"Display Text One\" idTest1: money" `shouldSatisfy` canParse

    it "can parse a calculated field" $
      testParser calculatedField "\"Display Text One\" idTest1: money = 10.00" `shouldSatisfy` canParse

    it "can parse a calculated field with a boolean condition" $
      testParser calculatedField "\"Display Text One\" idTest1: boolean = true or true" `shouldSatisfy` canParse

    it "can parse a calculated field with a money addition" $
      testParser calculatedField "\"Display Text One\" idTest1: money = 11.00 + 12.00" `shouldSatisfy` canParse

    it "can parse a calculated field with a money addition including a variable identifier" $
      testParser calculatedField "\"Display Text One\" idTest1: money = testVar + 12.00" `shouldSatisfy` canParse


  describe "TypeChecker" $ do
    it "can determine the type of this expression (Integer)" $
     getType [] (getRight $ testParser expr "12 + 12") `shouldBe` Right A.Integer

    it "can determine the type of this expression (Boolean)" $
     getType [] (getRight $ testParser expr "true and true") `shouldBe` Right A.Boolean

    it "can determine the type of this expression (Boolean)" $
     getType [] (getRight $ testParser expr "22.00 + 22.00") `shouldBe` Right A.Money

    it "can determine the type of this expression (String)" $
     getType [] (getRight $ testParser expr "\"hel\" ++ \"lo\"") `shouldBe` Right A.String

    it "can determine the type of this expression (Money + Integer = Integer)" $
     getType [] (getRight $ testParser expr "22.00 + 1") `shouldBe` Right A.Money

    it "should not be able determine the type of this expression (Money + Boolean)" $
     show  (getType [] (getRight $ testParser expr "22.00 + true")) `shouldBe` "Left [TypeMismatch Boolean Money (location {(line: 1, column: 7) - (line: 1, column: 9)})]"

    it "should not be able determine the type of this expression (Integer && Boolean)" $
     show  (getType [] (getRight $ testParser expr "1 and 1")) `shouldBe` "Left [TypeMismatch Integer Integer (location {(line: 1, column: 3) - (line: 1, column: 7)})]"
     
    it "finds no type errors" $
     analyze (testParseSemanticCheck "form taxOfficeExample { \"Display Text One\" idTest1: money \"DisplayText2\" \t idTest2: integer}")  `shouldSatisfy` null.typeErrors

    it "finds redeclaration errors" $
     analyze (testParseSemanticCheck "form taxOfficeExample { \"DisplayText1\" idTest1: money \"DisplayText1\" \t idTest1: integer}")  `shouldSatisfy` not.null.duplicationErrors

    it "finds duplication identifier warnings" $
     analyze (testParseSemanticCheck "form taxOfficeExample { \"DisplayText1\" idTest1: integer \"DisplayText1\" \t idTest1: integer}")  `shouldSatisfy` not.null.duplicationWarnings

    it "finds type errors" $
     analyze (testParseSemanticCheck "form taxOfficeExample { \"DisplayText1\" idTest1: money = 1 + true}")  `shouldSatisfy` not.null.typeErrors

    it "finds cycles errors" $
     analyze (testParseSemanticCheck "form taxOfficeExample { \"DisplayText1\" idTest1: money = idTest2  \"DisplayText1\" \t idTest2: integer = idTest1  + 1}")  `shouldSatisfy` not.null.cycleErrors
