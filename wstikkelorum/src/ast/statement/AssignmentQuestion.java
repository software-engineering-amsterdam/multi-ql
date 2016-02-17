package ast.statement;

import ast.expression.Expression;
import ast.visitor.Visitable;
import ast.visitor.Visitor;

public class AssignmentQuestion implements Visitable{
	private String id;
	private String str;
	private String type;
	private Expression expression;
	
	public AssignmentQuestion(Object object, Object object2, Object object3, Expression result) {
		this.id = (String) object;
		this.str = (String) object2;
		this.type = (String) object3;
		this.expression = result;
	}
	
	public String getId() {
		return id;
	}

	public String getStr() {
		return str;
	}

	public String getType() {
		return type;
	}

	public Expression getExpression() {
		return expression;
	}
	
	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
}
