package eu.bankersen.kevin.ql;

import eu.bankersen.kevin.ql.form.ast.types.BooleanType;
import eu.bankersen.kevin.ql.form.ast.types.Type;
import eu.bankersen.kevin.ql.form.ast.values.BooleanValue;
import eu.bankersen.kevin.ql.form.ast.values.Value;
import junit.framework.TestCase;

public class BooleanTest extends TestCase {

	public void test() {
		Type bool = new BooleanType();
		Value trueB = new BooleanValue(true);
		Value falseB = new BooleanValue(false);

		// Absolute
		assertTrue(bool.absolute().equals(bool));
		assertTrue(falseB.absolute().equals(trueB));
		assertTrue(trueB.absolute().equals(trueB));

		// Negate
		assertTrue(bool.negate().equals(bool));
		assertTrue(falseB.negate().equals(falseB));
		assertTrue(trueB.negate().equals(falseB));

		// Or
		assertTrue(bool.or(bool).equals(bool));
		assertTrue(trueB.or(falseB).equals(trueB));
		assertTrue(falseB.or(trueB).equals(trueB));
		assertTrue(falseB.or(falseB).equals(falseB));
		assertTrue(trueB.or(trueB).equals(trueB));

		// And
		assertTrue(bool.and(bool).equals(bool));
		assertTrue(trueB.and(falseB).equals(falseB));
		assertTrue(falseB.and(trueB).equals(falseB));
		assertTrue(falseB.and(falseB).equals(falseB));
		assertTrue(trueB.and(trueB).equals(trueB));

		// Equal
		assertTrue(bool.equal(bool).equals(bool));
		assertTrue(trueB.equal(falseB).equals(falseB));
		assertTrue(falseB.equal(trueB).equals(falseB));
		assertTrue(falseB.equal(falseB).equals(trueB));
		assertTrue(trueB.equal(trueB).equals(trueB));

		// Not
		assertTrue(bool.not().equals(bool));
		assertTrue(trueB.not().equals(falseB));

		assertTrue(falseB.not().equals(trueB));
	}

}
