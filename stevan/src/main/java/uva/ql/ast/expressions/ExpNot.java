package uva.ql.ast.expressions;

import uva.ql.ast.EnumType;
import uva.ql.ast.Node;
import uva.ql.ast.expressions.abstracts.LogicalOperatorUnary;
import uva.ql.ast.expressions.abstracts.Expression;
import uva.ql.ast.interfaces.BoolEval;
import uva.ql.ast.types.expression.Not;

public class ExpNot extends LogicalOperatorUnary implements BoolEval<Boolean> {

	private Not type = new Not();
	
	public ExpNot(Node parent, Expression<Boolean> lhs, int startLine, int startColumn) {
		super(parent, startLine, startColumn, lhs);
	}
	
	@Override
	public EnumType evalType() {
		return this.getLhs().evalType();
	}

	@Override
	public EnumType getType() {
		return this.type.getType();
	}
	
	@Override
	public Boolean eval() {
		return (Boolean) this.getLhs().eval();
	}
}
