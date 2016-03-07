package nl.uva.sc.ql.parser.ast;

import nl.uva.sc.ql.gui.State;
import nl.uva.sc.ql.parser.Visitor;
import nl.uva.sc.ql.parser.value.Value;

public class IdentifierNode extends ExpressionNode {

	private String name;
	private String type;
	
	public IdentifierNode(String name){
		this.name = name;
		this.type = "None";
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	@Override
	public String getType() {
		return type;
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

	@Override
	public Value eval(State state) {
		return state.lookup(this);
	}
	
	@Override
	public boolean equals(Object o){
        if(this == o) {
            return true;
        }

        IdentifierNode that = (IdentifierNode) o;

        return getName().equals(that.getName()); // TODO: it'll be valid to have same variable name?? && getType().equals(that.getType());
    }
	
    @Override
    public int hashCode() {
        return this.name.hashCode();
    }
}
