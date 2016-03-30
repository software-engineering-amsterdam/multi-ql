package ql.ast.expression;

import ql.ast.ASTNode;
import ql.ast.visitor.Visitable;

public abstract class Expression extends ASTNode implements Visitable {
	public Expression(int lineNumber) {
		super(lineNumber);
	}
}
