package nl.uva.ql.ast.expression.binaryexpression;

import nl.uva.ql.ast.expression.Expression;

public abstract class BinaryExpression extends Expression{
	
	private Expression leftExpression;
	private Expression rightExpression;
	
	public BinaryExpression(Expression leftExpression, Expression rightExprssion, int line){
		super(line);
		this.leftExpression = leftExpression;
		this.rightExpression = rightExprssion;
	}
	
	public Expression getLeftExpression(){
		return leftExpression;
	}
	
	public Expression getRightExpression(){
		return rightExpression;
	}
}
