package eu.bankersen.kevin.ql.form.analyzer;

import java.util.ArrayList;
import java.util.List;

import eu.bankersen.kevin.ql.form.analyzer.scanners.Dependencies;
import eu.bankersen.kevin.ql.form.analyzer.scanners.Scoping;
import eu.bankersen.kevin.ql.form.analyzer.scanners.TypeChecker;
import eu.bankersen.kevin.ql.form.ast.Form;

public class Analyzer {

	public Analyzer(Form form) throws InvalidForm {
		List errorList = new ArrayList<>();
		List warningList = new ArrayList<>();

		TypeChecker checker = new TypeChecker(form);
		errorList.addAll(checker.getErrors());
		warningList.addAll(checker.getWarnings());

		errorList.addAll(new Dependencies(form).getErrors());

		errorList.addAll(new Scoping(form).getErrors());

		if (!errorList.isEmpty() || !warningList.isEmpty()) {
			throw new InvalidForm(warningList, errorList);
		}
	}
}
