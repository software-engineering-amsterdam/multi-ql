package ql.ast.statement;

import ql.ast.TreeNode;
import ql.ast.literal.Variable;
import ql.ast.visitor.Visitable;
import ql.ast.visitor.Visitor;

public class Question extends TreeNode implements Visitable {
	private final Variable variable;
	private final String str;

	public Question(int lineNumber, Variable variable, String str) {
		super(lineNumber);
		this.variable = variable;
		this.str = str;
	}

	public Variable getVariable() {
		return variable;
	}

	public String getStr() {
		return str;
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}
