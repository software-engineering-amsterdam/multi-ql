/**@goal of the Subclass calculate the Division of two integer
 * @return a new integer type
 * 
 */

package semanticAction.tree.expressionNode.calculation;

import semanticAction.tree.expressionNode.BinSearchTree;
import semanticAction.tree.expressionNode.AbsExpression;
import semanticAction.tree.intermediate.InterfaceVisitExpression;
import semanticAction.tree.typeNode.IntegerQL_Type;

public class Division extends BinSearchTree {

	public Division(AbsExpression LeftExp, AbsExpression RightExp) {
		super(LeftExp, RightExp);
	}

	@Override
	public <T> T accept(InterfaceVisitExpression<T> visitor) {
		return visitor.visit(this);
	}

	@Override
	public String toString() {
		return this.getLeftExpression() + " / " + this.getRightExpression();
	}

	@Override
	public IntegerQL_Type getType() {
		return new IntegerQL_Type();
	}
}
