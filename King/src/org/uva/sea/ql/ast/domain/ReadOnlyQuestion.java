package org.uva.sea.ql.ast.domain;

import org.uva.sea.ql.ast.VarDeclaration;
import org.uva.sea.ql.ast.expr.Expr;
import org.uva.sea.ql.ast.expr.type.Type;
import org.uva.sea.ql.ast.visitors.QLNodeVisitor;

public class ReadOnlyQuestion extends Question {
	private Expr expression;
	public ReadOnlyQuestion(VarDeclaration var, String text, Expr expression) {
		super(var, text);
		this.expression = expression;
	}
	
	public Expr getExpression() {
		return expression;
	}
	
	@Override
	public Type accept(QLNodeVisitor qlPartVisitor) {
		return qlPartVisitor.visit(this);
	}

}
