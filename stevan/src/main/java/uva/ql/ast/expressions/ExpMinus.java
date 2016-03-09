package uva.ql.ast.expressions;

import uva.ql.ast.EnumType;
import uva.ql.ast.abstracts.Node;
import uva.ql.ast.expressions.abstracts.ArithmeticOperator;
import uva.ql.ast.expressions.abstracts.Expression;
import uva.ql.ast.expressions.types.Minus;
import uva.ql.interfaces.IArithmeticOperatorVisitor;
import uva.ql.interfaces.ICyclicDependencyVisitor;
import uva.ql.interfaces.IDupllicateLabelsVisitor;
import uva.ql.interfaces.IUndefinedQuestionVisitor;

public class ExpMinus extends ArithmeticOperator {

	private Minus type = new Minus();
	
	public ExpMinus(Node parent, Expression lhs, Expression rhs, int startLine, int startColumn) {
		super(parent, startLine, startColumn, lhs, rhs);
	}
	
	public boolean eval() {
		return this.getLhs().eval() - this.getRhs().eval();
	}
	
	@Override
	public EnumType getType() {
		return this.type.getType();
	}
	
	@Override
	public void accept(IArithmeticOperatorVisitor visitor) {
		visitor.visitExpMinus(this);
	}

	@Override
	public void accept(IUndefinedQuestionVisitor visitor) {
		visitor.visitExpMinus(this);
	}
	
	@Override
	public void accept(ICyclicDependencyVisitor visitor) {
		visitor.visitExpMinus(this);
	}
	
	@Override
	public void accept(IDupllicateLabelsVisitor visitor) {
		visitor.visitExpMinus(this);
	}
}
