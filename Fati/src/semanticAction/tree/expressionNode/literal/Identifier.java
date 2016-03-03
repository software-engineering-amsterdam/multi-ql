package semanticAction.tree.expressionNode.literal;


import semanticAction.tree.expressionNode.AbsExpression;
import semanticAction.tree.intermediate.InterfaceVisitExpression;
import semanticAction.tree.typeNode.AbsType;



public class Identifier extends AbsExpression {
	private final String identifier;
	private final AbsType type;	
	
	public Identifier (String identifier, AbsType type) {
		this.identifier = identifier;
		this.type = type;
	}
	
	public String getID() {
		return identifier;
	}

	@Override
	public String toString() {
		return identifier;
	}
	
	@Override
	public <T> T accept(InterfaceVisitExpression<T> visitor) {
		return visitor.visit(this);
	}
	 
	@Override
	public AbsType getType() {
		return this.type;
	}	
}