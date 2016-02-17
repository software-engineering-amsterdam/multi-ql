package org.uva.sea.ql.ast.statement;

import java.util.List;

import org.uva.sea.ql.ast.block.Block;
import org.uva.sea.ql.ast.expression.Expression;
import org.uva.sea.ql.ast.node.ASTNode;
import org.uva.sea.ql.ast.node.CodeFragment;

public class IfStatement extends Statement {

	private final Expression expression;
	private final Block block;
	
	public IfStatement(Expression expression, Block block, CodeFragment fragment) {
		super(fragment);
		this.expression = expression;
		this.block = block;
	}
	
	public Expression getExpression() {
		return this.expression;
	}

	public Block block() {
		return this.block;
	}

	@Override
	public ASTNode accept(StatementVisitor<ASTNode> visitor) {
		return visitor.visit(this);
	}
	
}