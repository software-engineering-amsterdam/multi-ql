package eu.bankersen.kevin.ql;

import java.math.BigDecimal;

import eu.bankersen.kevin.ql.ast.values.IntegerValue;
import eu.bankersen.kevin.ql.ast.values.MoneyValue;
import eu.bankersen.kevin.ql.ast.values.QLValue;
import eu.bankersen.kevin.ql.ast.values.StringValue;
import eu.bankersen.kevin.ql.ast.values.UndifinedValue;
import junit.framework.TestCase;

public class MixedValueTest extends TestCase {

    public void test() {
	QLValue int2 = new IntegerValue(2);
	QLValue money7 = new MoneyValue(7);
	QLValue blablabla = new StringValue("blablabla\t");

	assertTrue(int2.add(blablabla).equals(new UndifinedValue()));
	assertTrue(blablabla.add(int2).equals(new StringValue("blablabla\t2")));

	assertTrue(money7.add(blablabla).equals(new UndifinedValue()));
	assertTrue(blablabla.add(money7).equals(new StringValue("blablabla\t€7.00")));

	assertTrue(int2.multiply(money7).equals(new UndifinedValue()));
	assertTrue(money7.multiply(int2).equals(new MoneyValue(new BigDecimal(14.00))));

	assertTrue(int2.divide(money7).equals(new UndifinedValue()));
	assertTrue(money7.divide(int2).equals(new MoneyValue(new BigDecimal(3.5))));

    }
}
