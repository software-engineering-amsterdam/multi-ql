import           Ast                           as A
import           Lib
import           Test.Hspec
import           Text.ParserCombinators.Parsec as P
import           TypeChecker

tParse :: (Show a)  => Parser a -> String -> String
tParse p input =  case P.parse p "ql" input of
          Left err ->  "Error: " ++ show err
          Right val -> show val

tParse' :: Parser a -> String -> Either ParseError a
tParse' p = P.parse p "ql"

typeCheckHelper :: Either ParseError Expr -> Expr
typeCheckHelper (Right a) = a
typeCheckHelper _ = undefined -- Should never happen

canParse :: Either ParseError a -> Bool
canParse (Left _) = False
canParse (Right _) = True


main :: IO ()
main = hspec $
  describe "Lib. parse" $ do
  describe "Parsing Forms" $ do

    it "can parse an empty form" $
      tParse form "form name { }" `shouldBe` show (Form "name" [])

    it "can parse a Form with two fields " $
      tParse' form "form taxOfficeExample { \"Display Text One\" idTest1: money \"DisplayText2\" \t idTest2: integer}" `shouldSatisfy` canParse

    it "can parse a Form with an if statement" $
      tParse' form "form name { if (false) {} }" `shouldSatisfy` canParse

    it "can parse a Form with an if statement containing fields" $
      tParse' form "form name { if (false) {\"Display Text One\" idTest1: money} }" `shouldSatisfy` canParse


  describe "Parsing Expressions" $ do
    it "can parse an addition expression" $
      tParse' expr "1 + 2" `shouldSatisfy` canParse

    it "can parse a GT comparison" $
      tParse' expr "1 > 2" `shouldSatisfy` canParse

    it "can parse a LT comparison" $
      tParse' expr "1 < 2" `shouldSatisfy` canParse

    it "can parse an and expression" $
      tParse' expr "true and false" `shouldSatisfy` canParse

    it "can parse an or expression" $
      tParse' expr "true or false" `shouldSatisfy` canParse

    it "can parse a literal" $
      tParse' expr "1" `shouldSatisfy` canParse

    it "can parse a var addition" $
      tParse' expr "testValue + 2" `shouldSatisfy` canParse

    it "can parse string literal" $
      tParse' expr "\"TestString\"" `shouldSatisfy` canParse

    it "can parse money literal" $
      tParse' expr "12.22" `shouldSatisfy` canParse


  describe "Parsing control flow" $ do
    it "can parse an if statement" $
      tParse' ifStmnt "if(false) {}" `shouldSatisfy` canParse

    it "can parse an if else statement" $
      tParse' ifElseStmnt "if(true) {} else {}" `shouldSatisfy` canParse

    it "can parse control flow " $
      tParse' conditional "if(true) {} else {}" `shouldSatisfy` canParse

    it "can parse control flow" $
      tParse' conditional "if(true) {\n}" `shouldSatisfy` canParse


  describe "Parsing fields" $ do
    it "can parse a simple field" $
      tParse' simpleField "\"Display Text One\" idTest1: money" `shouldSatisfy` canParse

    it "can parse a calculated field" $
      tParse' calculatedField "\"Display Text One\" idTest1: money = 10.00" `shouldSatisfy` canParse

    it "can parse a calculated field with a boolean condition" $
      tParse' calculatedField "\"Display Text One\" idTest1: bool = true or true" `shouldSatisfy` canParse

    it "can parse a calculated field with a money addition" $
      tParse' calculatedField "\"Display Text One\" idTest1: money = 11.00 + 12.00" `shouldSatisfy` canParse

    it "can parse a calculated field with a money addition including a variable identifier" $
      tParse' calculatedField "\"Display Text One\" idTest1: money = testVar + 12.00" `shouldSatisfy` canParse


  describe "TypeChecker" $ do
    it "can determine the type of this expression (Integer)" $
     getType [] (typeCheckHelper $ tParse' expr "12 + 12") `shouldBe` Right Integer

    it "can determine the type of this expression (Boolean)" $
     getType [] (typeCheckHelper $ tParse' expr "true and true") `shouldBe` Right Boolean

    it "can determine the type of this expression (Boolean)" $
     getType [] (typeCheckHelper $ tParse' expr "22.00 + 22.00") `shouldBe` Right Money

    it "can determine the type of this expression (String)" $
     getType [] (typeCheckHelper $ tParse' expr "\"hel\" ++ \"lo\"") `shouldBe` Right String

    it "can determine the type of this expression (Money + Integer = Integer)" $
     getType [] (typeCheckHelper $ tParse' expr "22.00 + 1") `shouldBe` Right Integer

    it "should not be able determine the type of this expression (Money + Boolean)" $
     show  (getType [] (typeCheckHelper $ tParse' expr "22.00 + true")) `shouldBe` "Left [TypeMismatch Boolean Money (Region {start = \"ql\" (line 1, column 7), end = \"ql\" (line 1, column 9)})]"

    it "should not be able determine the type of this expression (Interger && Boolean)" $
     show  (getType [] (typeCheckHelper $ tParse' expr "1 and 1")) `shouldBe` "Left [TypeMismatch Integer Integer (Region {start = \"ql\" (line 1, column 3), end = \"ql\" (line 1, column 7)})]"
