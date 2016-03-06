package uva.ql.ast.expressions;

import uva.ql.ast.abstracts.Node;
import uva.ql.ast.abstracts.Type;
import uva.ql.ast.expressions.abstracts.Expression;
import uva.ql.ast.expressions.types.Add;
import uva.ql.interfaces.IArithmeticOperatorVisitor;
import uva.ql.interfaces.ICyclicQuestionDependenciesVisitor;
import uva.ql.interfaces.IDupllicateLabelsVisitor;
import uva.ql.interfaces.IUndefinedQuestionVisitor;

public class ExpParentheses extends Expression {

	private Type type = new Add();
	private Expression exp;
	
	public ExpParentheses(Node parent, Expression exp, int startLine, int startColumn) {
		super(parent, startLine, startColumn);
		this.setExp(exp);
	}
	
	public Expression getExp() {
		return exp;
	}

	public void setExp(Expression exp) {
		this.exp = exp;
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
		visitor.visitExpParentheses(this);
	}

	@Override
	public void accept(IUndefinedQuestionVisitor visitor) {}
	
	@Override
	public void accept(ICyclicQuestionDependenciesVisitor visitor) {}
	
	@Override
	public void accept(IDupllicateLabelsVisitor visitor) {}
}
