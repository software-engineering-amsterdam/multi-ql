package uva.ql.ast.questions;

import uva.ql.ast.EnumType;
import uva.ql.ast.abstracts.Node;
import uva.ql.ast.questions.abstracts.Question;
import uva.ql.ast.questions.types.Vanilla;
import uva.ql.ast.variables.abstracts.Variable;
import uva.ql.interfaces.IArithmeticOperatorVisitor;
import uva.ql.interfaces.ICyclicDependencyVisitor;
import uva.ql.interfaces.IDupllicateLabelsVisitor;
import uva.ql.interfaces.INodeVisitor;
import uva.ql.interfaces.IUndefinedQuestionVisitor;

public class QuestionVanilla extends Question {

	private Vanilla type = new Vanilla();
	
	public QuestionVanilla(Node parent, String label, Variable variable, int startLine, int startColumn) {
		super(parent, label, variable, startLine, startColumn);
	}
	
	@Override
	public EnumType getType() {
		return this.type.getType();
	}
	
	@Override
	public void accept(INodeVisitor visitor) {
		visitor.visitQuestionVanilla(this);
	}
	
	@Override
	public void accept(IArithmeticOperatorVisitor visitor) {
		visitor.visitQuestionVanilla(this);
	}

	@Override
	public void accept(IUndefinedQuestionVisitor visitor) {
		visitor.visitQuestionVanilla(this);
	}

	@Override
	public void accept(ICyclicDependencyVisitor visitor) {
		visitor.visitQuestionVanilla(this);
	}
	
	@Override
	public void accept(IDupllicateLabelsVisitor visitor) {
		visitor.visitQuestionVanilla(this);
	}
}
