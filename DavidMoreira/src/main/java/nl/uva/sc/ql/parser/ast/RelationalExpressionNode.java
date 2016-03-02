package nl.uva.sc.ql.parser.ast;

import nl.uva.sc.ql.exceptions.NoValueException;
import nl.uva.sc.ql.parser.Visitor;

public class RelationalExpressionNode extends OperationExpressionNode {

	@Override
	public String getType() {
		return "boolean";
	}
	
	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

	@Override
	public boolean eval() {
		Node left = this.getLeft();
		Node right = this.getRight();
		
		// update node values
		left.eval();
		right.eval();
		
		try {
			String symbol = this.getSymbol();
			switch(symbol){
				case "!":
					this.setValue(!(boolean) left.getValue());
					break;
				case "<":
					// TODO: can also be integer attention
					this.setValue((double) left.getValue() < (double) right.getValue());
					break;
				case ">":
					this.setValue((double) left.getValue() > (double) right.getValue());
					break;
				case "<=":
					this.setValue((double) left.getValue() <= (double) right.getValue());
					break;
				case ">=":
					this.setValue((double) left.getValue() >= (double) right.getValue());
					break;
				case "==":
					this.setValue(left.getValue().equals(right.getValue()));
					break;
				case "!=":
					this.setValue(!left.getValue().equals(right.getValue()));
					break;
			}
		} catch (NoValueException e) {
			return false;
		}		
		
		return true;
	}

}
