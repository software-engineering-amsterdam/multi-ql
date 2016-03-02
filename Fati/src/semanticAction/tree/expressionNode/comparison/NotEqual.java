/**@goal of the Subclass search and visit the NotEqual(!=)
 * @return a new boolean type to visitor
 * 
 */

package semanticAction.tree.expressionNode.comparison;

import semanticAction.tree.expressionNode.BinSearchTree;
import semanticAction.tree.expressionNode.AbsExpression;
import semanticAction.tree.intermediate.InterfaceVisitExpression;
import semanticAction.tree.typeNode.BooleanQL_Type;



public class NotEqual extends BinSearchTree {
				
	public NotEqual (AbsExpression leftExp, AbsExpression rightExp) {
		super(leftExp, rightExp);
	}
									
	@Override
	public <T> T accept(InterfaceVisitExpression<T> visitor) {
		return visitor.visit(this);
	}
	
	@Override
	public String toString() {
		return super.getLeftExpression().toString() + " != " + super.getRightExpression().toString();
	}
	
	@Override
	public BooleanQL_Type getType() {
		return new BooleanQL_Type();
	}
}