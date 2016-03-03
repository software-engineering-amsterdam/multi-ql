/**@goal the class is created to search and return the left side and right side value of the Expression 
 * @param left side of Expression, rightExpression right side of Expression
 * @return the left side and right side value of Expression
 */


package semanticAction.tree.expressionNode;

public abstract class BinSearchTree extends AbsExpression {
	private final AbsExpression leftExpression, rightExpression;
	
	public BinSearchTree (AbsExpression leftExpression, AbsExpression rightExpression) {
		this.leftExpression = leftExpression;
		this.rightExpression = rightExpression;
	}
			
	public AbsExpression getLeftExpression() {
		return leftExpression;
	}
			
	public AbsExpression getRightExpression() { 
		return rightExpression;
	}
}