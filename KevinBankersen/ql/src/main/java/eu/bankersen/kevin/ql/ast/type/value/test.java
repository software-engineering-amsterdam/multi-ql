package eu.bankersen.kevin.ql.ast.type.value;

public class test {

    public static void main(String[] args) {
	QLObject object1 = new IntegerObject();
	object1.valueOf(4);
	
	QLObject object2 = new MoneyObject();
	object2.valueOf(8);
	
	System.out.println(object2.divide(object1));
	
	
	QLObject object3 = new IntegerObject();
	object3.valueOf("1234");
	
	QLObject object4 = new MoneyObject();
	object4.valueOf("5678");
	
	System.out.println(object3.multiply(object4));
	
	
	QLObject object5 = new StringObject();
	object5.valueOf("1234");
	
	QLObject object6 = new StringObject();
	object6.valueOf("1234");
	
	System.out.println(object5.equal(object6));
	
	
	
	QLObject object7 = new BooleanObject();
	object7.valueOf("1234");
	
	QLObject object8 = new BooleanObject();
	object8.valueOf("1234");
	
	System.out.println(object8.equal(object8));
	
	

    }

}