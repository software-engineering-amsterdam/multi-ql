package uva.ql.ast.expressions;

import uva.ql.ast.EnumType;
import uva.ql.ast.Node;
import uva.ql.ast.expressions.abstracts.ArithmeticOperatorBinary;
import uva.ql.ast.expressions.abstracts.Expression;
import uva.ql.ast.interfaces.IntEval;
import uva.ql.ast.types.expression.Minus;

public class ExpMinus extends ArithmeticOperatorBinary implements IntEval<Integer> {

	private Minus type = new Minus();
	
	public ExpMinus(Node parent, Expression<Integer> lhs, Expression<Integer> rhs, int startLine, int startColumn) {
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
	public Integer eval() {
		return this.getLhs().eval() - this.getRhs().eval();
	}
}
