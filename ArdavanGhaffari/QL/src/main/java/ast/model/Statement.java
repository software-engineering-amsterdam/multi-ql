package ast.model;

import ast.visitor.StatementVisitor;

public abstract class Statement extends AbstractNode {
	public Statement(int line) {
		super(line);
	}

	public abstract void accept(StatementVisitor statementVisitor);
}
