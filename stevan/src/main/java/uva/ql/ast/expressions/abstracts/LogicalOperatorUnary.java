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

public abstract class LogicalOperatorUnary extends Expression<Boolean> {

	private Expression<Boolean> lhs;
	
	public LogicalOperatorUnary(Node parent, int startLine, int startColumn, Expression<Boolean> lhs) {
		
		super(parent, startLine, startColumn);
		
		this.lhs = lhs;
	}

	public Expression<Boolean> getLhs() {
		
		return this.lhs;
	}
	
	@Override
	public EnumType getEnumTypeEvaluation() {
		EnumType tlhs = this.getLhs().evalType();
		
		return tlhs;
	}
	
	@Override
	public boolean isValid() {
		boolean valid = false;
		EnumType tlhs = this.getLhs().getType();
		
		if (tlhs.equals(EnumType.BOOLEAN)) {
			valid = true;
		}
		else {
			valid = false;
		}

		return valid;
	}

	@Override
	public boolean isInValid() {
		return !this.isValid();
	}

	@Override
	public void accept(IUndefinedQuestionVisitor visitor) {
		visitor.visitLogicalOperatorUnary(this);
	}
	
	@Override
	public void accept(ICyclicDependencyVisitor visitor) {
		visitor.visitLogicalOperatorUnary(this);
	}
	
	@Override
	public void accept(IDupllicateLabelsVisitor visitor) {
		visitor.visitLogicalOperatorUnary(this);
	}

	@Override
	public void accept(IDupllicateQuestionDifferentTypesVisitor visitor) {
		visitor.visitLogicalOperatorUnary(this);
	}

	@Override
	public void accept(IBinaryOperatorVisitor visitor) {
		visitor.visitLogicalOperatorUnary(this);
	}
	
	@Override
	public void accept(IGUIVisitor visitor, JPanel panel) {
		visitor.visitLogicalOperatorUnary(this, panel);
	}
}
