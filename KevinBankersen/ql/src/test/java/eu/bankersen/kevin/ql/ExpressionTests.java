package eu.bankersen.kevin.ql;

import java.io.File;
import java.io.IOException;

import eu.bankersen.kevin.ql.form.analyzer.scanners.TypeChecker;
import eu.bankersen.kevin.ql.form.parser.FormParser;
import eu.bankersen.kevin.ql.form.parser.ParseException;
import junit.framework.TestCase;

public class ExpressionTests extends TestCase {

	public void test() throws ParseException, IOException {
		testLocation("tests\\expr1.form", 2);
		testLocation("tests\\expr2.form", 2);
		testLocation("tests\\expr3.form", 2);
	}

	private void testLocation(String location, int errors) throws ParseException, IOException {
		FormParser parser = new FormParser(new File(location));
		TypeChecker check = new TypeChecker(parser.getForm());
		assertTrue(check.getErrors().size() == errors);
	}

}
