package ast.statement;

import ast.TreeNode;
import ast.literal.Variable;
import ast.visitor.Visitable;
import ast.visitor.Visitor;

public class Question extends TreeNode implements Visitable {
	private Variable variable;
	private String str;

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
	public Object accept(Visitor visitor) {
		return visitor.visit(this);
	}
}
