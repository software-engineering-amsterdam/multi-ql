package eu.bankersen.kevin.ql;

import eu.bankersen.kevin.ql.ast.values.BooleanValue;
import eu.bankersen.kevin.ql.ast.values.QLValue;
import eu.bankersen.kevin.ql.ast.values.StringValue;
import junit.framework.TestCase;

public class StringValueTest extends TestCase {

    public void test() {
	QLValue blablabla = new StringValue("blablabla");
	QLValue tralala = new StringValue("tralala");

	assertTrue(blablabla.and(tralala).equals(new BooleanValue(false)));
	assertTrue(blablabla.and(blablabla).equals(new BooleanValue(true)));

	assertTrue(blablabla.equal(tralala).equals(new BooleanValue(false)));
	assertTrue(blablabla.equal(blablabla).equals(new BooleanValue(true)));

	assertTrue(blablabla.notEqual(tralala).equals(new BooleanValue(true)));
	assertTrue(blablabla.notEqual(blablabla).equals(new BooleanValue(false)));

	assertTrue(tralala.lower(blablabla).equals(new BooleanValue(true)));
	assertTrue(blablabla.lower(tralala).equals(new BooleanValue(false)));
	assertTrue(blablabla.lower(blablabla).equals(new BooleanValue(false)));

	assertTrue(blablabla.lowerOrEqual(tralala).equals(new BooleanValue(false)));
	assertTrue(tralala.lowerOrEqual(blablabla).equals(new BooleanValue(true)));
	assertTrue(blablabla.lowerOrEqual(blablabla).equals(new BooleanValue(true)));

	assertTrue(blablabla.greater(tralala).equals(new BooleanValue(true)));
	assertTrue(blablabla.greater(blablabla).equals(new BooleanValue(false)));

	assertTrue(blablabla.greaterOrEqual(tralala).equals(new BooleanValue(true)));
	assertTrue(tralala.greaterOrEqual(blablabla).equals(new BooleanValue(false)));
	assertTrue(blablabla.greaterOrEqual(blablabla).equals(new BooleanValue(true)));

    }

}
