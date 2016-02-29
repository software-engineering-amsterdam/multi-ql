package ast.statement;

import ast.TreeNode;
import ast.literal.Variable;
import ast.visitor.Visitable;
import ast.visitor.Visitor;

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
