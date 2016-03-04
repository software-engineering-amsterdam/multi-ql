package ql.ast.expression;

import ql.ast.TreeNode;
import ql.ast.visitor.Visitable;

public abstract class Expression extends TreeNode implements Visitable {
	public Expression(int lineNumber) {
		super(lineNumber);
	}
}
