package org.uva.sea.ql.ast.form;

import org.uva.sea.ql.ast.ASTNode;
import org.uva.sea.ql.ast.ASTNodeVisitor;
import org.uva.sea.ql.ast.Result;
import org.uva.sea.ql.ast.VariableIdentifier;

public class Question extends ASTNode {

	private final VariableIdentifier variableIdentifier;
	private final String label;

	public Question(VariableIdentifier variableIdentifier, String label) {
		this.variableIdentifier = variableIdentifier;
		this.label = label;
	}

	public VariableIdentifier getVariableId() {
		return variableIdentifier;
	}

	public String getLabel() {
		return label;
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
