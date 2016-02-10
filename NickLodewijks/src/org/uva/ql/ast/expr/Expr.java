package org.uva.ql.ast.expr;

import org.uva.ql.ast.ASTNode;
import org.uva.ql.ast.ValueType;

public abstract class Expr extends ASTNode {

	public abstract Object interpret(Context context);

	public abstract ValueType type();
}
