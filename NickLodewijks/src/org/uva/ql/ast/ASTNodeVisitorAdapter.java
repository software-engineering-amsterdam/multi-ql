package org.uva.ql.ast;

import org.uva.ql.ast.expr.BinaryExpr;
import org.uva.ql.ast.expr.Expr;
import org.uva.ql.ast.expr.LiteralExpr;
import org.uva.ql.ast.expr.UnaryExpr;
import org.uva.ql.ast.expr.VariableExpr;
import org.uva.ql.ast.expr.math.Add;
import org.uva.ql.ast.expr.math.Div;
import org.uva.ql.ast.expr.math.Mul;
import org.uva.ql.ast.expr.math.Neg;
import org.uva.ql.ast.expr.math.Pos;
import org.uva.ql.ast.expr.math.Sub;
import org.uva.ql.ast.expr.rel.And;
import org.uva.ql.ast.expr.rel.Equals;
import org.uva.ql.ast.expr.rel.GreaterThanOrEquals;
import org.uva.ql.ast.expr.rel.GreaterThan;
import org.uva.ql.ast.expr.rel.LessThanOrEquals;
import org.uva.ql.ast.expr.rel.LessThan;
import org.uva.ql.ast.expr.rel.EqualsNot;
import org.uva.ql.ast.expr.rel.Not;
import org.uva.ql.ast.expr.rel.Or;
import org.uva.ql.ast.form.QLBlock;
import org.uva.ql.ast.form.QLForm;
import org.uva.ql.ast.form.QLQuestionnaire;
import org.uva.ql.ast.literal.BooleanLiteral;
import org.uva.ql.ast.literal.IntegerLiteral;
import org.uva.ql.ast.literal.StringLiteral;
import org.uva.ql.ast.stat.QLQuestionComputed;
import org.uva.ql.ast.stat.QLIFStatement;
import org.uva.ql.ast.stat.QLQuestionInput;
import org.uva.ql.ast.stat.QLQuestion;

public class ASTNodeVisitorAdapter<T, U> implements ASTNodeVisitor<T, U> {

	@Override
	public T visit(ASTNode node, U context) {
		return null;
	}

	@Override
	public T visit(Expr node, U context) {
		return null;
	}

	@Override
	public T visit(BinaryExpr node, U context) {
		node.left().accept(this, context);
		node.right().accept(this, context);

		return visit((Expr) node, context);
	}

	@Override
	public T visit(UnaryExpr node, U context) {
		node.expr().accept(this, context);

		return visit((Expr) node, context);
	}

	@Override
	public T visit(Add node, U context) {
		return visit((BinaryExpr) node, context);
	}

	@Override
	public T visit(Sub node, U context) {
		return visit((BinaryExpr) node, context);
	}

	@Override
	public T visit(Div node, U context) {
		return visit((BinaryExpr) node, context);
	}

	@Override
	public T visit(Mul node, U context) {
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
	public T visit(LiteralExpr node, U context) {
		node.getLiteral().accept(this, context);

		return visit((Expr) node, context);
	}

	@Override
	public T visit(Neg node, U context) {
		return visit((UnaryExpr) node, context);
	}

	@Override
	public T visit(Not node, U context) {
		return visit((UnaryExpr) node, context);
	}

	@Override
	public T visit(Pos node, U context) {
		return visit((UnaryExpr) node, context);
	}

	@Override
	public T visit(VariableExpr node, U context) {
		return visit((Expr) node, context);
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
		node.getExpr().accept(this, context);
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
	public T visit(QLQuestion node, U context) {
		node.getType().accept(this, context);

		return null;
	}

	@Override
	public T visit(QLQuestionInput node, U context) {
		return visit((QLQuestion) node, context);
	}

	@Override
	public T visit(QLQuestionComputed node, U context) {
		return visit((QLQuestion) node, context);
	}

	@Override
	public T visit(QLQuestionnaire node, U context) {
		for (QLForm form : node.getForms()) {
			form.accept(this, context);
		}

		return null;
	}
}
