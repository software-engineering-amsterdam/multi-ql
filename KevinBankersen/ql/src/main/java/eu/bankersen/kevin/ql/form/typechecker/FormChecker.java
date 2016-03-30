package eu.bankersen.kevin.ql.form.typechecker;

import java.util.ArrayList;
import java.util.List;

import eu.bankersen.kevin.ql.form.ast.form.Form;
import eu.bankersen.kevin.ql.form.typechecker.analytics.DependancyAnalyzer;
import eu.bankersen.kevin.ql.form.typechecker.analytics.TypeChecker;

public class FormChecker {

    public FormChecker(Form form) throws FormCheckerException {
	List errorList = new ArrayList<>();
	List warningList = new ArrayList<>();

	errorList.addAll(new TypeChecker(form).getErrors());

	errorList.addAll(new DependancyAnalyzer(form).getErrors());

	if (!errorList.isEmpty() || !warningList.isEmpty()) {
	    throw new FormCheckerException(warningList, errorList);
	}
    }
}
