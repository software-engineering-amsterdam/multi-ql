package nl.nicasso.ql.ast.statement;

import nl.nicasso.ql.ast.CodeLocation;
import nl.nicasso.ql.ast.expression.Expression;
import nl.nicasso.ql.ast.structure.Block;
import nl.nicasso.ql.visitor.StatementVisitor;

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
	public <T> T accept(StatementVisitor<T> visitor) {
		return visitor.visit(this);
	}

}