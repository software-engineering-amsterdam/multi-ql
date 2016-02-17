package org.uva.ql.ast.form;

import org.antlr.v4.runtime.ParserRuleContext;
import org.uva.ql.ast.ASTNodeVisitor;
import org.uva.ql.ast.VariableDecl;
import org.uva.ql.ast.expr.Expr;

public class ComputedQuestion extends Question {

	private final Expr expression;

	public ComputedQuestion(ParserRuleContext context, VariableDecl variableDecl, String label, Expr expression) {
		super(context, variableDecl, label);
		this.expression = expression;
	}

	public Expr getExpression() {
		return expression;
	}

	@Override
	public <T, U> T accept(ASTNodeVisitor<T, U> visitor, U context) {
		return visitor.visit(this, context);
	}
}
