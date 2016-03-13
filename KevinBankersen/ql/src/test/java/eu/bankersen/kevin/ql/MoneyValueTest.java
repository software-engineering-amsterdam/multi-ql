package eu.bankersen.kevin.ql;

import java.math.BigDecimal;

import eu.bankersen.kevin.ql.ast.values.BooleanValue;
import eu.bankersen.kevin.ql.ast.values.IntegerValue;
import eu.bankersen.kevin.ql.ast.values.MoneyValue;
import eu.bankersen.kevin.ql.ast.values.QLValue;
import junit.framework.TestCase;

public class MoneyValueTest extends TestCase {

    public void test() {

	QLValue money5 = new MoneyValue(5);
	QLValue money12 = new MoneyValue(12);
	QLValue int5 = new IntegerValue(5);

	assertTrue(money5.add(money12).equals(new MoneyValue(new BigDecimal(17.00))));
	assertTrue(money5.subtract(money12).equals(new MoneyValue(new BigDecimal(-7.00))));

	assertTrue(money5.multiply(int5).equals(new MoneyValue(new BigDecimal(25.00))));
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
