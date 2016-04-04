package ql.ast.visitor;

import ql.ast.expression.Add;
import ql.ast.expression.AndExpression;
import ql.ast.expression.Div;
import ql.ast.expression.Eq;
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
import ql.ast.literal.Variable;
import ql.ast.statement.question.ComputedQuestion;
import ql.ast.value.BooleanValue;
import ql.ast.value.IntegerValue;
import ql.ast.value.Value;

public class Evaluator extends BasicVisitor<Value> {
	protected Context context;

	public Evaluator(Context context) {
		assert(context != null);
		this.context = context;
	}

	@Override
	public Value visit(ComputedQuestion computedQuestion) {
		Value value = computedQuestion.getExpression().accept(this);
		context.putValueForQuestion(computedQuestion, value);
		return null;
	}
	
	@Override
	public Value visit(OrExpression orExpression) {
		BooleanValue booleanValueLhs = (BooleanValue) orExpression.getLhs().accept(this);
		BooleanValue booleanValueRhs = (BooleanValue) orExpression.getRhs().accept(this);
		return booleanValueLhs.OrExpression(booleanValueRhs);
	}

	@Override
	public Value visit(AndExpression andExpression) {
		BooleanValue booleanValueLhs = (BooleanValue) andExpression.getLhs().accept(this);
		BooleanValue booleanValueRhs = (BooleanValue) andExpression.getRhs().accept(this);
		return booleanValueLhs.AndExpression(booleanValueRhs);
	}

	@Override
	public Value visit(Eq eq) {
		IntegerValue integerValueLhs = (IntegerValue) eq.getLhs().accept(this);
		IntegerValue integerValueRhs = (IntegerValue) eq.getRhs().accept(this);
		return integerValueLhs.Eq(integerValueRhs);
	}

	@Override
	public Value visit(GEq geq) {
		IntegerValue integerValueLhs = (IntegerValue) geq.getLhs().accept(this);
		IntegerValue integerValueRhs = (IntegerValue) geq.getRhs().accept(this);
		return integerValueLhs.Geq(integerValueRhs);
	}

	@Override
	public Value visit(GT gt) {
		IntegerValue integerValueLhs = (IntegerValue) gt.getLhs().accept(this);
		IntegerValue integerValueRhs = (IntegerValue) gt.getRhs().accept(this);
		return integerValueLhs.Gt(integerValueRhs);
	}

	@Override
	public Value visit(LEq leq) {
		IntegerValue integerValueLhs = (IntegerValue) leq.getLhs().accept(this);
		IntegerValue integerValueRhs = (IntegerValue) leq.getRhs().accept(this);
		return integerValueLhs.LEq(integerValueRhs);
	}

	@Override
	public Value visit(LT lt) {
		IntegerValue integerValueLhs = (IntegerValue) lt.getLhs().accept(this);
		IntegerValue integerValueRhs = (IntegerValue) lt.getRhs().accept(this);
		return integerValueLhs.LT(integerValueRhs);
	}

	@Override
	public Value visit(NEq neq) {
		IntegerValue integerValueLhs = (IntegerValue) neq.getLhs().accept(this);
		IntegerValue integerValueRhs = (IntegerValue) neq.getRhs().accept(this);
		return integerValueLhs.NEq(integerValueRhs);
	}

	@Override
	public Value visit(Add add) {
		IntegerValue integerValueLhs = (IntegerValue) add.getLhs().accept(this);
		IntegerValue integerValueRhs = (IntegerValue) add.getRhs().accept(this);
		return integerValueLhs.add(integerValueRhs);
	}

	@Override
	public Value visit(Sub sub) {
		IntegerValue integerValueLhs = (IntegerValue) sub.getLhs().accept(this);
		IntegerValue integerValueRhs = (IntegerValue) sub.getRhs().accept(this);
		return integerValueLhs.Sub(integerValueRhs);
	}

	@Override
	public Value visit(Mul mul) {
		IntegerValue integerValueLhs = (IntegerValue) mul.getLhs().accept(this);
		IntegerValue integerValueRhs = (IntegerValue) mul.getRhs().accept(this);
		return integerValueLhs.Mul(integerValueRhs);
	}

	@Override
	public Value visit(Div div) {
		IntegerValue integerValueLhs = (IntegerValue) div.getLhs().accept(this);
		IntegerValue integerValueRhs = (IntegerValue) div.getRhs().accept(this);
		return integerValueLhs.Div(integerValueRhs);
	}

	@Override
	public Value visit(Pos pos) {
		IntegerValue integerValue = (IntegerValue) pos.getExpression().accept(this);
		return integerValue.Pos();
	}

	@Override
	public Value visit(Neg neg) {
		IntegerValue integerValue = (IntegerValue) neg.getExpression().accept(this);
		return integerValue.Neg();
	}

	@Override
	public Value visit(Not not) {
		BooleanValue booleanValue = (BooleanValue) not.getExpression().accept(this);
		return booleanValue.Not();
	}

	@Override
	public Value visit(IntLiteral intLiteral) {
		return intLiteral.getValue();
	}

	@Override
	public Value visit(BoolLiteral boolLiteral) {
		return boolLiteral.getValue();
	}

	@Override
	public Value visit(StringLiteral stringLiteral) {
		return stringLiteral.getValue();
	}

	@Override
	public Value visit(VariableExpression variableExpression) {
		return context.getValueForVariable(variableExpression);
	}

	@Override
	public Value visit(Variable variable) {
		return context.getValueForVariable(variable);
	}

	public Context getContext() {
		return context;
	}

	protected void addValueForQuestion(ComputedQuestion computedQuestion, Value value) {
		context.putValueForQuestion(computedQuestion, value);
	}
}
