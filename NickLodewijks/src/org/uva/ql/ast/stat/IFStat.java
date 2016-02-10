package org.uva.ql.ast.stat;

import org.uva.ql.TypeChecker;
import org.uva.ql.ast.ASTNode;
import org.uva.ql.ast.ASTNodeVisitor;
import org.uva.ql.ast.Result;
import org.uva.ql.ast.ValueType;
import org.uva.ql.ast.expr.Context;
import org.uva.ql.ast.expr.Expr;
import org.uva.ql.ast.form.Block;

public class IFStat extends ASTNode {
	private final Expr condition;
	private final Block body;

	public IFStat(Expr condition, Block body) {
		this.condition = condition;
		this.body = body;
	}

	public Boolean interpret(Context context) {
		return (Boolean) condition.interpret(context);
	}

	@Override
	public void accept(ASTNodeVisitor visitor) {
		visitor.visit(this);
	}

	public Expr getExpression() {
		return condition;
	}

	public Block getBody() {
		return body;
	}
}
