package eu.bankersen.kevin.ql;

import eu.bankersen.kevin.ql.form.ast.values.BooleanValue;
import eu.bankersen.kevin.ql.form.ast.values.TextValue;
import eu.bankersen.kevin.ql.form.ast.values.Value;
import junit.framework.TestCase;

public class StringValueTest extends TestCase {

	public void test() {
		Value blablabla = new TextValue("blablabla");
		Value tralala = new TextValue("tralala");

		assertTrue(blablabla.equal(tralala).equals(new BooleanValue(false)));
		assertTrue(blablabla.equal(blablabla).equals(new BooleanValue(true)));

		assertTrue(blablabla.notEqual(tralala).equals(new BooleanValue(true)));
		assertTrue(blablabla.notEqual(blablabla).equals(new BooleanValue(false)));

	}

}
