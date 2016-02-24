package ast.model;

import ast.visitor.StatementVisitor;

public abstract class Statement extends AbstractNode {
	public abstract void accept(StatementVisitor statementVisitor);
}
