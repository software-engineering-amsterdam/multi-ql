package org.uva.sea.ql.ast.stat;

import org.uva.sea.ql.ast.ASTNode;
import org.uva.sea.ql.ast.Visitor;

public abstract class Stat extends ASTNode {

	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
}
