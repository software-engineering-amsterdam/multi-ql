package org.uva.sea.ql.ast.stat;

import org.uva.sea.ql.ast.ASTID;
import org.uva.sea.ql.ast.ASTNode;
import org.uva.sea.ql.ast.Visitor;

public abstract class Stat extends ASTNode {

	public Stat(ASTID id) {
		super(id);
	}
	
}
