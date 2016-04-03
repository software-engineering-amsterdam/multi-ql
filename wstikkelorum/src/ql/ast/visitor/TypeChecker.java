package ql.ast.visitor;

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
import ql.ast.type.BooleanType;
import ql.ast.type.IntegerType;
import ql.ast.type.StringType;
import ql.ast.type.ValueType;
import ql.issue.problem.ConditionNonBoolean;
import ql.issue.problem.InvalidTypeForOperand;

public class TypeChecker<T> extends BasicVisitor<ValueType> {
	private Context context;

	public TypeChecker(Context context) {
		this.context = context;
	}

	@Override
	public ValueType visit(IfStatement ifStatement) {
		checkTypesCon(ifStatement.getCondition(), new BooleanType());
		ifStatement.getBody().accept(this);
		return null;
	}

	@Override
	public ValueType visit(OrExpression orExpression) {
		checkTypesBinaryExpression(orExpression, new BooleanType());
		return new BooleanType();
	}

	@Override
	public ValueType visit(AndExpression andExpression) {
		checkTypesBinaryExpression(andExpression, new BooleanType());
		return new BooleanType();
	}

	@Override
	public ValueType visit(LT lt) {
		checkTypesBinaryExpression(lt, new IntegerType());
		return new BooleanType();
	}

	@Override
	public ValueType visit(LEq leq) {
		checkTypesBinaryExpression(leq, new IntegerType());
		return new BooleanType();
	}

	@Override
	public ValueType visit(GT gt) {
		checkTypesBinaryExpression(gt, new IntegerType());
		return new BooleanType();
	}

	@Override
	public ValueType visit(GEq GEq) {
		checkTypesBinaryExpression(GEq, new IntegerType());
		return new BooleanType();
	}

	@Override
	public ValueType visit(Eq eq) {
		checkTypesBinaryExpression(eq, new IntegerType());
		return new BooleanType();
	}

	@Override
	public ValueType visit(NEq neq) {
		checkTypesBinaryExpression(neq, new IntegerType());
		return new BooleanType();
	}

	@Override
	public ValueType visit(Add add) {
		checkTypesBinaryExpression(add, new IntegerType());
		return new IntegerType();
	}

	@Override
	public ValueType visit(Sub sub) {
		checkTypesBinaryExpression(sub, new IntegerType());
		return new IntegerType();
	}

	@Override
	public ValueType visit(Mul mul) {
		checkTypesBinaryExpression(mul, new IntegerType());
		return new IntegerType();
	}

	@Override
	public ValueType visit(Div div) {
		checkTypesBinaryExpression(div, new IntegerType());
		return new IntegerType();
	}

	@Override
	public ValueType visit(Pos pos) {
		checkTypes(pos.getExpression(), new IntegerType());
		return new IntegerType();
	}

	@Override
	public ValueType visit(Neg neg) {
		checkTypes(neg.getExpression(), new IntegerType());
		return new IntegerType();
	}

	@Override
	public ValueType visit(Not not) {
		checkTypes(not.getExpression(), new BooleanType());
		return new BooleanType();
	}

	@Override
	public ValueType visit(IntLiteral intLiteral) {
		return new IntegerType();
	}

	@Override
	public ValueType visit(BoolLiteral boolLiteral) {
		return new BooleanType();
	}

	@Override
	public ValueType visit(StringLiteral stringLiteral) {
		return new StringType();
	}

	@Override
	public ValueType visit(VariableExpression variableExpression) {
		return context.getType(variableExpression.getIdentifier(), variableExpression.getLineNumber());
	}

	private void checkTypesBinaryExpression(BinaryExpression binaryExpression, ValueType expectedType) {
		checkTypes(binaryExpression.getLhs(), expectedType);
		checkTypes(binaryExpression.getRhs(), expectedType);
	}

	private void checkTypes(Expression expression, ValueType expectedType) {
		ValueType actualType = expression.accept(this);
		if (!actualType.equals(expectedType)) {
			context.addIssue(new InvalidTypeForOperand(expression, expectedType, actualType));
		}
	}

	private void checkTypesCon(Expression expression, ValueType expectedType) {
		ValueType actualType = expression.accept(this);
		if (!actualType.equals(expectedType)) {
			context.addIssue(new ConditionNonBoolean(expression, expectedType, actualType));
		}
	}

	public Context getContext() {
		return context;
	}
}
