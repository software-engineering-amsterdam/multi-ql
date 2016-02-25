package org.uva.ql.ast;

import org.uva.ql.ast.expr.Add;
import org.uva.ql.ast.expr.And;
import org.uva.ql.ast.expr.BinaryExpr;
import org.uva.ql.ast.expr.Div;
import org.uva.ql.ast.expr.Eq;
import org.uva.ql.ast.expr.Expr;
import org.uva.ql.ast.expr.GEq;
import org.uva.ql.ast.expr.GT;
import org.uva.ql.ast.expr.LEq;
import org.uva.ql.ast.expr.LT;
import org.uva.ql.ast.expr.LiteralExpr;
import org.uva.ql.ast.expr.Mul;
import org.uva.ql.ast.expr.NEq;
import org.uva.ql.ast.expr.Neg;
import org.uva.ql.ast.expr.Not;
import org.uva.ql.ast.expr.Or;
import org.uva.ql.ast.expr.Pos;
import org.uva.ql.ast.expr.Sub;
import org.uva.ql.ast.expr.UnaryExpr;
import org.uva.ql.ast.expr.VariableExpr;
import org.uva.ql.ast.form.Block;
import org.uva.ql.ast.form.Form;
import org.uva.ql.ast.form.Questionnaire;
import org.uva.ql.ast.literal.BooleanLiteral;
import org.uva.ql.ast.literal.IntegerLiteral;
import org.uva.ql.ast.literal.StringLiteral;
import org.uva.ql.ast.stat.ComputedQuestion;
import org.uva.ql.ast.stat.IFStat;
import org.uva.ql.ast.stat.InputQuestion;
import org.uva.ql.ast.stat.Question;

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
	public T visit(Eq node, U context) {
		return visit((BinaryExpr) node, context);
	}

	@Override
	public T visit(GEq node, U context) {
		return visit((BinaryExpr) node, context);
	}

	@Override
	public T visit(GT node, U context) {
		return visit((BinaryExpr) node, context);
	}

	@Override
	public T visit(LEq node, U context) {
		return visit((BinaryExpr) node, context);
	}

	@Override
	public T visit(LT node, U context) {
		return visit((BinaryExpr) node, context);
	}

	@Override
	public T visit(NEq node, U context) {
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
	public T visit(Form node, U context) {
		node.getBody().accept(this, context);

		return null;
	}

	@Override
	public T visit(Block node, U context) {
		for (Question q : node.getQuestions()) {
			q.accept(this, context);
		}

		for (IFStat statement : node.getIfStatements()) {
			statement.accept(this, context);
		}

		return null;
	}

	@Override
	public T visit(IFStat node, U context) {
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
	public T visit(Question node, U context) {
		node.getType().accept(this, context);

		return null;
	}

	@Override
	public T visit(InputQuestion node, U context) {
		return visit((Question) node, context);
	}

	@Override
	public T visit(ComputedQuestion node, U context) {
		return visit((Question) node, context);
	}

	@Override
	public T visit(Questionnaire node, U context) {
		for (Form form : node.getForms()) {
			form.accept(this, context);
		}

		return null;
	}
}
