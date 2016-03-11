package eu.bankersen.kevin.ql;

import eu.bankersen.kevin.ql.ast.object.value.BooleanValue;
import eu.bankersen.kevin.ql.ast.object.value.QLValue;
import junit.framework.TestCase;

public class BooleanValueTest extends TestCase {

    public void test() {
	QLValue trueB = new BooleanValue(true);
	QLValue falseB = new BooleanValue(false);

	assertTrue(falseB.absolute().equals(new BooleanValue(true)));

	assertTrue(falseB.absolute().equals(new BooleanValue(true)));
	assertTrue(falseB.negate().equals(new BooleanValue(false)));
	assertTrue(trueB.absolute().equals(new BooleanValue(true)));
	assertTrue(trueB.negate().equals(new BooleanValue(false)));

	assertTrue(trueB.or(falseB).equals(new BooleanValue(true)));
	assertTrue(falseB.or(trueB).equals(new BooleanValue(true)));
	assertTrue(falseB.or(falseB).equals(new BooleanValue(false)));
	assertTrue(trueB.or(trueB).equals(new BooleanValue(true)));

	assertTrue(trueB.and(falseB).equals(new BooleanValue(false)));
	assertTrue(falseB.and(trueB).equals(new BooleanValue(false)));
	assertTrue(falseB.and(falseB).equals(new BooleanValue(false)));
	assertTrue(trueB.and(trueB).equals(new BooleanValue(true)));

	assertTrue(trueB.equal(falseB).equals(new BooleanValue(false)));
	assertTrue(falseB.equal(trueB).equals(new BooleanValue(false)));
	assertTrue(falseB.equal(falseB).equals(new BooleanValue(true)));
	assertTrue(trueB.equal(trueB).equals(new BooleanValue(true)));

	assertTrue(trueB.not().equals(new BooleanValue(false)));
	assertTrue(falseB.not().equals(new BooleanValue(true)));
    }

}