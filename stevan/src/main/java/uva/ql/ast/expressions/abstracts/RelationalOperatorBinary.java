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

public abstract class RelationalOperatorBinary<T> extends Expression<Boolean> {

	private Expression<T> lhs, rhs;
	
	public RelationalOperatorBinary(Node parent, int startLine, int startColumn, Expression<T> lhs, Expression<T> rhs) {
		
		super(parent, startLine, startColumn);
		
		this.lhs = lhs;
		this.rhs = rhs;
	}

	public Expression<T> getLhs() {
		
		return this.lhs;
	}
	
	public Expression<T> getRhs() {
		
		return this.rhs;
	}
	
	@Override
	public EnumType getEnumTypeEvaluation() {
		EnumType tlhs = this.getLhs().evalType();
		EnumType trhs = this.getRhs().evalType();
		
		if (
				!tlhs.equals(EnumType.STRING)
				&&
				trhs.equals(trhs)
			) {
			return tlhs;	
		}
		else {
			return trhs;
		}
	}
	
	@Override
	public boolean isValid() {
		boolean valid = false;
		EnumType tlhs = this.getLhs().getType();
		EnumType trhs = this.getRhs().getType();
		
		if (tlhs.equals(trhs)) {
			if (tlhs.equals(EnumType.INTEGER) || tlhs.equals(EnumType.MONEY)) {
				valid = true;
			}
			else {
				valid = false;
			}
		}
		
		return valid;
	}

	@Override
	public boolean isInValid() {
		return !this.isValid();
	}

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
	
	@Override
	public void accept(IGUIVisitor visitor, JPanel panel) {
		visitor.visitRelationalOperatorBinary(this, panel);
	}
}
