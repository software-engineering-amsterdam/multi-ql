package uva.ql.ast.expressions;

import uva.ql.ast.AExpression;
import uva.ql.ast.AST;
import uva.ql.interfaces.IExpression;
import uva.ql.interfaces.INode;

public class Add<T> extends AExpression implements IExpression {

	public Add(AST ast) {
		super(ast);
	}

	@Override
	protected int getNodeType0() {
		return INode.EXPRESSION;
	}
	
	@Override
	protected int getExprType0() {
		return IExpression.ADD_EXP;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T eval() {
		
		T val1 = this.getLeftNode().eval();
		T val2 = this.getRightNode().eval();
		
		return (val1 + val2);
	}

}
