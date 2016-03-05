package uva.ql.ast.questions;

import uva.ql.ast.abstracts.Node;
import uva.ql.ast.abstracts.Type;
import uva.ql.ast.expressions.abstracts.Expression;
import uva.ql.ast.questions.abstracts.Question;
import uva.ql.ast.questions.types.Computed;
import uva.ql.ast.variables.abstracts.Variable;
import uva.ql.visitors.INodeVisitor;

public class QuestionComputed extends Question {

	private Type type = new Computed();
	private Expression exp;
	
	public QuestionComputed(Node parent, String label, Variable variable, Expression exp, int startLine, int startColumn) {
		super(parent, label, variable, startLine, startColumn);
		this.exp = exp;
	}

	public Expression getExp() {
		return exp;
	}
	
	@Override
	public Type getType() {
		return this.type;
	}
	
	@Override
	public void accept(INodeVisitor visitor) {
		visitor.visitQuestionComputed(this);
	}
}
