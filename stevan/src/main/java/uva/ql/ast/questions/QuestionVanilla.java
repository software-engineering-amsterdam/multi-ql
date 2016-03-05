package uva.ql.ast.questions;

import uva.ql.ast.abstracts.Node;
import uva.ql.ast.abstracts.Type;
import uva.ql.ast.questions.abstracts.Question;
import uva.ql.ast.questions.types.Vanilla;
import uva.ql.ast.variables.abstracts.Variable;
import uva.ql.visitors.INodeVisitor;

public class QuestionVanilla extends Question {

	private Type type = new Vanilla();
	
	public QuestionVanilla(Node parent, String label, Variable variable, int startLine, int startColumn) {
		super(parent, label, variable, startLine, startColumn);
	}
	
	@Override
	public Type getType() {
		return this.type;
	}
	
	@Override
	public void accept(INodeVisitor visitor) {
		visitor.visitQuestionVanilla(this);
	}
}
