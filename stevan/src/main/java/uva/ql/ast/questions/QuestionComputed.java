package uva.ql.ast.questions;

import javax.swing.JPanel;

import uva.ql.ast.EnumType;
import uva.ql.ast.abstracts.Node;
import uva.ql.ast.expressions.abstracts.Expression;
import uva.ql.ast.questions.abstracts.Question;
import uva.ql.ast.questions.types.Computed;
import uva.ql.ast.variables.abstracts.Variable;
import uva.ql.gui.visitors.IGUIVisitor;
import uva.ql.typechecker.visitors.IArithmeticOperatorVisitor;
import uva.ql.typechecker.visitors.ICyclicDependencyVisitor;
import uva.ql.typechecker.visitors.IDupllicateLabelsVisitor;
import uva.ql.typechecker.visitors.IDupllicateQuestionDifferentTypesVisitor;
import uva.ql.typechecker.visitors.IUndefinedQuestionVisitor;

public class QuestionComputed extends Question {

	private Computed type = new Computed();
	private Expression exp;
	
	public QuestionComputed(Node parent, String label, Variable variable, Expression exp, int startLine, int startColumn) {
		super(parent, label, variable, startLine, startColumn);
		this.exp = exp;
	}

	public Expression getExp() {
		return exp;
	}

	@Override
	public EnumType getType() {
		return this.type.getType();
	}
	
	@Override
	public void accept(IArithmeticOperatorVisitor visitor) {
		visitor.visitQuestionComputed(this);
	}

	@Override
	public void accept(IUndefinedQuestionVisitor visitor) {
		visitor.visitQuestionComputed(this);
	}

	@Override
	public void accept(ICyclicDependencyVisitor visitor) {
		visitor.visitQuestionComputed(this);
	}
	
	@Override
	public void accept(IDupllicateLabelsVisitor visitor) {
		visitor.visitQuestionComputed(this);
	}
	
	@Override
	public void accept(IDupllicateQuestionDifferentTypesVisitor visitor) {
		visitor.visitQuestionComputed(this);
	}
	
	@Override
	public void accept(IGUIVisitor visitor, JPanel panel) {
		visitor.visitQuestionComputed(this, panel);
	}
}
