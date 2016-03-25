package uva.ql.ast.expressions;

import uva.ql.ast.EnumType;
import uva.ql.ast.Node;
import uva.ql.ast.expressions.abstracts.Expression;
import uva.ql.ast.expressions.abstracts.RelationalOperatorBinary;
import uva.ql.ast.interfaces.EqualsEval;
import uva.ql.ast.types.expression.NotEqualTo;

public class ExpNotEqualTo extends RelationalOperatorBinary implements EqualsEval<Boolean> {

	private NotEqualTo type = new NotEqualTo();
	
	public ExpNotEqualTo(Node parent, Expression<Integer> lhs, Expression<Integer> rhs, int startLine, int startColumn) {
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
	
	@Override
	public Boolean eval() {
		return !this.getLhs().eval().equals(this.getRhs().eval());
	}
}
