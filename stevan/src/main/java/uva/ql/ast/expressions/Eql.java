package uva.ql.ast.expressions;

import uva.ql.ast.AExpression;
import uva.ql.ast.AST;
import uva.ql.interfaces.IExpression;
import uva.ql.interfaces.INodeVisitor;

public class Eql extends AExpression implements IExpression {

	public Eql(AST ast) {
		super(ast);
	}
	
	@Override
	protected int getExprType0() {
		return IExpression.EQL;
	}
	
	@Override
	public void accept(INodeVisitor visitor) {
		visitor.visitExp(this);
	}
}
