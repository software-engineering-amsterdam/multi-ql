package eu.bankersen.kevin.ql;

import java.io.IOException;

import eu.bankersen.kevin.ql.form.analyzer.scanners.Dependencies;
import eu.bankersen.kevin.ql.form.parser.ANTLRParseException;
import eu.bankersen.kevin.ql.form.parser.FormParser;
import junit.framework.TestCase;

public class DependencyTest extends TestCase {

	public void test() throws ANTLRParseException, IOException {
		testLocation("tests\\dep1.form", 4);
		testLocation("tests\\dep2.form", 4);
		testLocation("tests\\dep3.form", 4);
	}

	private void testLocation(String location, int errors) throws ANTLRParseException, IOException {
		FormParser parser = new FormParser(location);
		Dependencies check = new Dependencies(parser.getForm());
		assertTrue(check.getErrors().size() == errors);
	}

}
