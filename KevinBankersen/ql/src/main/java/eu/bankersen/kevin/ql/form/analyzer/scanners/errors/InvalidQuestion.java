package eu.bankersen.kevin.ql.form.analyzer.scanners.errors;

import eu.bankersen.kevin.ql.form.ast.statements.Question;

public class InvalidQuestion extends ScannerError {

	public InvalidQuestion(Question question) {
		super(question.line(),
				String.format("The question %s is already defined with a different type!", question.name()));
	}

}
