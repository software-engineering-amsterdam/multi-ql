package semanticAction.tree.questionNode;

import semanticAction.tree.expressionNode.AbsExpression;
import semanticAction.tree.expressionNode.literal.Identifier;
import semanticAction.tree.intermediate.InterfaceVisitQuestion;
import semanticAction.tree.typeNode.AbsType;

public class CalculatedQuestion extends NormalQuestion {
	private final AbsExpression expression;

	public CalculatedQuestion(Identifier ID, String questionText, AbsType TYPE,
			AbsExpression expression) {
		super(ID, questionText, TYPE);
		this.expression = expression;
	}

	public AbsExpression getExpression() {
		return expression;
	}

	@Override
	public <T> T accept(InterfaceVisitQuestion<T> visitor) {
		return visitor.visit(this);
	}

	@Override
	public String toString() {
		return getQId() + " \"" + getQText() + "\" " + getQType() + " ( "
				+ this.expression + " )";
	}
}