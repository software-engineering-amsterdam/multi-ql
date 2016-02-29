package nl.nicasso.ql.ast.statement;

import nl.nicasso.ql.ast.Visitor;
import nl.nicasso.ql.ast.expression.Expression;
import nl.nicasso.ql.ast.structure.Block;

public class IfElseStatement extends IfStatement {

	private final Block block_else;

	public IfElseStatement(Expression expr, Block block_if, Block block_else) {
		super(expr, block_if);
		this.block_else = block_else;
	}

	public Block getBlock_else() {
		return block_else;
	}
	
	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}

}