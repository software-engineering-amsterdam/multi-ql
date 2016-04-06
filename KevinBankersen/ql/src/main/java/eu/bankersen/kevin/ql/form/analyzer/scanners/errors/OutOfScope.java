package eu.bankersen.kevin.ql.form.analyzer.scanners.errors;

import java.util.Set;

public class OutOfScope extends ScannerError {

	public OutOfScope(int line, String identifier) {
		super(line, String.format("A computation requires identifier %s, but it is out of scope!", identifier));
	}

	public OutOfScope(int line, Set<String> condition, String identifier) {
		super(line, String.format("The condition \"if %s\" requires identifier %s but it is out of scope!", condition,
				identifier));
	}

}
