/** @goal visit unary '!' on tree
 * @return String  representing  of the unary Expression 
 * @return a new integer type to visitor
 */


package semanticAction.tree.expressionNode.unary;

import semanticAction.tree.expressionNode.AbsExpression;
import semanticAction.tree.intermediate.InterfaceVisitExpression;
import semanticAction.tree.typeNode.BooleanQL_Type;

public class NOT extends AbsUnary {
									
	public NOT (AbsExpression expression) {
		super(expression);
	}
					
	@Override
	public <T> T accept(InterfaceVisitExpression<T> visitor) {
		return visitor.visit(this);
	}
	
	@Override
	public String toString() {
		return " ! " + this.getUnaryExpression().toString();
	}

	@Override
	public BooleanQL_Type getType() {
		return new BooleanQL_Type();
	}
}
