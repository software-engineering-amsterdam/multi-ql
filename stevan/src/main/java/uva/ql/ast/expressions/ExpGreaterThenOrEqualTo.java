package uva.ql.ast.expressions;

import uva.ql.ast.EnumType;
import uva.ql.ast.Node;
import uva.ql.ast.expressions.abstracts.RelationalOperatorBinary;
import uva.ql.ast.expressions.abstracts.Expression;
import uva.ql.ast.interfaces.EqualsEval;
import uva.ql.ast.types.expression.LessThenOrEqualTo;

public class ExpGreaterThenOrEqualTo extends RelationalOperatorBinary<Integer> implements EqualsEval<Boolean> {

	private LessThenOrEqualTo type = new LessThenOrEqualTo();
	
	public ExpGreaterThenOrEqualTo(Node parent, Expression<Integer> lhs, Expression<Integer> rhs, int startLine, int startColumn) {
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
		return this.getLhs().eval() >= this.getRhs().eval();
	}
}
