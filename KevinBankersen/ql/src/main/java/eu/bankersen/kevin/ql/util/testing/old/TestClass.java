package eu.bankersen.kevin.ql.util.testing.old;

public final class TestClass {
    
    private TestClass() {
    }

    public static void main(String[] args) {
	ValueObject boolA = new BooleanType();
	ValueObject boolB = new BooleanType();
	
	ValueObject intA = new IntegerType();
	
	ValueObject moneyA = new MoneyType();
	
	System.out.println("boolA vs boolB");
	System.out.println(boolA.compatibleType(boolB) + "\n");
	
	System.out.println("boolA vs intA");
	System.out.println(boolA.sameType(intA) + "\n");
	
	System.out.println("intA vs moneyA");
	System.out.println(intA.compatibleType(moneyA) + "\n");
	
	System.out.println("boolA, true");
	boolA.setValue("false");
	System.out.println(boolA.getValue() + "\n");
	
	System.out.println("intA, false");
	intA.setValue("1235456342");
	System.out.println(intA.getValue() + "\n");
	
	
	

    }

}
