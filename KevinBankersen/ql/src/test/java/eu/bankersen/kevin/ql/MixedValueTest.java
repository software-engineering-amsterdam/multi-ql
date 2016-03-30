package eu.bankersen.kevin.ql;

import java.math.BigDecimal;

import eu.bankersen.kevin.ql.form.ast.values.IntegerValue;
import eu.bankersen.kevin.ql.form.ast.values.MoneyValue;
import eu.bankersen.kevin.ql.form.ast.values.StringValue;
import eu.bankersen.kevin.ql.form.ast.values.Value;
import junit.framework.TestCase;

public class MixedValueTest extends TestCase {

    public void test() {
	Value int2 = new IntegerValue(2);
	Value money7 = new MoneyValue(7);
	Value blablabla = new StringValue("blablabla\t");

	assertTrue(blablabla.add(int2).equals(new StringValue("blablabla\t2")));

	assertTrue(blablabla.add(money7).equals(new StringValue("blablabla\t€7.00")));

	assertTrue(money7.divide(int2).equals(new MoneyValue(new BigDecimal(3.5))));

    }
}
