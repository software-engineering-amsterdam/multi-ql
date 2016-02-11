package org.uva.ql.ast.form;

import org.uva.ql.ast.ASTNodeVisitor;
import org.uva.ql.ast.VariableIdentifier;
import org.uva.ql.ast.expr.Expr;
import org.uva.ql.ui.QLQuestion;
import org.uva.ql.ui.WidgetFactory;

public class ComputedQuestion extends Question {

	private final Expr expression;

	public ComputedQuestion(VariableIdentifier variableIdentifier, String label, Expr expression) {
		super(variableIdentifier, label);
		this.expression = expression;
	}

	public Expr getExpression() {
		return expression;
	}

	@Override
	public void accept(ASTNodeVisitor visitor) {
		visitor.visit(this);
	}
}
