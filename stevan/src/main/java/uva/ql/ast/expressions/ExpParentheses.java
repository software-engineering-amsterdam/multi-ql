package uva.ql.ast.expressions;

import uva.ql.ast.EnumType;
import uva.ql.ast.abstracts.Node;
import uva.ql.ast.expressions.abstracts.Expression;
import uva.ql.ast.expressions.types.Parentheses;

public class ExpParentheses extends Expression {

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
}
