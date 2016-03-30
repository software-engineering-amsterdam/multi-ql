package uva.ql.ast.questions;

import javax.swing.JPanel;

import uva.ql.ast.EnumType;
import uva.ql.ast.Node;
import uva.ql.ast.expressions.abstracts.Expression;
import uva.ql.ast.types.question.Computed;
import uva.ql.ast.variables.Variable;
import uva.ql.typechecker.visitors.IArithmeticOperatorVisitor;
import uva.ql.typechecker.visitors.ICyclicDependencyVisitor;
import uva.ql.typechecker.visitors.IDupllicateLabelsVisitor;
import uva.ql.typechecker.visitors.IDupllicateQuestionDifferentTypesVisitor;
import uva.ql.typechecker.visitors.IUndefinedQuestionVisitor;
import uva.ql.visitors.IGUIVisitor;

public class QuestionComputed<T> extends Question<T> {

	private Computed type = new Computed();
	private Expression<T> exp;
	
	public QuestionComputed(Node parent, String label, Variable<T> variable, Expression<T> exp, int startLine, int startColumn) {
		super(parent, label, variable, startLine, startColumn);
		this.exp = exp;
	}

	public Expression<T> getExp() {
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
