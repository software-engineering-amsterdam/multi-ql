package uva.ql.ast.expressions.abstracts;

import javax.swing.JPanel;

import uva.ql.ast.EnumType;
import uva.ql.ast.abstracts.Node;
import uva.ql.gui.visitors.IGUIVisitor;
import uva.ql.typechecker.visitors.IArithmeticOperatorVisitor;
import uva.ql.typechecker.visitors.ICyclicDependencyVisitor;
import uva.ql.typechecker.visitors.IDupllicateLabelsVisitor;
import uva.ql.typechecker.visitors.IDupllicateQuestionDifferentTypesVisitor;
import uva.ql.typechecker.visitors.IUndefinedQuestionVisitor;

public abstract class ArithmeticOperatorBinary extends Expression {

	private Expression lhs, rhs;
	
	public ArithmeticOperatorBinary(Node parent, int startLine, int startColumn, Expression lhs, Expression rhs) {
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
	
	protected EnumType getEnumTypeEvaluation() {
		EnumType tlhs = this.getLhs().evalType();
		EnumType trhs = this.getRhs().evalType();
		
		if (
				(tlhs.equals(EnumType.INTEGER) || tlhs.equals(EnumType.MONEY))
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
	public void accept(IGUIVisitor visitor, JPanel panel) {
		visitor.visitArithmeticOperator(this, panel);
	}
}
