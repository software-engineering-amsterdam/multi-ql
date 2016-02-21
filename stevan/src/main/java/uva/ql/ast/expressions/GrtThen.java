package uva.ql.ast.expressions;

import uva.ql.ast.AExpression;
import uva.ql.ast.AST;
import uva.ql.interfaces.IExpression;
import uva.ql.interfaces.INode;

public class GrtThen extends AExpression implements IExpression {

	public GrtThen(AST ast) {
		super(ast);
	}

	@Override
	protected int getNodeType0() {
		return INode.EXPRESSION;
	}
	
	@Override
	protected int getExprType0() {
		return IExpression.GRT_THEN;
	}
}
