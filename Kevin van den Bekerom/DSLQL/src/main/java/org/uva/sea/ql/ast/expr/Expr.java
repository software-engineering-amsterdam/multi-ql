package org.uva.sea.ql.ast.expr;

import org.uva.sea.ql.ast.*;
import org.uva.sea.ql.ast.form.Context;

public abstract class Expr extends ASTNode implements Visitable {
	
	public abstract Object eval();
	public abstract Type getType(Context context);
	
	@Override
	public String toString() {
		return "expression";
	}
}
