package uva.ql.ast.expressions.abstracts;

import javax.swing.JPanel;

import uva.ql.ast.EnumType;
import uva.ql.ast.Node;
import uva.ql.gui.visitors.IGUIVisitor;
import uva.ql.typechecker.visitors.IBinaryOperatorVisitor;
import uva.ql.typechecker.visitors.ICyclicDependencyVisitor;
import uva.ql.typechecker.visitors.IDupllicateLabelsVisitor;
import uva.ql.typechecker.visitors.IDupllicateQuestionDifferentTypesVisitor;
import uva.ql.typechecker.visitors.IUndefinedQuestionVisitor;

public abstract class RelationalOperatorBinary extends Expression<Boolean> {

	private Expression<Integer> lhs, rhs;
	
	public RelationalOperatorBinary(Node parent, int startLine, int startColumn, Expression<Integer> lhs, Expression<Integer> rhs) {
		
		super(parent, startLine, startColumn);
		
		this.lhs = lhs;
		this.rhs = rhs;
	}

	public Expression<Integer> getLhs() {
		
		return this.lhs;
	}
	
	public Expression<Integer> getRhs() {
		
		return this.rhs;
	}
	
	protected EnumType getEnumTypeEvaluation() {
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
