package uva.ql.ast.expressions;

import uva.ql.ast.AExpression;
import uva.ql.ast.AST;
import uva.ql.interfaces.IExpression;
import uva.ql.visitors.INodeVisitor;

public class Div extends AExpression implements IExpression {

	public Div(AST ast) {
		super(ast);
	}
	
	@Override
	protected int getExprType0() {
		return IExpression.DIVIDE_EXP;
	}
	
	@Override
	public void accept(INodeVisitor visitor) {
		visitor.visitExp(this);
	}
}
