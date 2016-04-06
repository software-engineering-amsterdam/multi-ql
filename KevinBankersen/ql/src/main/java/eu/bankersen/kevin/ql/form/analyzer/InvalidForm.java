package eu.bankersen.kevin.ql.form.analyzer;

import java.util.Iterator;
import java.util.List;

import eu.bankersen.kevin.ql.form.analyzer.scanners.errors.ScannerError;
import eu.bankersen.kevin.ql.form.analyzer.scanners.warnings.TypeCheckWarning;

public class InvalidForm extends Exception {

	private final List<ScannerError> errors;
	private final List<TypeCheckWarning> warnings;

	public InvalidForm(List<TypeCheckWarning> warningList, List<ScannerError> errorList) {
		this.warnings = warningList;
		this.errors = errorList;
	}

	public Iterator<TypeCheckWarning> getWarnings() {
		return warnings.iterator();
	}

	public Iterator<ScannerError> getErrors() {
		return errors.iterator();
	}
}
