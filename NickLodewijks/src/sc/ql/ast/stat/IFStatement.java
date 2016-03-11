package sc.ql.ast.stat;

import sc.ql.ast.Expression;
import sc.ql.ast.form.Block;

public final class IFStatement extends Statement {

	private final Expression condition;
	private final Block body;

	public IFStatement(Expression condition, Block body) {
		this.condition = condition;
		this.body = body;
	}

	public Expression getCondition() {
		return condition;
	}

	public Block getBody() {
		return body;
	}

	@Override
	public <T, U> T accept(StatementVisitor<T, U> visitor, U context) {
		return visitor.visit(this, context);
	}
}
