package uva.ql.ast.expressions;

import uva.ql.ast.EnumType;
import uva.ql.ast.Node;
import uva.ql.ast.expressions.abstracts.ArithmeticOperatorBinary;
import uva.ql.ast.expressions.abstracts.Expression;
import uva.ql.ast.interfaces.IntEval;
import uva.ql.ast.types.expression.Multiply;

public class ExpMultiply extends ArithmeticOperatorBinary implements IntEval<Integer> {

	private Multiply type = new Multiply();
	
	public ExpMultiply(Node parent, Expression<Integer> lhs, Expression<Integer> rhs, int startLine, int startColumn) {
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
		return this.getLhs().eval() * this.getRhs().eval();
	}
}
