package uva.ql.ast.expressions.abstracts;

import uva.ql.ast.abstracts.Node;
import uva.ql.interfaces.IArithmeticOperatorVisitor;
import uva.ql.interfaces.IBinaryOperatorVisitor;
import uva.ql.interfaces.ICyclicDependencyVisitor;
import uva.ql.interfaces.IDupllicateLabelsVisitor;
import uva.ql.interfaces.IDupllicateQuestionDifferentTypesVisitor;
import uva.ql.interfaces.IUndefinedQuestionVisitor;

public abstract class AbstractSingleLogicalOperator extends Expression {

	private Expression lhs;
	
	public AbstractSingleLogicalOperator(Node parent, int startLine, int startColumn, Expression lhs) {
		
		super(parent, startLine, startColumn);
		
		this.lhs = lhs;
	}

	public Expression getLhs() {
		
		return this.lhs;
	}
	
	@Override
	public void accept(IArithmeticOperatorVisitor visitor) {}

	@Override
	public void accept(IUndefinedQuestionVisitor visitor) {
		visitor.visitSingleLogicalOperator(this);
	}
	
	@Override
	public void accept(ICyclicDependencyVisitor visitor) {
		visitor.visitSingleLogicalOperator(this);
	}
	
	@Override
	public void accept(IDupllicateLabelsVisitor visitor) {
		visitor.visitSingleLogicalOperator(this);
	}

	@Override
	public void accept(IDupllicateQuestionDifferentTypesVisitor visitor) {
		visitor.visitSingleLogicalOperator(this);
	}

	@Override
	public void accept(IBinaryOperatorVisitor visitor) {
		visitor.visitSingleLogicalOperator(this);
	}
}
