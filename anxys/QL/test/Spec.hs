import Test.Hspec
import Lib
import Ast as A

simpleForm :: String
simpleForm = "form name { }"

ifForm :: String
ifForm = "form name { if (false) {} }"

ifFormWithField :: String
ifFormWithField = "form name { if (false) {\"Display Text One\" idTest1: money} }"

testData :: String
testData = "form taxOfficeExample { \n \"Display Text One\"\n \t idTest1: money \n \"DisplayText2\"\n \t idTest2: integer \n}"

main :: IO ()
main = hspec $
  describe "Lib. parse" $ do

    it "can parse an empty form" $
      someFunc simpleForm `shouldBe` show (Form "name" [])

    it "can parse a Form with two fields " $
      someFunc testData `shouldBe` show (Form "taxOfficeExample" [Field {A.label = "Display Text One", A.id = "idTest1", fieldType = Money, value = "None"},Field {A.label = "DisplayText2", A.id = "idTest2", fieldType = Integer, value = "None"}])

    it "can parse a Form with an if statement" $
      someFunc ifForm `shouldBe` show (Form "name" [If (Lit (BLit False)) []])

    it "can parse a Form with an if statement containing fields" $
      someFunc ifFormWithField `shouldBe` show (Form "name" [If (Lit (BLit False)) [Field {A.label = "Display Text One", A.id = "idTest1", fieldType = Money, value = "None"}]])
