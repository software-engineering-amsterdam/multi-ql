package uva.ql.ast.expressions;

import uva.ql.ast.EnumType;
import uva.ql.ast.abstracts.Node;
import uva.ql.ast.expressions.abstracts.AbstractLogicalOperator;
import uva.ql.ast.expressions.abstracts.Expression;
import uva.ql.ast.expressions.types.And;

public class ExpAnd extends AbstractLogicalOperator {

	private And type = new And();
	
	public ExpAnd(Node parent, Expression lhs, Expression rhs, int startLine, int startColumn) {
		super(parent, startLine, startColumn, lhs, rhs);
	}
	
	@Override
	public EnumType evalType() {
		EnumType Tlhs = this.getLhs().evalType();
		EnumType Trhs = this.getRhs().evalType();
		
		return (Tlhs.equals(Trhs))? Tlhs : Trhs;
	}

	@Override
	public EnumType getType() {
		return this.type.getType();
	}
}
