package uva.ql.ast.expressions;

import uva.ql.ast.EnumType;
import uva.ql.ast.abstracts.Node;
import uva.ql.ast.expressions.abstracts.AbstractSingleLogicalOperator;
import uva.ql.ast.expressions.abstracts.Expression;
import uva.ql.ast.expressions.types.Not;

public class ExpNot extends AbstractSingleLogicalOperator {

	private Not type = new Not();
	
	public ExpNot(Node parent, Expression lhs, int startLine, int startColumn) {
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
}
