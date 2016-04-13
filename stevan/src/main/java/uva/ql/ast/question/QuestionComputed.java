package uva.ql.ast.question;

import uva.ql.ast.Node;
import uva.ql.ast.expression.Expression;
import uva.ql.ast.variable.Variable;


public class QuestionComputed extends Question {

	private final Expression exp;
	
	public QuestionComputed(Node parent, Expression exp, int startLine, int startColumn, String label, Variable variable) {
		super(parent, startLine, startColumn, label, variable);
		this.exp = exp;
	}

	public Expression getExp() {
		return exp;
	}
}
