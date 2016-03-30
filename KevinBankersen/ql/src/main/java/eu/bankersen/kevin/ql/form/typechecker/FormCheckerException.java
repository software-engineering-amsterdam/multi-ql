package eu.bankersen.kevin.ql.form.typechecker;

import java.util.List;

import eu.bankersen.kevin.ql.form.typechecker.errors.TypeCheckError;
import eu.bankersen.kevin.ql.form.typechecker.warnings.TypeCheckWarning;

public class FormCheckerException extends Exception {

    private final List<TypeCheckError> errors;
    private final List<TypeCheckWarning> warnings;

    public FormCheckerException(List<TypeCheckWarning> warningList, List<TypeCheckError> errorList) {
	this.warnings = warningList;
	this.errors = errorList;
    }

    public List<TypeCheckWarning> getWarnings() {
	return warnings;
    }

    public List<TypeCheckError> getErrors() {
	return errors;
    }
}
