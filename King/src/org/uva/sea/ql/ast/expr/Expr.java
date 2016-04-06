package org.uva.sea.ql.ast.expr;

import org.uva.sea.ql.ast.ASTNode;
import org.uva.sea.ql.ast.visitors.QLNodeVisitor;

public abstract class Expr extends ASTNode {

	public abstract <T> T accept(QLNodeVisitor<T> visitor, boolean context);

}
