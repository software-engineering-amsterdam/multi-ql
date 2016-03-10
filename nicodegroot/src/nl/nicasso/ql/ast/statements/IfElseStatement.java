package nl.nicasso.ql.ast.statements;

import nl.nicasso.ql.ast.CodeLocation;
import nl.nicasso.ql.ast.expressions.Expression;
import nl.nicasso.ql.ast.structures.Block;
import nl.nicasso.ql.ast.types.BooleanType;
import nl.nicasso.ql.ast.types.Type;
import nl.nicasso.ql.visitors.StatementVisitor;

public class IfElseStatement extends IfStatement {

	private final Block block_else;

	public IfElseStatement(Expression expr, Block block_if, Block block_else, CodeLocation location) {
		super(expr, block_if, location);
		this.block_else = block_else;
	}

	public Block getBlock_else() {
		return block_else;
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