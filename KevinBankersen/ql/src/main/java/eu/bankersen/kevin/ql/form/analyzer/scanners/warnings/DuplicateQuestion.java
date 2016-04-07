package eu.bankersen.kevin.ql.form.analyzer.scanners.warnings;

import eu.bankersen.kevin.ql.form.ast.statements.Question;

public class DuplicateQuestion extends ScanWarning {

	public DuplicateQuestion(Question question) {
		super(question.line(), String.format("The question \"%s\" is declared multiple times!", question.name()));
	}

}
