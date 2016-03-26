package uva.ql.ast.expressions;

import uva.ql.ast.EnumType;
import uva.ql.ast.Node;
import uva.ql.ast.expressions.abstracts.LogicalOperatorBinary;
import uva.ql.ast.expressions.abstracts.Expression;
import uva.ql.ast.interfaces.BoolEval;
import uva.ql.ast.types.expression.Or;

public class ExpOr extends LogicalOperatorBinary implements BoolEval<Boolean> {

	private Or type = new Or();
	
	public ExpOr(Node parent, Expression<Boolean> lhs, Expression<Boolean> rhs, int startLine, int startColumn) {
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

	@Override
	public Boolean eval() {
		return this.getLhs().eval() || this.getRhs().eval();
	}
}
