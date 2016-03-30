package eu.bankersen.kevin.ql.typechecker;

import java.util.ArrayList;
import java.util.List;

import eu.bankersen.kevin.ql.ast.form.Form;
import eu.bankersen.kevin.ql.typechecker.analytics.DependancyAnalyzer;
import eu.bankersen.kevin.ql.typechecker.analytics.TypeChecker;
import eu.bankersen.kevin.ql.typechecker.errors.TypeCheckError;
import eu.bankersen.kevin.ql.typechecker.warnings.TypeCheckWarning;

public class FormChecker {

    private final Form form;
    private final List<TypeCheckError> errorList;
    private final List<TypeCheckWarning> warningList;

    public FormChecker(Form form) throws FormCheckerException {
	this.errorList = new ArrayList<>();
	this.warningList = new ArrayList<>();
	this.form = form;

	// TypeCheck the form.
	errorList.addAll(new TypeChecker(form).getErrors());

	// Look for dependencies in the form.
	errorList.addAll(new DependancyAnalyzer(form).getErrors());

	if (!errorList.isEmpty() || !warningList.isEmpty()) {
	    throw new FormCheckerException(warningList, errorList);
	}
    }
}
