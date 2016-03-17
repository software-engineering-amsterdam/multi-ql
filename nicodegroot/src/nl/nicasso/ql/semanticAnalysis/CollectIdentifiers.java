package nl.nicasso.ql.semanticAnalysis;

import java.util.HashSet;
import java.util.Set;

import nl.nicasso.ql.ast.nodes.expressions.Binary;
import nl.nicasso.ql.ast.nodes.expressions.Identifier;
import nl.nicasso.ql.ast.nodes.expressions.Parenthesis;
import nl.nicasso.ql.ast.nodes.expressions.Unary;
import nl.nicasso.ql.ast.nodes.expressions.additive.Addition;
import nl.nicasso.ql.ast.nodes.expressions.additive.Subtraction;
import nl.nicasso.ql.ast.nodes.expressions.conditional.And;
import nl.nicasso.ql.ast.nodes.expressions.conditional.Not;
import nl.nicasso.ql.ast.nodes.expressions.conditional.Or;
import nl.nicasso.ql.ast.nodes.expressions.equality.Equal;
import nl.nicasso.ql.ast.nodes.expressions.equality.NotEqual;
import nl.nicasso.ql.ast.nodes.expressions.multiplicative.Division;
import nl.nicasso.ql.ast.nodes.expressions.multiplicative.Multiplication;
import nl.nicasso.ql.ast.nodes.expressions.relational.Greater;
import nl.nicasso.ql.ast.nodes.expressions.relational.GreaterEqual;
import nl.nicasso.ql.ast.nodes.expressions.relational.Less;
import nl.nicasso.ql.ast.nodes.expressions.relational.LessEqual;
import nl.nicasso.ql.ast.nodes.literals.BooleanLiteral;
import nl.nicasso.ql.ast.nodes.literals.IntegerLiteral;
import nl.nicasso.ql.ast.nodes.literals.MoneyLiteral;
import nl.nicasso.ql.ast.nodes.literals.StringLiteral;
import nl.nicasso.ql.visitors.ExpressionVisitor;

public class CollectIdentifiers implements ExpressionVisitor<Void> {
	
	private Set<Identifier> identifiers;
	
	public CollectIdentifiers() {
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
	public Void visit(BooleanLiteral expr) {
		return null;
	}

	@Override
	public Void visit(IntegerLiteral expr) {
		return null;
	}

	@Override
	public Void visit(StringLiteral expr) {
		return null;
	}

	@Override
	public Void visit(MoneyLiteral expr) {
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
