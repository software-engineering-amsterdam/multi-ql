/**@goal  search and visit the logical " ||" expression'
 * @return a String object representing the value of the Number Object.
 * @return a new boolean type to visitor
 * 
 */

package semanticAction.tree.expressionNode.logical;

import semanticAction.tree.expressionNode.BinSearchTree;
import semanticAction.tree.expressionNode.AbsExpression;
import semanticAction.tree.intermediate.InterfaceVisitExpression;
import semanticAction.tree.typeNode.BooleanQL_Type;

// For &&

public class OR extends BinSearchTree {
							
	public OR (AbsExpression leftExp, AbsExpression rightExp) {
		super(leftExp, rightExp);
	}
				
	@Override
	public <T> T accept(InterfaceVisitExpression<T> visitor) {
		return visitor.visit(this);
	}
	
	@Override
	public String toString() {
		return this.getLeftExpression().toString() + " || " + this.getRightExpression().toString();
	}

	@Override
	public BooleanQL_Type getType() {
		return new BooleanQL_Type();
	}
}
