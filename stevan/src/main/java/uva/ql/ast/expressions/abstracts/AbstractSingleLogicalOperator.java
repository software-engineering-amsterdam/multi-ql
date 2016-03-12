package uva.ql.ast.expressions.abstracts;

import uva.ql.ast.abstracts.Node;
import uva.ql.visitors.interfaces.typechecker.IBinaryOperatorVisitor;
import uva.ql.visitors.interfaces.typechecker.ICyclicDependencyVisitor;
import uva.ql.visitors.interfaces.typechecker.IDupllicateLabelsVisitor;
import uva.ql.visitors.interfaces.typechecker.IDupllicateQuestionDifferentTypesVisitor;
import uva.ql.visitors.interfaces.typechecker.IUndefinedQuestionVisitor;

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
