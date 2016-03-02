
/**@goal of the Subclass search and visit the equal (==)
 * @return a new boolean type
 * 
 */
package semanticAction.tree.expressionNode.comparison;

import semanticAction.tree.expressionNode.BinSearchTree;
import semanticAction.tree.expressionNode.AbsExpression;
import semanticAction.tree.intermediate.InterfaceVisitExpression;
import semanticAction.tree.typeNode.BooleanQL_Type;



public class Equal extends BinSearchTree {
						
	public Equal (AbsExpression leftExp, AbsExpression rightExp) {
		super (leftExp, rightExp);
	}
				
	@Override
	public <T> T accept(InterfaceVisitExpression<T> visitor) {
		return visitor.visit(this);
	}
	
	@Override
	public String toString() {
		return this.getLeftExpression().toString() + " == " + this.getRightExpression().toString();
	}

	@Override
	public BooleanQL_Type getType() {
		return new BooleanQL_Type();
	}
}
