package eu.bankersen.kevin.ql.form.typechecker;

import java.util.Iterator;
import java.util.List;

import eu.bankersen.kevin.ql.form.typechecker.errors.TypeCheckError;
import eu.bankersen.kevin.ql.form.typechecker.warnings.TypeCheckWarning;

public class InvalidForm extends Exception {

	private final List<TypeCheckError> errors;
	private final List<TypeCheckWarning> warnings;

	public InvalidForm(List<TypeCheckWarning> warningList, List<TypeCheckError> errorList) {
		this.warnings = warningList;
		this.errors = errorList;
	}

	public Iterator<TypeCheckWarning> getWarnings() {
		return warnings.iterator();
	}

	public Iterator<TypeCheckError> getErrors() {
		return errors.iterator();
	}
}
