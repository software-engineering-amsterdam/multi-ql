package org.uva.sea.ql.ast.expr;

import org.uva.sea.ql.ast.*;

public abstract class Expr extends ASTNode implements Visitable {
    protected Type type = Type.MONEY;
	
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
	
	public Type getType() {
		return this.type;
	}
	
	abstract Object eval() throws ClassCastException;
	//TODO: Implement try-catch blocks for all methods?!
}
