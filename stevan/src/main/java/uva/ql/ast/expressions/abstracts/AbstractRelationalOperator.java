package uva.ql.ast.expressions.abstracts;

import uva.ql.ast.abstracts.Node;
import uva.ql.interfaces.IArithmeticOperatorVisitor;
import uva.ql.interfaces.IBinaryOperatorVisitor;
import uva.ql.interfaces.ICyclicDependencyVisitor;
import uva.ql.interfaces.IDupllicateLabelsVisitor;
import uva.ql.interfaces.IDupllicateQuestionDifferentTypesVisitor;
import uva.ql.interfaces.IUndefinedQuestionVisitor;

public abstract class AbstractRelationalOperator extends Expression {

	private Expression lhs, rhs;
	
	public AbstractRelationalOperator(Node parent, int startLine, int startColumn, Expression lhs, Expression rhs) {
		
		super(parent, startLine, startColumn);
		
		this.lhs = lhs;
		this.rhs = rhs;
	}

	public Expression getLhs() {
		
		return this.lhs;
	}
	
	public Expression getRhs() {
		
		return this.rhs;
	}
	
	@Override
	public void accept(IArithmeticOperatorVisitor visitor) {}

	@Override
	public void accept(IUndefinedQuestionVisitor visitor) {
		visitor.visitRelationalOperator(this);
	}
	
	@Override
	public void accept(ICyclicDependencyVisitor visitor) {
		visitor.visitRelationalOperator(this);
	}
	
	@Override
	public void accept(IDupllicateLabelsVisitor visitor) {
		visitor.visitRelationalOperator(this);
	}

	@Override
	public void accept(IDupllicateQuestionDifferentTypesVisitor visitor) {
		visitor.visitRelationalOperator(this);
	}

	@Override
	public void accept(IBinaryOperatorVisitor visitor) {
		visitor.visitRelationalOperator(this);
	}
}
