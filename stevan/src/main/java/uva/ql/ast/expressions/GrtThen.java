package uva.ql.ast.expressions;

import uva.ql.ast.AExpression;
import uva.ql.ast.AST;
import uva.ql.interfaces.IExpression;
import uva.ql.interfaces.INodeVisitor;

public class GrtThen extends AExpression implements IExpression {

	public GrtThen(AST ast) {
		super(ast);
	}
	
	@Override
	protected int getExprType0() {
		return IExpression.GRT_THEN;
	}
	
	@Override
	public void accept(INodeVisitor visitor) {
		visitor.visitExp(this);
	}
}
