package uva.ql.ast.expressions;

import uva.ql.ast.EnumType;
import uva.ql.ast.Node;
import uva.ql.ast.expressions.abstracts.RelationalOperatorBinary;
import uva.ql.ast.expressions.abstracts.Expression;
import uva.ql.ast.interfaces.EqualsEval;
import uva.ql.ast.types.expression.LessThen;

public class ExpLessThen extends RelationalOperatorBinary implements EqualsEval<Boolean> {

	private LessThen type = new LessThen();
	
	public ExpLessThen(Node parent, Expression<Integer> lhs, Expression<Integer> rhs, int startLine, int startColumn) {
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
		return this.getLhs().eval() < this.getRhs().eval();
	}
}
