package nl.nicasso.ql.ast.nodes.statements;

import nl.nicasso.ql.ast.nodes.CodeLocation;
import nl.nicasso.ql.ast.nodes.expressions.Expression;
import nl.nicasso.ql.ast.nodes.structures.Block;
import nl.nicasso.ql.ast.nodes.types.BooleanType;
import nl.nicasso.ql.ast.nodes.types.Type;
import nl.nicasso.ql.ast.nodes.types.UnknownType;
import nl.nicasso.ql.visitors.StatementVisitor;

public class IfElseStatement extends IfStatement {

	private final Block blockElse;

	public IfElseStatement(Expression expression, Block blockIf, Block blockElse, CodeLocation location) {
		super(expression, blockIf, location);
		this.blockElse = blockElse;
	}

	public Block getBlockElse() {
		return blockElse;
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