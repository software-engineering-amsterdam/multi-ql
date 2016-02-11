package org.uva.sea.ql.ast;

import org.uva.sea.ql.ast.expr.BinaryExpr;

public class PrintVisitor extends LeftDFSVisitor {
	
	@Override
	public void visit(BinaryExpr binExpr) {
		System.out.println("Something");
	}
}
