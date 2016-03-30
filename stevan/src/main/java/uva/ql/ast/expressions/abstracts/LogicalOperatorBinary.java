package uva.ql.ast.expressions.abstracts;

import javax.swing.JPanel;

import uva.ql.ast.EnumType;
import uva.ql.ast.Node;
import uva.ql.typechecker.visitors.IBinaryOperatorVisitor;
import uva.ql.typechecker.visitors.ICyclicDependencyVisitor;
import uva.ql.typechecker.visitors.IDupllicateLabelsVisitor;
import uva.ql.typechecker.visitors.IDupllicateQuestionDifferentTypesVisitor;
import uva.ql.typechecker.visitors.IUndefinedQuestionVisitor;
import uva.ql.visitors.IGUIVisitor;

public abstract class LogicalOperatorBinary extends Expression<Boolean> {

	private Expression<Boolean> lhs, rhs;
	
	public LogicalOperatorBinary(Node parent, int startLine, int startColumn, Expression<Boolean> lhs, Expression<Boolean> rhs) {
		super(parent, startLine, startColumn);
		this.lhs = lhs;
		this.rhs = rhs;
	}

	public Expression<Boolean> getLhs() {
		return this.lhs;
	}
	
	public Expression<Boolean> getRhs() {
		return this.rhs;
	}
	
	protected EnumType getEnumTypeEvaluation() {
		EnumType tlhs = this.getLhs().evalType();
		EnumType trhs = this.getRhs().evalType();
		
		return (tlhs.equals(trhs))? tlhs : trhs;
	}

	@Override
	public void accept(IUndefinedQuestionVisitor visitor) {
		visitor.visitLogicalOperatorBinary(this);
	}
	
	@Override
	public void accept(ICyclicDependencyVisitor visitor) {
		visitor.visitLogicalOperatorBinary(this);
	}
	
	@Override
	public void accept(IDupllicateLabelsVisitor visitor) {
		visitor.visitLogicalOperatorBinary(this);
	}

	@Override
	public void accept(IDupllicateQuestionDifferentTypesVisitor visitor) {
		visitor.visitLogicalOperatorBinary(this);
	}

	@Override
	public void accept(IBinaryOperatorVisitor visitor) {
		visitor.visitLogicalOperatorBinary(this);
	}
	
	@Override
	public void accept(IGUIVisitor visitor, JPanel panel) {
		visitor.visitLogicalOperatorBinary(this, panel);
	}
}
