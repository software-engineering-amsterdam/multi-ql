package org.uva.sea.ql.ast.TaxForm;

import org.uva.sea.ql.ast.VarDeclaration;
import org.uva.sea.ql.ast.VarIdentifier;
import org.uva.sea.ql.ast.expr.Expr;

public class ReadOnlyQuestion extends Question {
	private Expr expression;
	public ReadOnlyQuestion(VarDeclaration var, String text, Expr expression) {
		super(var, text);
		this.expression = expression;
	}
	
	public Expr getExpression() {
		return expression;
	}

}
