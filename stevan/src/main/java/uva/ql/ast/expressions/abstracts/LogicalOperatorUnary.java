package uva.ql.ast.expressions.abstracts;

import javax.swing.JPanel;

import uva.ql.ast.abstracts.Node;
import uva.ql.gui.visitors.IGUIVisitor;
import uva.ql.typechecker.visitors.IBinaryOperatorVisitor;
import uva.ql.typechecker.visitors.ICyclicDependencyVisitor;
import uva.ql.typechecker.visitors.IDupllicateLabelsVisitor;
import uva.ql.typechecker.visitors.IDupllicateQuestionDifferentTypesVisitor;
import uva.ql.typechecker.visitors.IUndefinedQuestionVisitor;

public abstract class LogicalOperatorUnary extends Expression {

	private Expression lhs;
	
	public LogicalOperatorUnary(Node parent, int startLine, int startColumn, Expression lhs) {
		
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
	
	@Override
	public void accept(IGUIVisitor visitor, JPanel panel) {
		visitor.visitLogicalOperatorUnary(this, panel);
	}
}
