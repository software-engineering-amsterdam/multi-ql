package uva.ql.ast.expressions;

import uva.ql.ast.AExpression;
import uva.ql.ast.AST;
import uva.ql.interfaces.IExpression;
import uva.ql.interfaces.INode;

public class Minus extends AExpression implements IExpression {

	public Minus(AST ast) {
		super(ast);
	}

	@Override
	protected int getExprType0() {
		return INode.EXPRESSION;
	}

	@Override
	protected int getNodeType0() {
		return IExpression.MINUS_EXP;
	}

}
