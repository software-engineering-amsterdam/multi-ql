package org.uva.sea.ql.ast.domain;

import org.uva.sea.ql.ast.VarDeclaration;
import org.uva.sea.ql.ast.expr.Expr;
import org.uva.sea.ql.ast.visitors.QLDomainVisitor;

public class ReadOnlyQuestion extends Question {
	private Expr expression;

	public ReadOnlyQuestion(VarDeclaration var, String text, Expr expression) {
		super(var, text);
		this.expression = expression;
	}

	public Expr getExpression() {
		return expression;
	}

	public void accept(QLDomainVisitor qlPartVisitor) {
		qlPartVisitor.visit(this);
	}

}
