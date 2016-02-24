package uva.ql.ast;

import uva.ql.interfaces.IExpression;
import uva.ql.interfaces.INodeVisitor;

public abstract class ANumber extends AExpression {

	private int numType = 0;
	
	protected ANumber(AST ast) {
		super(ast);
		
		setNumType(getNumType0());
	}
	
	@Override
	protected int getExprType0() {
		return IExpression.NUMBER;
	}

	protected abstract int getNumType0();

	public final int getNumType() {
		return this.numType;
	}
	
	private void setNumType(int numType) {
		this.numType = numType;
	}
	
	abstract public void setValue(String value);
	abstract public <T> T getValue();
	
	@Override
	public void accept(INodeVisitor visitor) {
		visitor.visitNum(this);
	}
}
