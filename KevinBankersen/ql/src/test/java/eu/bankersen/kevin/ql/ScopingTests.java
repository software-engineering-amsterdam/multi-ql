package eu.bankersen.kevin.ql;

import java.io.File;
import java.io.IOException;

import eu.bankersen.kevin.ql.form.analyzer.scanners.Scoping;
import eu.bankersen.kevin.ql.form.parser.FormParser;
import eu.bankersen.kevin.ql.form.parser.ParseException;
import junit.framework.TestCase;

public class ScopingTests extends TestCase {

	public void test() throws ParseException, IOException {
		testLocation("tests\\scoping1.form", 2);
		testLocation("tests\\scoping2.form", 2);
	}

	private void testLocation(String location, int errors) throws ParseException, IOException {
		FormParser parser = new FormParser(new File(location));
		Scoping check = new Scoping(parser.getForm());
		assertTrue(check.getErrors().size() == errors);
	}

}
