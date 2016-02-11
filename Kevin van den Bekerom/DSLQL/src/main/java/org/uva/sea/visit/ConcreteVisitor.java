package org.uva.sea.visit;

import org.uva.sea.ql.ast.LeftDFSVisitor;

public class ConcreteVisitor extends LeftDFSVisitor {

	@Override
	public void visit(IntLiteral il) {
		super.dfs(il, this);
		System.out.println("Went into the int thing" + Integer.toString(il.eval()));
	}

}
