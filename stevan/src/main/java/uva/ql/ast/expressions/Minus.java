package uva.ql.ast.expressions;

import uva.ql.ast.AExpression;
import uva.ql.ast.AST;
import uva.ql.interfaces.IExpression;
import uva.ql.visitors.INodeVisitor;

public class Minus extends AExpression {

	public Minus(AST ast) {
		super(ast);
	}

	@Override
	protected int getExprType0() {
		return IExpression.MINUS_EXP;
	}

	@Override
	public void accept(INodeVisitor visitor) {
		visitor.visitExp(this);
	}
}
