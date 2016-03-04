package org.uva.sea.ql.ast;

import org.uva.sea.ql.ast.expr.type.Type;
import org.uva.sea.ql.ast.visitors.QLNodeVisitor;

public abstract class ASTNode {
	public abstract Type accept(QLNodeVisitor visitor);
}
