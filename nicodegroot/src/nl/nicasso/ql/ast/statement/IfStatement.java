package nl.nicasso.ql.ast.statement;

import nl.nicasso.ql.EvaluatorVisitor;
import nl.nicasso.ql.TypeCheckerVisitor;
import nl.nicasso.ql.ast.Traversable;
import nl.nicasso.ql.ast.Visitor;
import nl.nicasso.ql.ast.expression.Expression;
import nl.nicasso.ql.ast.literal.Literal;
import nl.nicasso.ql.ast.structure.Block;
import nl.nicasso.ql.ast.type.Type;

public class IfStatement extends Statement implements Traversable  {

	private final Expression expr;
	private final Block block_if;
	
	public IfStatement(Expression expr, Block block_if) {
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
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
	
	@Override
	public Type accept(TypeCheckerVisitor visitor) {
		return visitor.visit(this);
	}

	@Override
	public Literal accept(EvaluatorVisitor visitor) {
		return visitor.visit(this);
	}

}
