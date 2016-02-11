import           Ast        as A
import   TypeChecker
import           Lib
import           Test.Hspec

main :: IO ()
main = hspec $
  describe "Lib. parse" $ do
  describe "Parsing Forms" $ do

    it "can parse an empty form" $
      tParse form "form name { }" `shouldBe` show (Form "name" [])

    it "can parse a Form with two fields " $
      tParse form "form taxOfficeExample { \"Display Text One\" idTest1: money \"DisplayText2\" \t idTest2: integer}" `shouldBe` show (Form "taxOfficeExample" [Field SimpleField {A.label = "Display Text One", A.id = "idTest1", fieldType = Money}, Field SimpleField {A.label = "DisplayText2", A.id = "idTest2", fieldType = Integer}])

    it "can parse a Form with an if statement" $
      tParse form "form name { if (false) {} }" `shouldBe` show (Form "name" [If (Lit (BLit False)) []])

    it "can parse a Form with an if statement containing fields" $
      tParse form "form name { if (false) {\"Display Text One\" idTest1: money} }" `shouldBe` show (Form "name" [If (Lit (BLit False)) [Field  SimpleField {A.label = "Display Text One", A.id = "idTest1", fieldType = Money }]])


  describe "Parsing Expressions" $ do
    it "can parse an addition expression" $
      tParse expr "1 + 2" `shouldBe` show (BinOp Add (Lit $ ILit 1) (Lit $ ILit 2))

    it "can parse a GT comparison" $
      tParse expr "1 > 2" `shouldBe` show (BinOp A.GT (Lit $ ILit 1) (Lit $ ILit 2))

    it "can parse a LT comparison" $
      tParse expr "1 < 2" `shouldBe` show (BinOp A.LT (Lit $ ILit 1) (Lit $ ILit 2))

    it "can parse an and expression" $
      tParse expr "true and false" `shouldBe` show (BinOp And (Lit $ BLit True) (Lit $ BLit False))

    it "can parse an or expression" $
      tParse expr "true or false" `shouldBe` show (BinOp Or (Lit $ BLit True) (Lit $ BLit False))

    it "can parse a literal" $
      tParse expr "1" `shouldBe` show (Lit $ ILit 1)

    it "can parse a var addition" $
      tParse expr "testValue + 2" `shouldBe` show (BinOp Add (Var "testValue") (Lit $ ILit 2))

    it "can parse string literal" $
      tParse expr "\"TestString\"" `shouldBe` show (Lit $ SLit "TestString")

    it "can parse money literal" $
      tParse expr "12.22" `shouldBe` show (Lit $ MLit 12.22)


  describe "Parsing control flow" $ do
    it "can parse an if statement" $
      tParse ifStmnt "if(false) {}" `shouldBe` show (If (Lit $ BLit False) [])

    it "can parse an if else statement" $
      tParse ifElseStmnt "if(true) {} else {}" `shouldBe` show (IfElse (Lit $ BLit True) [] [])

    it "can parse control flow parsing" $
      tParse conditional "if(true) {} else {}" `shouldBe` show (IfElse (Lit $ BLit True) [] [])

    it "can parse control flow parsing" $
      tParse conditional "if(true) {}" `shouldBe` show (If (Lit $ BLit True) [])


  describe "Parsing fields" $ do
    it "can parse a simple field" $
      tParse simpleField "\"Display Text One\" idTest1: money" `shouldBe` show SimpleField {A.label = "Display Text One", A.id = "idTest1", fieldType = Money}

    it "can parse a calculated field" $
      tParse calculatedField "\"Display Text One\" idTest1: money = 10.00" `shouldBe` show CalculatedField {A.label = "Display Text One", A.id = "idTest1", fieldType = Money, A.exp = Lit $ MLit 10.00}

    it "can parse a calculated field with a boolean condition" $
      tParse calculatedField "\"Display Text One\" idTest1: bool = true or true" `shouldBe` show CalculatedField {A.label = "Display Text One", A.id = "idTest1", fieldType = Boolean, A.exp = BinOp Or (Lit $ BLit True) (Lit $ BLit True)}

    it "can parse a calculated field with a money addition" $
      tParse calculatedField "\"Display Text One\" idTest1: money = 11.00 + 12.00" `shouldBe` show CalculatedField {A.label = "Display Text One", A.id = "idTest1", fieldType = Money, A.exp = BinOp Add (Lit $ MLit 11.00) (Lit $ MLit 12.00)}

    it "can parse a calculated field with a money addition including a variable identifier" $
      tParse calculatedField "\"Display Text One\" idTest1: money = testVar + 12.00" `shouldBe` show CalculatedField {A.label = "Display Text One", A.id = "idTest1", fieldType = Money, A.exp = BinOp Add (Var "testVar") (Lit $ MLit 12.00)}


  describe "TypeChecker" $ do
    it "can type determine the type of this expression (Integer)" $
     getType [] (BinOp Add (Lit (ILit 12)) (Lit (ILit 12))) `shouldBe` Just Integer

    it "can type determine the type of this expression (Boolean)" $
     getType [] (BinOp And (Lit (BLit True)) (Lit (BLit True))) `shouldBe` Just Boolean

    it "can type determine the type of this expression (Boolean)" $
     getType [] (BinOp Add (Lit (MLit 22.00)) (Lit (MLit 22.00))) `shouldBe` Just Money

    it "can not type determine the type of this expression (Money + Integer)" $
     getType [] (BinOp Add (Lit (MLit 22.00)) (Lit (ILit 1))) `shouldBe` Nothing 

    it "can not type determine the type of this expression (Money + Boolean)" $
     getType [] (BinOp Add (Lit (MLit 22.00)) (Lit (BLit False))) `shouldBe` Nothing 
