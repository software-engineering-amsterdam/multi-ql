package uva.ql.ast.questions;

import javax.swing.JPanel;

import uva.ql.ast.EnumType;
import uva.ql.ast.abstracts.Node;
import uva.ql.ast.questions.abstracts.Question;
import uva.ql.ast.questions.types.Vanilla;
import uva.ql.ast.variables.abstracts.Variable;
import uva.ql.gui.visitors.IGUIVisitor;
import uva.ql.typechecker.visitors.ICyclicDependencyVisitor;
import uva.ql.typechecker.visitors.IDupllicateLabelsVisitor;
import uva.ql.typechecker.visitors.IDupllicateQuestionDifferentTypesVisitor;
import uva.ql.typechecker.visitors.IUndefinedQuestionVisitor;

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
	
	@Override
	public void accept(IDupllicateQuestionDifferentTypesVisitor visitor) {
		visitor.visitQuestionVanilla(this);
	}
	
	@Override
	public void accept(IGUIVisitor visitor, JPanel panel) {
		visitor.visitQuestionVanilla(this, panel);
	}
}
