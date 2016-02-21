package ast.model;

import ast.visitor.ExpressionVisitor;

public abstract class Expression extends AbstractNode {
	public abstract <T> T accept(ExpressionVisitor<T> expressionVisitor);
}
