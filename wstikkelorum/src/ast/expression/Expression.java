package ast.expression;

import ast.TreeNode;
import ast.visitor.Visitable;

public abstract class Expression extends TreeNode implements Visitable {
	public Expression(int lineNumber) {
		super(lineNumber);
	}
}
