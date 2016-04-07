package eu.bankersen.kevin.ql;

import java.math.BigDecimal;

import eu.bankersen.kevin.ql.form.ast.values.MoneyValue;
import eu.bankersen.kevin.ql.form.ast.values.NumberValue;
import eu.bankersen.kevin.ql.form.ast.values.TextValue;
import eu.bankersen.kevin.ql.form.ast.values.Value;
import junit.framework.TestCase;

public class MixedValueTest extends TestCase {

	public void test() {
		Value int2 = new NumberValue(2);
		Value money7 = new MoneyValue(7);
		Value blablabla = new TextValue("blablabla\t");

		assertTrue(blablabla.add(int2).equals(new TextValue("blablabla\t2")));

		assertTrue(blablabla.add(money7).equals(new TextValue("blablabla\t€7.00")));

		assertTrue(money7.divide(int2).equals(new MoneyValue(new BigDecimal(3.5))));

	}
}
