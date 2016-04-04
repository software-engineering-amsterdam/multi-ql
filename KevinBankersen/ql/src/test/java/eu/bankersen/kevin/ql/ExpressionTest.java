package eu.bankersen.kevin.ql;

import java.io.IOException;

import eu.bankersen.kevin.ql.form.formchecker.analytics.TypeChecker;
import eu.bankersen.kevin.ql.form.parser.ANTLRParseException;
import eu.bankersen.kevin.ql.form.parser.FormParser;
import junit.framework.TestCase;

public class ExpressionTest extends TestCase {

	public void test() throws ANTLRParseException, IOException {
		testLocation("tests\\expr1.form", 2);
		// testLocation("tests\\expr2.form", 4);
		// testLocation("tests\\expr3.form", 4);
	}

	private void testLocation(String location, int errors) throws ANTLRParseException, IOException {
		FormParser parser = new FormParser(location);
		TypeChecker check = new TypeChecker(parser.getForm());
		assertTrue(check.getErrors().size() == errors);
	}

}
