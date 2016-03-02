/**@goal Code Reusable: share the common information among the subclasses.--- responsibility driven design
 * @return unary expression
 * @param e expression has type AbsExpression
 * 
 */


package semanticAction.tree.expressionNode.unary;

import semanticAction.tree.expressionNode.AbsExpression;

public abstract class AbsUnary extends AbsExpression {
	private final AbsExpression e;
	
	public AbsUnary (AbsExpression e) {
		this.e = e;
	}
	
	public AbsExpression getUnaryExpression() {
		return e;
	}
}

