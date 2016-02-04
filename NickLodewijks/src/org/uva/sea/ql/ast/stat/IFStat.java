package org.uva.sea.ql.ast.stat;

import org.uva.sea.ql.TypeChecker;
import org.uva.sea.ql.ast.ASTNode;
import org.uva.sea.ql.ast.ASTNodeVisitor;
import org.uva.sea.ql.ast.Result;
import org.uva.sea.ql.ast.ValueType;
import org.uva.sea.ql.ast.expr.Expr;
import org.uva.sea.ql.ast.form.Block;

public class IFStat extends ASTNode {
	private final Expr condition;
	private final Block body;

	public IFStat(Expr condition, Block body) {
		this.condition = condition;
		this.body = body;
	}

	@Override
	public void accept(ASTNodeVisitor visitor) {
		visitor.visit(this);

		condition.accept(visitor);
		body.accept(visitor);
	}

	@Override
	public Result validate() {
		return TypeChecker.checkType(condition, ValueType.BOOLEAN);
	}

}
