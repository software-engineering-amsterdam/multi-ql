package eu.bankersen.kevin.ql.form.analyzer.scanners.errors;

import eu.bankersen.kevin.ql.form.ast.expressions.Identifier;

public class UndefinedQuestion extends ScannerError {

	public UndefinedQuestion(Identifier o) {
		super(o.line(), String.format("Question \"%s\" is not declared!", o.name()));
	}
}
