package ast.statement;

import ast.expression.Expression;
import ast.expression.OrExpression;

public class AssignmentQuestion {
	private String id;
	private String str;
	private String type;
	private Expression expression;
	
	public AssignmentQuestion(Object object, Object object2, Object object3,
			Expression result) {
		this.id = (String) object;
		this.str = (String) object2;
		this.type = (String) object3;
		this.expression = result;
	}
}
