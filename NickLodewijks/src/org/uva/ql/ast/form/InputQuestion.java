package org.uva.ql.ast.form;

import org.uva.ql.ast.ASTNodeVisitor;
import org.uva.ql.ast.VariableDecl;

public class InputQuestion extends Question {

	public InputQuestion(VariableDecl variableDecl, String label) {
		super(variableDecl, label);
	}

	@Override
	public <T, U> T accept(ASTNodeVisitor<T, U> visitor, U context) {
		return visitor.visit(this, context);
	}
}
