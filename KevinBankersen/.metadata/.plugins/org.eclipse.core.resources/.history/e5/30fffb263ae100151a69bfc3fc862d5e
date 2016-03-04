package eu.bankersen.kevin.ql.util.testing.new1;

import eu.bankersen.kevin.ql.ast.type.BooleanType;
import eu.bankersen.kevin.ql.ast.type.IntType;
import eu.bankersen.kevin.ql.ast.type.MoneyType;
import eu.bankersen.kevin.ql.ast.type.TypeObject;

public class Testing {

    public static void main(String[] args) {
	TypeObject intA = new IntType();
	TypeObject moneyA = new MoneyType();
	TypeObject boolA = new BooleanType();

	System.out.println(moneyA.compatible(intA));
	
	System.out.println(intA.compatible(moneyA));

	System.out.println(intA.parseValue("11234"));
    }

}
