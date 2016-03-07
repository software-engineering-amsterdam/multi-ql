package uva.ql.ast.questions;

import uva.ql.ast.abstracts.Node;
import uva.ql.ast.abstracts.Type;
import uva.ql.ast.expressions.abstracts.Expression;
import uva.ql.ast.questions.abstracts.Question;
import uva.ql.ast.questions.types.Computed;
import uva.ql.ast.variables.abstracts.Variable;
import uva.ql.interfaces.IArithmeticOperatorVisitor;
import uva.ql.interfaces.ICyclicQuestionDependenciesVisitor;
import uva.ql.interfaces.IDupllicateLabelsVisitor;
import uva.ql.interfaces.IUndefinedQuestionVisitor;

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
	public String typeToString() {
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
	public void accept(ICyclicQuestionDependenciesVisitor visitor) {
		visitor.visitQuestionComputed(this);
	}
	
	@Override
	public void accept(IDupllicateLabelsVisitor visitor) {
		visitor.visitQuestionComputed(this);
	}
}
