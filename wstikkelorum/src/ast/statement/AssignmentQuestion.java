package ast.statement;

import ast.expression.Expression;
import ast.literal.Variable;
import ast.visitor.Visitable;
import ast.visitor.Visitor;

public class AssignmentQuestion implements Visitable{
	private Variable variable;
	private String str;
	private Expression expression;
	
	public AssignmentQuestion(Variable variable, String str, Expression expression) {
		this.variable = variable;
		this.str = str;
		this.expression = expression;
	}
	
	public Variable getVariable() {
		return variable;
	}

	public String getStr() {
		return str;
	}

	public Expression getExpression() {
		return expression;
	}

	@Override
	public Object accept(Visitor visitor) {
		return visitor.visit(this);
	}
}
