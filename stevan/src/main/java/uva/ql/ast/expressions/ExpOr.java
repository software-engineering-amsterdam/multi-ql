package uva.ql.ast.expressions;

import uva.ql.ast.EnumType;
import uva.ql.ast.abstracts.Node;
import uva.ql.ast.expressions.abstracts.LogicalOperatorBinary;
import uva.ql.ast.expressions.abstracts.Expression;
import uva.ql.ast.expressions.types.Or;

public class ExpOr extends LogicalOperatorBinary {

	private Or type = new Or();
	
	public ExpOr(Node parent, Expression lhs, Expression rhs, int startLine, int startColumn) {
		super(parent, startLine, startColumn, lhs, rhs);
	}
	
	@Override
	public EnumType evalType() {
		return super.getEnumTypeEvaluation();
	}

	@Override
	public EnumType getType() {
		return this.type.getType();
	}
}
