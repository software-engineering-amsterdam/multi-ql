package nl.nicasso.ql.ast.statements;

import nl.nicasso.ql.ast.CodeLocation;
import nl.nicasso.ql.ast.expressions.Expression;
import nl.nicasso.ql.ast.structures.Block;
import nl.nicasso.ql.ast.types.BooleanType;
import nl.nicasso.ql.ast.types.Type;
import nl.nicasso.ql.visitors.StatementVisitor;

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
	public <T, U> T accept(StatementVisitor<T, U> visitor, U context) {
		return visitor.visit(this, context);
	}
	
	public Type checkAllowedTypes(Type expr) {	
		if (expr.equals(new BooleanType())) {
			return expr;
		}
		return null;
	}

}
