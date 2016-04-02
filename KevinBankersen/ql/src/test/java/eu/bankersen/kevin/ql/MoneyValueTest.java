package eu.bankersen.kevin.ql;

import java.math.BigDecimal;

import eu.bankersen.kevin.ql.form.ast.values.BooleanValue;
import eu.bankersen.kevin.ql.form.ast.values.NumberValue;
import eu.bankersen.kevin.ql.form.ast.values.MoneyValue;
import eu.bankersen.kevin.ql.form.ast.values.Value;
import junit.framework.TestCase;

public class MoneyValueTest extends TestCase {

	public void test() {

		Value money5 = new MoneyValue(5);
		Value money12 = new MoneyValue(12);
		Value int5 = new NumberValue(5);

		assertTrue(money5.add(money12).equals(new MoneyValue(new BigDecimal(17.00))));
		assertTrue(money5.subtract(money12).equals(new MoneyValue(new BigDecimal(-7.00))));

		assertTrue(int5.multiply(money5).equals(new MoneyValue(new BigDecimal(25.00))));
		assertTrue(money5.divide(int5).equals(new MoneyValue(new BigDecimal(1.00))));

		assertTrue(money5.negate().equals(new MoneyValue(new BigDecimal(-5.00))));
		assertTrue(money5.negate().absolute().equals(new MoneyValue(new BigDecimal(5.00))));

		assertTrue(money5.equal(money12).equals(new BooleanValue(false)));
		assertTrue(money5.equal(new MoneyValue(5)).equals(new BooleanValue(true)));

		assertTrue(money12.notEqual(money5).equals(new BooleanValue(true)));
		assertTrue(money5.notEqual(new MoneyValue(5)).equals(new BooleanValue(false)));

		assertTrue(money12.greater(money5).equals(new BooleanValue(true)));
		assertTrue(money12.greaterOrEqual(money5).equals(new BooleanValue(true)));
		assertTrue(money5.greaterOrEqual(money5).equals(new BooleanValue(true)));
		assertTrue(money5.greaterOrEqual(money12).equals(new BooleanValue(false)));
		assertTrue(money12.greaterOrEqual(money5).equals(new BooleanValue(true)));

		assertTrue(money12.lower(money5).equals(new BooleanValue(false)));
		assertTrue(money5.lower(money12).equals(new BooleanValue(true)));

		assertTrue(money5.lowerOrEqual(money12).equals(new BooleanValue(true)));
		assertTrue(money5.lowerOrEqual(money5).equals(new BooleanValue(true)));
	}
}
