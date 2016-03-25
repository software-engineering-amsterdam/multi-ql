package uva.ql.deprecated;

import uva.ql.ast.EnumType;
import uva.ql.ast.Node;
import uva.ql.ast.expressions.abstracts.Expression;
import uva.ql.ast.types.expression.Parentheses;

public class ExpParentheses<T> extends Expression<T> {

	private Parentheses type = new Parentheses();
	private Expression exp;
	
	public ExpParentheses(Node parent, Expression exp, int startLine, int startColumn) {
		super(parent, startLine, startColumn);
		this.exp = exp;
	}
	
	@Override
	public EnumType evalType() {
		return this.exp.evalType();
	}

	@Override
	public EnumType getType() {
		return this.type.getType();
	}

	@Override
	public T eval() {
		// TODO Auto-generated method stub
		return null;
	}
}
