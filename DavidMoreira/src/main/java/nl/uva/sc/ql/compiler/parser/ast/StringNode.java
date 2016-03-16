package nl.uva.sc.ql.compiler.parser.ast;

import nl.uva.sc.ql.compiler.parser.value.StringVal;
import nl.uva.sc.ql.compiler.parser.value.Value;
import nl.uva.sc.ql.compiler.typechecker.Visitor;
import nl.uva.sc.ql.gui.VisitorExpression;
import nl.uva.sc.ql.gui.state.State;

public class StringNode extends ExpressionNode {

	private StringVal value;
	
	public StringNode(StringVal value){
		this.value = value;
	}
	
	@Override
	public String getType() {
		return "string";
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
		return value;
	}
}
