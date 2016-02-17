package org.uva.sea.ql.ast.TaxForm;

import org.uva.sea.ql.ast.ASTNODE;
import org.uva.sea.ql.ast.TaxForm.interfaces.QLPart;
import org.uva.sea.ql.ast.TaxForm.interfaces.QLNodeVisitor;
import org.uva.sea.ql.ast.expr.Expr;

public class IFblock extends ASTNODE implements QLPart {
	private final Expr condition;
	private final Block body;

	public IFblock(Expr condition, Block body) {
		this.condition = condition;
		this.body = body;
	}

	@Override
	public void accept(QLNodeVisitor qlPartVisitor) {
		qlPartVisitor.visit(this);
		
	}
}
