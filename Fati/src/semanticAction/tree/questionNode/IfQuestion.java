package semanticAction.tree.questionNode;

import java.util.List;

import semanticAction.tree.expressionNode.AbsExpression;
import semanticAction.tree.intermediate.InterfaceVisitQuestion;
import semanticAction.tree.typeNode.BooleanQL_Type;

public class IfQuestion extends AbsQuestion {

	private final AbsExpression condition;
	private List<AbsQuestion> body;

	public IfQuestion(AbsExpression ifExpr, List<AbsQuestion> ifq) {
		this.body = ifq;
		this.condition = ifExpr;
	}

	public AbsExpression getExpression() {
		return condition;
	}

	public List<AbsQuestion> getIfQuestion() {
		return body;
	}

	@Override
	public <T> T accept(InterfaceVisitQuestion<T> visitor) {
		return visitor.visit(this);
	}

	public BooleanQL_Type getType() {
		return new BooleanQL_Type();
	}

	@Override
	public String toString() {
		String output = "if " + " ( " + this.condition.toString() + " ) { \n";
		for (AbsQuestion q : body)
			output += q.toString() + "\n";
		output += " } ";

		return output;
	}
}