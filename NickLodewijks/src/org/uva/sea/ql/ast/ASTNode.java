package org.uva.sea.ql.ast;

public abstract class ASTNode {

	public abstract void accept(ASTNodeVisitor visitor);

	public abstract Result validate();
}
