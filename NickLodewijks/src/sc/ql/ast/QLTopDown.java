package sc.ql.ast;

import sc.ql.ast.expr.Add;
import sc.ql.ast.expr.And;
import sc.ql.ast.expr.BinaryExpr;
import sc.ql.ast.expr.BooleanLiteral;
import sc.ql.ast.expr.Divide;
import sc.ql.ast.expr.Equals;
import sc.ql.ast.expr.EqualsNot;
import sc.ql.ast.expr.ExprVisitor;
import sc.ql.ast.expr.GreaterThan;
import sc.ql.ast.expr.GreaterThanOrEqual;
import sc.ql.ast.expr.IntegerLiteral;
import sc.ql.ast.expr.LessThan;
import sc.ql.ast.expr.LessThanOrEqual;
import sc.ql.ast.expr.Multiply;
import sc.ql.ast.expr.Negative;
import sc.ql.ast.expr.Not;
import sc.ql.ast.expr.Or;
import sc.ql.ast.expr.Positive;
import sc.ql.ast.expr.StringLiteral;
import sc.ql.ast.expr.Subtract;
import sc.ql.ast.expr.UnaryExpr;
import sc.ql.ast.expr.VariableExpr;
import sc.ql.ast.form.QLBlock;
import sc.ql.ast.form.QLForm;
import sc.ql.ast.form.QLFormVisitor;
import sc.ql.ast.stat.QLIFStatement;
import sc.ql.ast.stat.QLQuestion;
import sc.ql.ast.stat.QLQuestionComputed;
import sc.ql.ast.stat.QLQuestionInput;
import sc.ql.ast.stat.QLStatementVisitor;
import sc.ql.ast.type.QLBooleanType;
import sc.ql.ast.type.QLIntegerType;
import sc.ql.ast.type.QLStringType;
import sc.ql.ast.type.QLTypeVisitor;

public class QLTopDown<T, U>
		implements ExprVisitor<T, U>, QLFormVisitor<T, U>, QLStatementVisitor<T, U>, QLTypeVisitor<T, U> {

	public T visit(BinaryExpr node, U context) {
		node.left().accept(this, context);
		node.right().accept(this, context);

		return null;
	}

	public T visit(UnaryExpr node, U context) {
		node.expr().accept(this, context);

		return null;
	}

	@Override
	public T visit(Add node, U context) {
		return visit((BinaryExpr) node, context);
	}

	@Override
	public T visit(Subtract node, U context) {
		return visit((BinaryExpr) node, context);
	}

	@Override
	public T visit(Divide node, U context) {
		return visit((BinaryExpr) node, context);
	}

	@Override
	public T visit(Multiply node, U context) {
		return visit((BinaryExpr) node, context);
	}

	@Override
	public T visit(Equals node, U context) {
		return visit((BinaryExpr) node, context);
	}

	@Override
	public T visit(GreaterThanOrEqual node, U context) {
		return visit((BinaryExpr) node, context);
	}

	@Override
	public T visit(GreaterThan node, U context) {
		return visit((BinaryExpr) node, context);
	}

	@Override
	public T visit(LessThanOrEqual node, U context) {
		return visit((BinaryExpr) node, context);
	}

	@Override
	public T visit(LessThan node, U context) {
		return visit((BinaryExpr) node, context);
	}

	@Override
	public T visit(EqualsNot node, U context) {
		return visit((BinaryExpr) node, context);
	}

	@Override
	public T visit(And node, U context) {
		return visit((BinaryExpr) node, context);
	}

	@Override
	public T visit(Or node, U context) {
		return visit((BinaryExpr) node, context);
	}

	@Override
	public T visit(Negative node, U context) {
		return visit((UnaryExpr) node, context);
	}

	@Override
	public T visit(Not node, U context) {
		return visit((UnaryExpr) node, context);
	}

	@Override
	public T visit(Positive node, U context) {
		return visit((UnaryExpr) node, context);
	}

	@Override
	public T visit(VariableExpr node, U context) {
		return null;
	}

	@Override
	public T visit(QLForm node, U context) {
		node.getBody().accept(this, context);

		return null;
	}

	@Override
	public T visit(QLBlock node, U context) {
		for (QLQuestion q : node.getQuestions()) {
			q.accept(this, context);
		}

		for (QLIFStatement statement : node.getIfStatements()) {
			statement.accept(this, context);
		}

		return null;
	}

	@Override
	public T visit(QLIFStatement node, U context) {
		node.getCondition().accept(this, context);
		node.getBody().accept(this, context);

		return null;
	}

	@Override
	public T visit(BooleanLiteral node, U context) {
		return null;
	}

	@Override
	public T visit(IntegerLiteral node, U context) {
		return null;
	}

	@Override
	public T visit(StringLiteral node, U context) {
		return null;
	}

	@Override
	public T visit(QLQuestionInput node, U context) {
		node.getType().accept(this, context);

		return null;
	}

	@Override
	public T visit(QLQuestionComputed node, U context) {
		node.getType().accept(this, context);

		return null;
	}

	@Override
	public T visit(QLBooleanType type, U context) {
		return null;
	}

	@Override
	public T visit(QLStringType type, U context) {
		return null;
	}

	@Override
	public T visit(QLIntegerType type, U context) {
		return null;
	}
}
