package org.uva.sea.ql.ast.expr;

import org.uva.sea.ql.ast.ASTNode;
import org.uva.sea.ql.ast.ValueType;

public abstract class Expr extends ASTNode {

	public abstract Object interpret(Context context);

	public abstract ValueType type();
}
