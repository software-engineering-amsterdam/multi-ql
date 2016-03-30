package eu.bankersen.kevin.ql;

import eu.bankersen.kevin.ql.form.ast.values.BooleanValue;
import eu.bankersen.kevin.ql.form.ast.values.Value;
import junit.framework.TestCase;

public class BooleanValueTest extends TestCase {

    public void test() {
	Value trueB = new BooleanValue(true);
	Value falseB = new BooleanValue(false);

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
