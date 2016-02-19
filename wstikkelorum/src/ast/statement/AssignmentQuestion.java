package ast.statement;

import ast.TreeNode;
import ast.expression.Expression;
import ast.literal.Variable;
import ast.visitor.Visitable;
import ast.visitor.Visitor;

public class AssignmentQuestion extends TreeNode implements Visitable{
	private Variable variable;
	private String str;
	private Expression expression;
	
	public AssignmentQuestion(int lineNumber, Variable variable, String str, Expression expression) {
		super(lineNumber);
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
