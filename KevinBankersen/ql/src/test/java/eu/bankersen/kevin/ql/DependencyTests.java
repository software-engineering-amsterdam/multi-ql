package eu.bankersen.kevin.ql;

import java.io.File;
import java.io.IOException;

import eu.bankersen.kevin.ql.form.analyzer.scanners.Dependencies;
import eu.bankersen.kevin.ql.form.parser.FormParser;
import eu.bankersen.kevin.ql.form.parser.ParseException;
import junit.framework.TestCase;

public class DependencyTests extends TestCase {

	public void test() throws ParseException, IOException {
		testLocation("tests\\dep1.form", 4);
		testLocation("tests\\dep2.form", 4);
		testLocation("tests\\dep3.form", 4);
	}

	private void testLocation(String location, int errors) throws ParseException, IOException {
		FormParser parser = new FormParser(new File(location));
		Dependencies check = new Dependencies(parser.getForm());
		assertTrue(check.getErrors().size() == errors);
	}

}
