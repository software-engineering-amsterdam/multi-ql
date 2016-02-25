package org.uva.sea.ql.ast.TaxForm;

import org.uva.sea.ql.ast.ASTNode;
import org.uva.sea.ql.ast.expr.Expr;
import org.uva.sea.ql.ast.visitor.interfaces.QLNodeVisitor;
import org.uva.sea.ql.semantic.SymbolTable;

public class IFblock extends ASTNode {
	private final Expr condition;
	private final Block body;

	public IFblock(Expr condition, Block body) {
		this.condition = condition;
		this.body = body;
	}

	@Override
	public void accept(QLNodeVisitor qlPartVisitor) {
		qlPartVisitor.visit(this);
		//body.accept(qlPartVisitor);
		
	}

	public Block getBody() {
		return body;
	}
}
