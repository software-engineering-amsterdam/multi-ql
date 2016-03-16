package uva.ql.ast.expressions;

import uva.ql.ast.EnumType;
import uva.ql.ast.abstracts.Node;
import uva.ql.ast.expressions.abstracts.RelationalOperatorBinary;
import uva.ql.ast.expressions.abstracts.Expression;
import uva.ql.ast.expressions.types.LessThen;

public class ExpLessThen extends RelationalOperatorBinary {

	private LessThen type = new LessThen();
	
	public ExpLessThen(Node parent, Expression lhs, Expression rhs, int startLine, int startColumn) {
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
