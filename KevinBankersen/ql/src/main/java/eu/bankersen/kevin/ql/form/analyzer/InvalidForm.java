package eu.bankersen.kevin.ql.form.analyzer;

import java.util.Iterator;
import java.util.List;

import eu.bankersen.kevin.ql.form.analyzer.scanners.errors.ScannerError;
import eu.bankersen.kevin.ql.form.analyzer.scanners.warnings.ScanWarning;

public class InvalidForm extends Exception {

	private final List<ScannerError> errors;
	private final List<ScanWarning> warnings;

	public InvalidForm(List<ScanWarning> warningList, List<ScannerError> errorList) {
		this.warnings = warningList;
		this.errors = errorList;
	}

	public Iterator<ScanWarning> getWarnings() {
		return warnings.iterator();
	}

	public Iterator<ScannerError> getErrors() {
		return errors.iterator();
	}
}
