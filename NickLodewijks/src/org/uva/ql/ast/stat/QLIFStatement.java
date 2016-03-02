package org.uva.ql.ast.stat;

import org.antlr.v4.runtime.ParserRuleContext;
import org.uva.ql.ast.ASTNode;
import org.uva.ql.ast.ASTNodeVisitor;
import org.uva.ql.ast.expr.Expr;
import org.uva.ql.ast.form.QLBlock;

public class QLIFStatement extends ASTNode {

	private final Expr expr;
	private final QLBlock body;

	public QLIFStatement(ParserRuleContext context, Expr condition, QLBlock body) {
		super(context);
		this.expr = condition;
		this.body = body;
	}

	public Expr getExpr() {
		return expr;
	}

	public QLBlock getBody() {
		return body;
	}

	@Override
	public <T, U> T accept(ASTNodeVisitor<T, U> visitor, U context) {
		return visitor.visit(this, context);
	}
}
