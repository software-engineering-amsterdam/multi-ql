package eu.bankersen.kevin.ql;

import eu.bankersen.kevin.ql.ast.values.BooleanValue;
import eu.bankersen.kevin.ql.ast.values.IntegerValue;
import eu.bankersen.kevin.ql.ast.values.QLValue;
import junit.framework.TestCase;

public class IntegerValueTest extends TestCase {

    public void test() {

	QLValue int8 = new IntegerValue(8);
	QLValue int5 = new IntegerValue(5);

	assertTrue(int8.add(int5).equals(new IntegerValue(13)));
	assertTrue(int8.subtract(int5).equals(new IntegerValue(3)));

	assertTrue(int8.multiply(int5).equals(new IntegerValue(40)));
	assertTrue(int8.divide(int5).equals(new IntegerValue(2)));

	assertTrue(int8.negate().equals(new IntegerValue(-8)));
	assertTrue(int8.negate().absolute().equals(new IntegerValue(8)));

	assertTrue(int8.equal(int5).equals(new BooleanValue(false)));
	assertTrue(int8.equal(new IntegerValue(8)).equals(new BooleanValue(true)));

	assertTrue(int5.notEqual(int8).equals(new BooleanValue(true)));
	assertTrue(int8.notEqual(new IntegerValue(8)).equals(new BooleanValue(false)));

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
