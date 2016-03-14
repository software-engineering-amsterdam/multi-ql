package nl.uva.sc.ql.parser.ast;

import nl.uva.sc.ql.gui.VisitorExpression;
import nl.uva.sc.ql.gui.state.State;
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
	public void accept(VisitorExpression visitorExpression){
		visitorExpression.visit(this);
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

        return this.name.equals(that.getName());
    }
	
    @Override
    public int hashCode() {
        return this.name.hashCode();
    }
}
