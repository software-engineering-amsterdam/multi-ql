package uva.ql.ast.expressions;

import uva.ql.ast.EnumType;
import uva.ql.ast.abstracts.Node;
import uva.ql.ast.expressions.abstracts.Expression;
import uva.ql.ast.expressions.types.Parentheses;
import uva.ql.interfaces.IArithmeticOperatorVisitor;
import uva.ql.interfaces.IBinaryOperatorVisitor;
import uva.ql.interfaces.ICyclicDependencyVisitor;
import uva.ql.interfaces.IDupllicateLabelsVisitor;
import uva.ql.interfaces.IDupllicateQuestionDifferentTypesVisitor;
import uva.ql.interfaces.IUndefinedQuestionVisitor;

public class ExpParentheses extends Expression {

	private Parentheses type = new Parentheses();
	private Expression exp;
	
	public ExpParentheses(Node parent, Expression exp, int startLine, int startColumn) {
		super(parent, startLine, startColumn);
		this.setExp(exp);
	}
	
	@Override
	public EnumType evalType() {
		return this.exp.evalType();
	}

	@Override
	public EnumType getType() {
		return this.type.getType();
	}
	
	public Expression getExp() {
		return exp;
	}

	public void setExp(Expression exp) {
		this.exp = exp;
	}

	@Override
	public void accept(IArithmeticOperatorVisitor visitor) {}

	@Override
	public void accept(IBinaryOperatorVisitor visitor) {}

	@Override
	public void accept(ICyclicDependencyVisitor visitor) {}

	@Override
	public void accept(IDupllicateLabelsVisitor visitor) {}

	@Override
	public void accept(IDupllicateQuestionDifferentTypesVisitor visitor) {}

	@Override
	public void accept(IUndefinedQuestionVisitor visitor) {}
}
