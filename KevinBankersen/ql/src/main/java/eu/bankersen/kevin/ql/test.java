package eu.bankersen.kevin.ql;

import eu.bankersen.kevin.ql.ast.values.IntegerValue;
import eu.bankersen.kevin.ql.ast.values.QLValue;

public class test {

    public static void main(String[] args) {
	QLValue a = new IntegerValue(2);
	QLValue b = new IntegerValue(8);
	QLValue c = new IntegerValue(2);

	System.out.println(b.multiply(a));
	System.out.println(a.add(b).multiply(c));

    }

}
