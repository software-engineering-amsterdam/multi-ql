package org.uva.sea.ql.ast.form;

import org.uva.sea.ql.ast.expr.Variable;

public class Question {

	private final Variable variable;
	private final String text;

	public Question(Variable variable, String text) {
		this.variable = variable;
		this.text = text;
	}
}
