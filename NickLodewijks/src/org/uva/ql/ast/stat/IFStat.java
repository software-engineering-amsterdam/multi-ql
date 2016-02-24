package org.uva.ql.ast.stat;

import org.antlr.v4.runtime.ParserRuleContext;
import org.uva.ql.ast.ASTNode;
import org.uva.ql.ast.ASTNodeVisitor;
import org.uva.ql.ast.expr.Expr;
import org.uva.ql.ast.form.Block;

public class IFStat extends ASTNode {

	private final Expr expr;
	private final Block body;

	public IFStat(ParserRuleContext context, Expr condition, Block body) {
		super(context);
		this.expr = condition;
		this.body = body;
	}

	public Expr getExpr() {
		return expr;
	}

	public Block getBody() {
		return body;
	}

	@Override
	public <T, U> T accept(ASTNodeVisitor<T, U> visitor, U context) {
		return visitor.visit(this, context);
	}
}
