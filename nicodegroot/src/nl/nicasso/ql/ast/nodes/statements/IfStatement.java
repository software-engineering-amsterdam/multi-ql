package nl.nicasso.ql.ast.nodes.statements;

import nl.nicasso.ql.ast.nodes.CodeLocation;
import nl.nicasso.ql.ast.nodes.expressions.Expression;
import nl.nicasso.ql.ast.nodes.structures.Block;
import nl.nicasso.ql.ast.nodes.types.BooleanType;
import nl.nicasso.ql.ast.nodes.types.Type;
import nl.nicasso.ql.ast.nodes.types.UnknownType;
import nl.nicasso.ql.visitors.StatementVisitor;

public class IfStatement extends Statement {

	private final Expression expression;
	private final Block blockIf;

	public IfStatement(Expression expression, Block blockIf, CodeLocation location) {
		super(location);
		this.expression = expression;
		this.blockIf = blockIf;
	}

	public Expression getExpression() {
		return expression;
	}

	public Block getBlockIf() {
		return blockIf;
	}

	@Override
	public <T, U> T accept(StatementVisitor<T, U> visitor, U context) {
		return visitor.visit(this, context);
	}

	public Type inferType(Type expression) {
		if (expression.equals(new BooleanType())) {
			return expression;
		}
		return new UnknownType();
	}

}
