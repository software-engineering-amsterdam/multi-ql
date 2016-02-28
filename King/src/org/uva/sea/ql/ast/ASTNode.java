package org.uva.sea.ql.ast;

import org.uva.sea.ql.ast.expr.type.Type;
import org.uva.sea.ql.ast.visitor.interfaces.QLNodeVisitor;

public abstract class ASTNode {
	public abstract Type accept(QLNodeVisitor visitor);
}
