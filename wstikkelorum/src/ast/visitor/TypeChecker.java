package ast.visitor;

import issue.ConditionNonBoolean;
import issue.DisplayIssues;
import issue.InvalidTypeForOperant;
import ast.expression.Add;
import ast.expression.AndExpression;
import ast.expression.BinaryExpression;
import ast.expression.Div;
import ast.expression.Eq;
import ast.expression.Expression;
import ast.expression.GEq;
import ast.expression.GT;
import ast.expression.LEq;
import ast.expression.LT;
import ast.expression.Mul;
import ast.expression.NEq;
import ast.expression.Neg;
import ast.expression.Not;
import ast.expression.OrExpression;
import ast.expression.Pos;
import ast.expression.Sub;
import ast.expression.VariableExpression;
import ast.literal.BoolLiteral;
import ast.literal.IntLiteral;
import ast.literal.StringLiteral;
import ast.statement.IfStatement;

public class TypeChecker<T> extends BasicVisitor<Type>{
	private Context context;
	
	public TypeChecker(Context context){
		this.context = context;
	}
	
	public Context getContext(){
		return context;
	}
	
	@Override
	public Type visit(IfStatement ifStatement) {
		checkTypesCon(ifStatement.getExpression(), Type.BOOLEAN);
		ifStatement.getBody().accept(this);
		return null;
	}
	
	@Override
	public Type visit(OrExpression orExpression) {
		checkTypesBinaryExpression(orExpression, Type.BOOLEAN);
		return Type.BOOLEAN;
	}

	@Override
	public Type visit(AndExpression andExpression) {
		checkTypesBinaryExpression(andExpression, Type.BOOLEAN);
		return Type.BOOLEAN;
	}

	@Override
	public Type visit(LT lt) {
		checkTypesBinaryExpression(lt, Type.INT);
		return Type.BOOLEAN;
	}

	@Override
	public Type visit(LEq leq) {
		checkTypesBinaryExpression(leq, Type.INT);
		return Type.BOOLEAN;
	}

	@Override
	public Type visit(GT gt) {
		checkTypesBinaryExpression(gt, Type.INT);
		return Type.BOOLEAN;
	}

	@Override
	public Type visit(GEq GEq) {
		checkTypesBinaryExpression(GEq, Type.INT);
		return Type.BOOLEAN;
	}

	@Override
	public Type visit(Eq eq) {
		checkTypesBinaryExpression(eq, Type.INT);
		return Type.BOOLEAN;
	}

	@Override
	public Type visit(NEq neq) {
		checkTypesBinaryExpression(neq, Type.INT);
		return Type.BOOLEAN;
	}

	@Override
	public Type visit(Add add) {
		checkTypesBinaryExpression(add, Type.INT);
		return Type.INT;
	}

	@Override
	public Type visit(Sub sub) {
		checkTypesBinaryExpression(sub, Type.INT);
		return Type.INT;
	}

	@Override
	public Type visit(Mul mul) {
		checkTypesBinaryExpression(mul, Type.INT);
		return Type.INT;
	}

	@Override
	public Type visit(Div div) {
		checkTypesBinaryExpression(div, Type.INT);
		return Type.INT;
	}

	@Override
	public Type visit(Pos pos) {
		checkTypes(pos.getExpression(), Type.INT);
		return Type.INT;
	}

	@Override
	public Type visit(Neg neg) {
		checkTypes(neg.getExpression(), Type.INT);
		return Type.INT;
	}

	@Override
	public Type visit(Not not) {
		checkTypes(not.getExpression(), Type.BOOLEAN);
		return Type.BOOLEAN;
	}
	
	@Override
	public Type visit(IntLiteral intLiteral) {
		return Type.INT;
	}

	@Override
	public Type visit(BoolLiteral boolLiteral) {
		return Type.BOOLEAN;
	}

	@Override
	public Type visit(StringLiteral stringLiteral) {
		return Type.STRING;
	}
	
	@Override
	public Type visit(VariableExpression variableExpression) {
		return context.getType(variableExpression.getName(), variableExpression.getLineNumber());
	}
	
	private void checkTypesBinaryExpression(BinaryExpression binaryExpression, Type expectedType){
		checkTypes(binaryExpression.getLhs(), expectedType);
		checkTypes(binaryExpression.getRhs(), expectedType);
	}
	
	private void checkTypes(Expression expression, Type expectedType){
		Type actualType = expression.accept(this);
		if(actualType != expectedType){
			DisplayIssues.dislayIssue(new InvalidTypeForOperant(expression, expectedType, actualType));
		}
	}
	
	private void checkTypesCon(Expression expression, Type expectedType){
		Type actualType = expression.accept(this);
		if(actualType != expectedType){
			DisplayIssues.dislayIssue(new ConditionNonBoolean(expression, expectedType, actualType));
		}
	}
}
