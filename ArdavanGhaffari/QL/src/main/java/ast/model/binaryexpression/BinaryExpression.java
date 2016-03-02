package ast.model.binaryexpression;

import ast.model.Expression;

public abstract class BinaryExpression extends Expression{
	
	protected Expression leftExpression;
	protected Expression rightExpression;
	
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
