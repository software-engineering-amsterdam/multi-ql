package nl.uva.ql.ast.literal;

import nl.uva.ql.ast.expression.Expression;
import nl.uva.ql.visitors.ExpressionVisitor;

public class Identifier extends Expression {
	private String name;
	
	public Identifier(String name, int line) {
		super(line);
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Identifier){
			return name.equals(((Identifier)obj).getName());
		}
		return false;
	}

	@Override
	public <T> T accept(ExpressionVisitor<T> expressionVisitor) {
		return expressionVisitor.visit(this);
	}	
}
