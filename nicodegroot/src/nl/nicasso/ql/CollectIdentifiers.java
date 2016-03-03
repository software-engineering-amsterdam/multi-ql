package nl.nicasso.ql;

import java.util.HashSet;
import java.util.Set;

import nl.nicasso.ql.ast.expressions.Binary;
import nl.nicasso.ql.ast.expressions.Identifier;
import nl.nicasso.ql.ast.expressions.Parenthesis;
import nl.nicasso.ql.ast.expressions.Unary;
import nl.nicasso.ql.ast.expressions.additive.Addition;
import nl.nicasso.ql.ast.expressions.additive.Subtraction;
import nl.nicasso.ql.ast.expressions.conditional.And;
import nl.nicasso.ql.ast.expressions.conditional.Not;
import nl.nicasso.ql.ast.expressions.conditional.Or;
import nl.nicasso.ql.ast.expressions.equality.Equal;
import nl.nicasso.ql.ast.expressions.equality.NotEqual;
import nl.nicasso.ql.ast.expressions.multiplicative.Division;
import nl.nicasso.ql.ast.expressions.multiplicative.Multiplication;
import nl.nicasso.ql.ast.expressions.relational.Greater;
import nl.nicasso.ql.ast.expressions.relational.GreaterEqual;
import nl.nicasso.ql.ast.expressions.relational.Less;
import nl.nicasso.ql.ast.expressions.relational.LessEqual;
import nl.nicasso.ql.ast.literals.BooleanLit;
import nl.nicasso.ql.ast.literals.IntegerLit;
import nl.nicasso.ql.ast.literals.MoneyLit;
import nl.nicasso.ql.ast.literals.StringLit;
import nl.nicasso.ql.visitors.ExpressionVisitor;

public class CollectIdentifiers implements ExpressionVisitor<Void> {
	
	private Set<Identifier> identifiers;
	
	CollectIdentifiers() {
		identifiers = new HashSet<Identifier>();
	}

	@Override
	public Void visit(Addition expr) {
		binaryExpressionTraversal(expr);
		return null;
	}

	@Override
	public Void visit(Subtraction expr) {
		binaryExpressionTraversal(expr);
		return null;
	}

	@Override
	public Void visit(And expr) {
		binaryExpressionTraversal(expr);
		return null;
	}

	@Override
	public Void visit(Or expr) {
		binaryExpressionTraversal(expr);
		return null;
	}

	@Override
	public Void visit(Not expr) {
		unaryExpressionTraversal(expr);
		return null;
	}

	@Override
	public Void visit(Parenthesis expr) {
		unaryExpressionTraversal(expr);
		return null;
	}

	@Override
	public Void visit(Equal expr) {
		binaryExpressionTraversal(expr);
		return null;
	}

	@Override
	public Void visit(NotEqual expr) {
		binaryExpressionTraversal(expr);
		return null;
	}

	@Override
	public Void visit(Division expr) {
		binaryExpressionTraversal(expr);
		return null;
	}

	@Override
	public Void visit(Multiplication expr) {
		binaryExpressionTraversal(expr);
		return null;
	}

	@Override
	public Void visit(Greater expr) {
		binaryExpressionTraversal(expr);
		return null;
	}

	@Override
	public Void visit(GreaterEqual expr) {
		binaryExpressionTraversal(expr);
		return null;
	}

	@Override
	public Void visit(Less expr) {
		binaryExpressionTraversal(expr);
		return null;
	}

	@Override
	public Void visit(LessEqual expr) {
		binaryExpressionTraversal(expr);
		return null;
	}

	@Override
	public Void visit(Identifier expr) {
		identifiers.add(expr);
		return null;
	}

	@Override
	public Void visit(BooleanLit expr) {
		return null;
	}

	@Override
	public Void visit(IntegerLit expr) {
		return null;
	}

	@Override
	public Void visit(StringLit expr) {
		return null;
	}

	@Override
	public Void visit(MoneyLit expr) {
		return null;
	}
	
	private void binaryExpressionTraversal(Binary expr) {
		expr.getLeft().accept(this);
		expr.getRight().accept(this);
	}
	
	private void unaryExpressionTraversal(Unary expr) {
		expr.getExpr().accept(this);
	}
	
	public Set<Identifier> getIdentifiers() {
		return identifiers;
	}

}
