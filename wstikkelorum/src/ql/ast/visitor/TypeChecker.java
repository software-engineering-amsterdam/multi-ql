package ql.ast.visitor;

import java.util.List;

import ql.ast.expression.Add;
import ql.ast.expression.AndExpression;
import ql.ast.expression.BinaryExpression;
import ql.ast.expression.Div;
import ql.ast.expression.Eq;
import ql.ast.expression.Expression;
import ql.ast.expression.GEq;
import ql.ast.expression.GT;
import ql.ast.expression.LEq;
import ql.ast.expression.LT;
import ql.ast.expression.Mul;
import ql.ast.expression.NEq;
import ql.ast.expression.Neg;
import ql.ast.expression.Not;
import ql.ast.expression.OrExpression;
import ql.ast.expression.Pos;
import ql.ast.expression.Sub;
import ql.ast.expression.VariableExpression;
import ql.ast.literal.BoolLiteral;
import ql.ast.literal.IntLiteral;
import ql.ast.literal.StringLiteral;
import ql.ast.statement.IfStatement;
import ql.issue.ConditionNonBoolean;
import ql.issue.InvalidTypeForOperant;
import ql.issue.Issue;

public class TypeChecker<T> extends BasicVisitor<Type>{
	private Context context;
	
	public TypeChecker(Context context){
		this.context = context;
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
		return context.getType(variableExpression.getIdentifier(), variableExpression.getLineNumber());
	}
	
	private void checkTypesBinaryExpression(BinaryExpression binaryExpression, Type expectedType){
		checkTypes(binaryExpression.getLhs(), expectedType);
		checkTypes(binaryExpression.getRhs(), expectedType);
	}
	
	private void checkTypes(Expression expression, Type expectedType){
		Type actualType = expression.accept(this);
		if(actualType != expectedType){
			context.addIssue(new InvalidTypeForOperant(expression, expectedType, actualType));
		}
	}
	
	private void checkTypesCon(Expression expression, Type expectedType){
		Type actualType = expression.accept(this);
		if(actualType != expectedType){
			context.addIssue(new ConditionNonBoolean(expression, expectedType, actualType));
		}
	}
	
	public Context getContext(){
		return context;
	}
}
