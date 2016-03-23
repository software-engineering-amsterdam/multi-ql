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
	public Void visit(Addition expression) {
		binaryExpressionTraversal(expression);
		return null;
	}

	@Override
	public Void visit(Subtraction expression) {
		binaryExpressionTraversal(expression);
		return null;
	}

	@Override
	public Void visit(And expression) {
		binaryExpressionTraversal(expression);
		return null;
	}

	@Override
	public Void visit(Or expression) {
		binaryExpressionTraversal(expression);
		return null;
	}

	@Override
	public Void visit(Not expression) {
		unaryExpressionTraversal(expression);
		return null;
	}

	@Override
	public Void visit(Parenthesis expression) {
		unaryExpressionTraversal(expression);
		return null;
	}

	@Override
	public Void visit(Equal expression) {
		binaryExpressionTraversal(expression);
		return null;
	}

	@Override
	public Void visit(NotEqual expression) {
		binaryExpressionTraversal(expression);
		return null;
	}

	@Override
	public Void visit(Division expression) {
		binaryExpressionTraversal(expression);
		return null;
	}

	@Override
	public Void visit(Multiplication expression) {
		binaryExpressionTraversal(expression);
		return null;
	}

	@Override
	public Void visit(Greater expression) {
		binaryExpressionTraversal(expression);
		return null;
	}

	@Override
	public Void visit(GreaterEqual expression) {
		binaryExpressionTraversal(expression);
		return null;
	}

	@Override
	public Void visit(Less expression) {
		binaryExpressionTraversal(expression);
		return null;
	}

	@Override
	public Void visit(LessEqual expression) {
		binaryExpressionTraversal(expression);
		return null;
	}

	@Override
	public Void visit(Identifier expression) {
		identifiers.add(expression);
		return null;
	}

	@Override
	public Void visit(BooleanLiteral expression) {
		return null;
	}

	@Override
	public Void visit(IntegerLiteral expression) {
		return null;
	}

	@Override
	public Void visit(StringLiteral expression) {
		return null;
	}

	@Override
	public Void visit(MoneyLiteral expression) {
		return null;
	}
	
	private void binaryExpressionTraversal(Binary expression) {
		expression.getLeft().accept(this);
		expression.getRight().accept(this);
	}
	
	private void unaryExpressionTraversal(Unary expression) {
		expression.getExpr().accept(this);
	}
	
	public Set<Identifier> getIdentifiers() {
		return identifiers;
	}

}
