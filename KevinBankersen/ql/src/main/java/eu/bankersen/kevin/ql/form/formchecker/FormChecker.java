package eu.bankersen.kevin.ql.form.formchecker;

import java.util.ArrayList;
import java.util.List;

import eu.bankersen.kevin.ql.form.ast.statements.Form;
import eu.bankersen.kevin.ql.form.formchecker.analytics.DepCheck;
import eu.bankersen.kevin.ql.form.formchecker.analytics.ScopeCheck;
import eu.bankersen.kevin.ql.form.formchecker.analytics.TypeChecker;

public class FormChecker {

	public FormChecker(Form form) throws InvalidForm {
		List errorList = new ArrayList<>();
		List warningList = new ArrayList<>();

		TypeChecker checker = new TypeChecker(form);
		errorList.addAll(checker.getErrors());
		warningList.addAll(checker.getWarnings());

		errorList.addAll(new DepCheck(form).getErrors());

		new ScopeCheck(form);

		if (!errorList.isEmpty() || !warningList.isEmpty()) {
			throw new InvalidForm(warningList, errorList);
		}
	}
}
