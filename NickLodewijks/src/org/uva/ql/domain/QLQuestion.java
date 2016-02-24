package org.uva.ql.domain;

import org.uva.ql.ast.VariableType;
import org.uva.ql.ast.expr.Expr;
import org.uva.ql.ast.form.ComputedQuestion;
import org.uva.ql.ast.form.InputQuestion;

public class QLQuestion {

	private final String id;
	private final String label;
	private final Expr expr;
	private final VariableType type;

	public QLQuestion(ComputedQuestion question) {
		this(question.getId(), question.getLabel(), question.getExpr(), question.getType());
	}

	public QLQuestion(InputQuestion question) {
		this(question.getId(), question.getLabel(), null, question.getType());
	}

	private QLQuestion(String id, String label, Expr expr, VariableType type) {
		this.id = id;
		this.label = label;
		this.expr = expr;
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public String getLabel() {
		return label;
	}

	public Expr getExpr() {
		return expr;
	}

	public VariableType getType() {
		return type;
	}
}
