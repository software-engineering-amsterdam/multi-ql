package org.uva.ql.ast.form;

import org.uva.ql.ast.ASTNodeVisitor;
import org.uva.ql.ast.VariableIdentifier;

public class InputQuestion extends Question {

	public InputQuestion(VariableIdentifier variableIdentifier, String label) {
		super(variableIdentifier, label);
	}

	@Override
	public void accept(ASTNodeVisitor visitor) {
		visitor.visit(this);
	}
}
