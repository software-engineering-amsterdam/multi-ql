package semanticAction.tree.expressionNode.literal;

import semanticAction.tree.intermediate.InterfaceVisitExpression;
import semanticAction.tree.typeNode.IntegerQL_Type;

public class Integerliteral extends AbsLiteral { 
	private final Integer integerliteral;

	public Integerliteral (int integerliteral) {
		this.integerliteral = integerliteral;
	}	
	
	public int getVariable() {
		return integerliteral;
	}
	
	@Override
	public String toString() {
		return integerliteral.toString();
	}
	
	@Override
	public <T> T accept(InterfaceVisitExpression<T> visitor) {
		return visitor.visit(this);
	}

	@Override
	public IntegerQL_Type getType() {
		return new  IntegerQL_Type();
	}
}