package org.uva.sea.ql.ast.form;

import org.uva.sea.ql.ast.ASTNodeVisitor;
import org.uva.sea.ql.ast.Result;
import org.uva.sea.ql.ast.VariableIdentifier;

public class InputQuestion extends Question {

	public InputQuestion(VariableIdentifier variableIdentifier, String label) {
		super(variableIdentifier, label);
	}

	@Override
	public void accept(ASTNodeVisitor visitor) {
		visitor.visit(this);
	}

	@Override
	public Result validate() {
		return Result.TRUE();
	}
}
