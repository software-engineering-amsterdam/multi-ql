package semanticAction.tree.expressionNode.literal;

import semanticAction.tree.expressionNode.AbsExpression;
import semanticAction.tree.intermediate.InterfaceVisitExpression;

public abstract class AbsLiteral extends AbsExpression {
	public abstract String toString();
	public abstract <T> T accept(InterfaceVisitExpression<T> visitor);
}