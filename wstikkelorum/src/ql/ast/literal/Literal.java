package ql.ast.literal;

import ql.ast.expression.Expression;
import ql.ast.visitor.Visitor;

public abstract class Literal extends Expression {
	public Literal(int lineNumber) {
		super(lineNumber);
	}
	
}
