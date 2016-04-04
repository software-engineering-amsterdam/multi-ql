package eu.bankersen.kevin.ql.form.formchecker;

import java.util.Iterator;
import java.util.List;

import eu.bankersen.kevin.ql.form.formchecker.analytics.errors.AnalyticsError;
import eu.bankersen.kevin.ql.form.formchecker.analytics.warnings.TypeCheckWarning;

public class InvalidForm extends Exception {

	private final List<AnalyticsError> errors;
	private final List<TypeCheckWarning> warnings;

	public InvalidForm(List<TypeCheckWarning> warningList, List<AnalyticsError> errorList) {
		this.warnings = warningList;
		this.errors = errorList;
	}

	public Iterator<TypeCheckWarning> getWarnings() {
		return warnings.iterator();
	}

	public Iterator<AnalyticsError> getErrors() {
		return errors.iterator();
	}
}
