package eu.bankersen.kevin.ql;

import eu.bankersen.kevin.ql.form.ast.values.BooleanValue;
import eu.bankersen.kevin.ql.form.ast.values.NumberValue;
import eu.bankersen.kevin.ql.form.ast.values.Value;
import junit.framework.TestCase;

public class IntegerValueTest extends TestCase {

	public void test() {

		Value int8 = new NumberValue(8);
		Value int5 = new NumberValue(5);

		assertTrue(int8.add(int5).equals(new NumberValue(13)));
		assertTrue(int8.subtract(int5).equals(new NumberValue(3)));

		assertTrue(int8.multiply(int5).equals(new NumberValue(40)));
		assertTrue(int8.divide(int5).equals(new NumberValue(2)));

		assertTrue(int8.negate().equals(new NumberValue(-8)));
		assertTrue(int8.negate().absolute().equals(new NumberValue(8)));

		assertTrue(int8.equal(int5).equals(new BooleanValue(false)));
		assertTrue(int8.equal(new NumberValue(8)).equals(new BooleanValue(true)));

		assertTrue(int5.notEqual(int8).equals(new BooleanValue(true)));
		assertTrue(int8.notEqual(new NumberValue(8)).equals(new BooleanValue(false)));

		assertTrue(int8.greater(int5).equals(new BooleanValue(true)));
		assertTrue(int8.greaterOrEqual(int5).equals(new BooleanValue(true)));
		assertTrue(int8.greaterOrEqual(int8).equals(new BooleanValue(true)));
		assertTrue(int5.greaterOrEqual(int8).equals(new BooleanValue(false)));
		assertTrue(int5.greaterOrEqual(int5).equals(new BooleanValue(true)));

		assertTrue(int8.lower(int5).equals(new BooleanValue(false)));
		assertTrue(int5.lower(int8).equals(new BooleanValue(true)));

		assertTrue(int8.lowerOrEqual(int5).equals(new BooleanValue(false)));
		assertTrue(int5.lowerOrEqual(int8).equals(new BooleanValue(true)));

	}

}
