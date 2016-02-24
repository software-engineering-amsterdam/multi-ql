package org.uva.sea.ql.ast.statement;

import org.uva.sea.ql.ast.block.Block;
import org.uva.sea.ql.ast.expression.Expression;
import org.uva.sea.ql.ast.node.CodeFragment;

public class IfElseStatement extends IfStatement {
	
	private final Block elseBlock;
	
	public IfElseStatement(Expression expression, Block block, Block elseBlock, CodeFragment fragment) {
		super(expression, block, fragment);
		this.elseBlock = elseBlock;
	}
	
	public Block getElseBlock() {
		return this.elseBlock;
	}
	
	@Override
	public void accept(StatementVisitor visitor) {
		visitor.visitIfElseStatement(this);
		
	}

}