package nl.nicasso.ql.ast.statement;

import nl.nicasso.ql.ast.CodeLocation;
import nl.nicasso.ql.ast.expression.Expression;
import nl.nicasso.ql.ast.structure.Block;
import nl.nicasso.ql.visitor.StatementVisitor;

public class IfStatement extends Statement {

	private final Expression expr;
	private final Block block_if;
	
	public IfStatement(Expression expr, Block block_if, CodeLocation location) {
		super(location);
		this.expr = expr;
		this.block_if = block_if;
	}

	public Expression getExpr() {
		return expr;
	}

	public Block getBlock_if() {
		return block_if;
	}
	
	@Override
	public <T> T accept(StatementVisitor<T> visitor) {
		return visitor.visit(this);
	}

}
