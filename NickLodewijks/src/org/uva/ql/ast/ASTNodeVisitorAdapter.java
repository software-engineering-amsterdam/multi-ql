package org.uva.ql.ast;

import org.uva.ql.ast.expr.Add;
import org.uva.ql.ast.expr.And;
import org.uva.ql.ast.expr.BinaryExpr;
import org.uva.ql.ast.expr.BooleanLiteral;
import org.uva.ql.ast.expr.Divide;
import org.uva.ql.ast.expr.Equals;
import org.uva.ql.ast.expr.EqualsNot;
import org.uva.ql.ast.expr.ExprVisitor;
import org.uva.ql.ast.expr.GreaterThan;
import org.uva.ql.ast.expr.GreaterThanOrEquals;
import org.uva.ql.ast.expr.IntegerLiteral;
import org.uva.ql.ast.expr.LessThan;
import org.uva.ql.ast.expr.LessThanOrEquals;
import org.uva.ql.ast.expr.Multiply;
import org.uva.ql.ast.expr.Negative;
import org.uva.ql.ast.expr.Not;
import org.uva.ql.ast.expr.Or;
import org.uva.ql.ast.expr.Positive;
import org.uva.ql.ast.expr.StringLiteral;
import org.uva.ql.ast.expr.Subtract;
import org.uva.ql.ast.expr.UnaryExpr;
import org.uva.ql.ast.expr.VariableExpr;
import org.uva.ql.ast.form.QLBlock;
import org.uva.ql.ast.form.QLForm;
import org.uva.ql.ast.form.QLFormVisitor;
import org.uva.ql.ast.stat.QLIFStatement;
import org.uva.ql.ast.stat.QLQuestion;
import org.uva.ql.ast.stat.QLQuestionComputed;
import org.uva.ql.ast.stat.QLQuestionInput;
import org.uva.ql.ast.stat.QLStatementVisitor;
import org.uva.ql.ast.type.QLBooleanType;
import org.uva.ql.ast.type.QLIntegerType;
import org.uva.ql.ast.type.QLStringType;
import org.uva.ql.ast.type.QLTypeVisitor;

public class ASTNodeVisitorAdapter<T, U>
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
	public T visit(GreaterThanOrEquals node, U context) {
		return visit((BinaryExpr) node, context);
	}

	@Override
	public T visit(GreaterThan node, U context) {
		return visit((BinaryExpr) node, context);
	}

	@Override
	public T visit(LessThanOrEquals node, U context) {
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
		node.condition().accept(this, context);
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
