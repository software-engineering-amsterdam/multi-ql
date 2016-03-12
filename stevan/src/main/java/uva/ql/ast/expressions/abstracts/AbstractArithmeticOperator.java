package uva.ql.ast.expressions.abstracts;

import uva.ql.ast.abstracts.Node;
import uva.ql.interfaces.IArithmeticOperatorVisitor;
import uva.ql.interfaces.IBinaryOperatorVisitor;
import uva.ql.interfaces.ICyclicDependencyVisitor;
import uva.ql.interfaces.IDupllicateLabelsVisitor;
import uva.ql.interfaces.IDupllicateQuestionDifferentTypesVisitor;
import uva.ql.interfaces.IUndefinedQuestionVisitor;

public abstract class AbstractArithmeticOperator extends Expression {

	private Expression lhs, rhs;
	
	public AbstractArithmeticOperator(Node parent, int startLine, int startColumn, Expression lhs, Expression rhs) {
		super(parent, startLine, startColumn);
		this.lhs = lhs;
		this.rhs = rhs;
	}

	public Expression getLhs() {
		return this.lhs;
	}
	
	public void setLhs(Expression lhs) {
		this.lhs = lhs;
	}
	
	public Expression getRhs() {
		return this.rhs;
	}
	
	public void setRhs(Expression rhs) {
		this.rhs = rhs;
	}
	
	@Override
	public void accept(IArithmeticOperatorVisitor visitor) {
		visitor.visitArithmeticOperator(this);
	}

	@Override
	public void accept(IUndefinedQuestionVisitor visitor) {
		visitor.visitArithmeticOperator(this);
	}
	
	@Override
	public void accept(ICyclicDependencyVisitor visitor) {
		visitor.visitArithmeticOperator(this);
	}
	
	@Override
	public void accept(IDupllicateLabelsVisitor visitor) {
		visitor.visitArithmeticOperator(this);
	}

	@Override
	public void accept(IDupllicateQuestionDifferentTypesVisitor visitor) {
		visitor.visitArithmeticOperator(this);
	}

	@Override
	public void accept(IBinaryOperatorVisitor visitor) {}
}
