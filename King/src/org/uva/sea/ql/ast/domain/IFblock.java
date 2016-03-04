package org.uva.sea.ql.ast.domain;

import org.uva.sea.ql.ast.ASTNode;
import org.uva.sea.ql.ast.expr.Expr;
import org.uva.sea.ql.ast.expr.type.Type;
import org.uva.sea.ql.ast.visitors.QLNodeVisitor;

public class IFblock extends ASTNode {
	private final Expr condition;
	private final Block body;

	public IFblock(Expr condition, Block body) {
		this.condition = condition;
		this.body = body;
	}

	@Override
	public Type accept(QLNodeVisitor qlPartVisitor) {
		return qlPartVisitor.visit(this);
		
	}

	public Block getBody() {
		return body;
	}
	public Expr getCondition() {
		return condition;
	}
	
	@Override
	public String toString() {
		return "[ "+condition.toString()+" ]";
	}
}
