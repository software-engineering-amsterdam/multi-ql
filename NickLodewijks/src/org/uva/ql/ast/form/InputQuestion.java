package org.uva.ql.ast.form;

import org.antlr.v4.runtime.ParserRuleContext;
import org.uva.ql.ast.ASTNodeVisitor;
import org.uva.ql.ast.VariableDecl;

public class InputQuestion extends Question {

	public InputQuestion(ParserRuleContext context, VariableDecl variableDecl, String label) {
		super(context, variableDecl, label);
	}

	@Override
	public <T, U> T accept(ASTNodeVisitor<T, U> visitor, U context) {
		return visitor.visit(this, context);
	}
}
