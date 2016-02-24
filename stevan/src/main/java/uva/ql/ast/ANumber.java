package uva.ql.ast;

import uva.ql.interfaces.IExpression;
import uva.ql.interfaces.INode;

public abstract class ANumber extends AExpression {

	private int numType = 0;
	
	protected ANumber(AST ast) {
		super(ast);
		
		setNumType(getNumType0());
	}

	protected abstract int getNumType0();

	public final int getNumType() {
		return this.numType;
	}
	
	private void setNumType(int numType) {
		this.numType = numType;
	}
	
	@Override
	protected int getExprType0() {
		return IExpression.NUMBER;
	}

	@Override
	protected int getNodeType0() {
		return INode.EXPRESSION;
	}
	
	abstract public void setValue(String value);
}
