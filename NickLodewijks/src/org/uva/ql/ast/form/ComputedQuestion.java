package org.uva.ql.ast.form;

import org.uva.ql.TypeChecker;
import org.uva.ql.ast.ASTNodeVisitor;
import org.uva.ql.ast.Result;
import org.uva.ql.ast.VariableIdentifier;
import org.uva.ql.ast.expr.Expr;

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

		super.accept(visitor);
		expression.accept(visitor);
	}

	@Override
	public Result validate() {
		return TypeChecker.checkType(expression, getVariableId().getType());
	}
}
