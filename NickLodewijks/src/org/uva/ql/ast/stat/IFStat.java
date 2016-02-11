package org.uva.ql.ast.stat;

import org.uva.ql.ast.ASTNode;
import org.uva.ql.ast.ASTNodeVisitor;
import org.uva.ql.ast.expr.Context;
import org.uva.ql.ast.expr.Expr;
import org.uva.ql.ast.form.Block;

public class IFStat extends ASTNode {
	private final Expr expression;
	private final Block body;

	public IFStat(Expr condition, Block body) {
		this.expression = condition;
		this.body = body;
	}

	public Expr getExpression() {
		return expression;
	}

	public Block getBody() {
		return body;
	}

	public Boolean interpret(Context context) {
		return (Boolean) expression.interpret(context);
	}

	@Override
	public <T, U> T accept(ASTNodeVisitor<T, U> visitor, U context){
		return visitor.visit(this, context);
	}
}
