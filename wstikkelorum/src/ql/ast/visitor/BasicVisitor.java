package ql.ast.visitor;

import ql.ast.expression.Add;
import ql.ast.expression.AndExpression;
import ql.ast.expression.BinaryExpression;
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
import ql.ast.form.Body;
import ql.ast.form.Form;
import ql.ast.literal.BoolLiteral;
import ql.ast.literal.IntLiteral;
import ql.ast.literal.StringLiteral;
import ql.ast.literal.Variable;
import ql.ast.statement.IfStatement;
import ql.ast.statement.Statement;
import ql.ast.statement.question.ComputedQuestion;
import ql.ast.statement.question.InputQuestion;
import ql.ast.statement.question.Question;
import ql.ast.value.BooleanValue;
import ql.ast.value.IntegerValue;
import ql.ast.value.NullValue;
import ql.ast.value.StringValue;

public class BasicVisitor<T> implements Visitor<T> {
	public BasicVisitor() {
	}

	@Override
	public T visit(Form form) {
		form.getBody().accept(this);
		return null;
	}

	@Override
	public T visit(Body body) {
		for (Visitable v : body.getStatements()) {
			v.accept(this);
		}
		return null;
	}

	@Override
	public T visit(Statement statement) {
		if (statement.getIfStatement() != null) {
			statement.getIfStatement().accept(this);
		}
		if (statement.getQuestion() != null) {
			statement.getQuestion().accept(this);
		}
		return null;
	}

	@Override
	public T visit(Question question) {
		question.getVariable().accept(this);
		return null;
	}

	@Override
	public T visit(ComputedQuestion computedQuestion) {
		computedQuestion.getExpression().accept(this);
		return null;
	}

	@Override
	public T visit(InputQuestion inputQuestion) {
		inputQuestion.getVariable().accept(this);
		return null;
	}

	@Override
	public T visit(IfStatement ifStatement) {
		ifStatement.getCondition().accept(this);
		ifStatement.getBody().accept(this);
		return null;
	}

	@Override
	public T visit(BinaryExpression binaryExpression) {
		binaryExpression.getLhs().accept(this);
		binaryExpression.getRhs().accept(this);
		return null;
	}

	@Override
	public T visit(OrExpression orExpression) {
		return visit((BinaryExpression) orExpression);
	}

	@Override
	public T visit(AndExpression andExpression) {
		return visit((BinaryExpression) andExpression);
	}

	@Override
	public T visit(Add add) {
		return visit((BinaryExpression) add);
	}

	@Override
	public T visit(Div div) {
		return visit((BinaryExpression) div);
	}

	@Override
	public T visit(Eq eq) {
		return visit((BinaryExpression) eq);
	}

	@Override
	public T visit(GEq geq) {
		return visit((BinaryExpression) geq);
	}

	@Override
	public T visit(GT gt) {
		return visit((BinaryExpression) gt);
	}

	@Override
	public T visit(LEq leq) {
		return visit((BinaryExpression) leq);
	}

	@Override
	public T visit(LT lt) {
		return visit((BinaryExpression) lt);
	}

	@Override
	public T visit(Mul mul) {
		return visit((BinaryExpression) mul);
	}

	@Override
	public T visit(Sub sub) {
		return visit((BinaryExpression) sub);
	}

	@Override
	public T visit(NEq neq) {
		return visit((BinaryExpression) neq);
	}

	@Override
	public T visit(Neg neg) {
		return neg.getExpression().accept(this);
	}

	@Override
	public T visit(Not not) {
		return not.getExpression().accept(this);
	}

	@Override
	public T visit(Pos pos) {
		return pos.getExpression().accept(this);
	}

	@Override
	public T visit(IntLiteral intLiteral) {
		return null;
	}

	@Override
	public T visit(BoolLiteral boolLiteral) {
		return null;
	}

	@Override
	public T visit(StringLiteral stringLiteral) {
		return null;
	}

	@Override
	public T visit(Variable variable) {
		return null;
	}

	@Override
	public T visit(VariableExpression variableExpression) {
		return null;
	}

	@Override
	public T visit(BooleanValue booleanValue) {
		return null;
	}

	@Override
	public T visit(IntegerValue integerValue) {
		return null;
	}

	@Override
	public T visit(StringValue stringValue) {
		return null;
	}

	@Override
	public T visit(NullValue nullValue) {
		return null;
	}
}
