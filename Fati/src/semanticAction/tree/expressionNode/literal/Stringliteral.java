package semanticAction.tree.expressionNode.literal;

import semanticAction.tree.intermediate.InterfaceVisitExpression;
import semanticAction.tree.typeNode.StringQL_Type;

public class Stringliteral extends AbsLiteral { 
	private final String stringliteral;
	
	public Stringliteral (String stringliteral) {
		this.stringliteral = stringliteral;
	}	
	
	public String getVariable() {
		return stringliteral;
	}
	
	@Override
	public String toString() {
		return stringliteral.toString();
	}
	
	@Override
	public <T> T accept(InterfaceVisitExpression<T> visitor) {
		return visitor.visit(this);
	}

	@Override
	public StringQL_Type getType() {
		return new StringQL_Type();
	}
}