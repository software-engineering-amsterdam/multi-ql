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

public class CollectIdentifiers implements ExpressionVisitor<Void, Void> {

	private Set<Identifier> identifiers;

	public CollectIdentifiers() {
		identifiers = new HashSet<Identifier>();
	}
	
	private void binaryExpressionTraversal(Binary expression, Void context) {
		expression.getLeft().accept(this, null);
		expression.getRight().accept(this, null);
	}

	private void unaryExpressionTraversal(Unary expression, Void context) {
		expression.getExpression().accept(this, null);
	}

	public Set<Identifier> getIdentifiers() {
		return identifiers;
	}

	@Override
	public Void visit(Addition expression, Void context) {
		binaryExpressionTraversal(expression, context);
		return null;
	}

	@Override
	public Void visit(Subtraction expression, Void context) {
		binaryExpressionTraversal(expression, context);
		return null;
	}

	@Override
	public Void visit(And expression, Void context) {
		binaryExpressionTraversal(expression, context);
		return null;
	}

	@Override
	public Void visit(Or expression, Void context) {
		binaryExpressionTraversal(expression, context);
		return null;
	}

	@Override
	public Void visit(Not expression, Void context) {
		unaryExpressionTraversal(expression, context);
		return null;
	}

	@Override
	public Void visit(Parenthesis expression, Void context) {
		unaryExpressionTraversal(expression, context);
		return null;
	}

	@Override
	public Void visit(Equal expression, Void context) {
		binaryExpressionTraversal(expression, context);
		return null;
	}

	@Override
	public Void visit(NotEqual expression, Void context) {
		binaryExpressionTraversal(expression, context);
		return null;
	}

	@Override
	public Void visit(Division expression, Void context) {
		binaryExpressionTraversal(expression, context);
		return null;
	}

	@Override
	public Void visit(Multiplication expression, Void context) {
		binaryExpressionTraversal(expression, context);
		return null;
	}

	@Override
	public Void visit(Greater expression, Void context) {
		binaryExpressionTraversal(expression, context);
		return null;
	}

	@Override
	public Void visit(GreaterEqual expression, Void context) {
		binaryExpressionTraversal(expression, context);
		return null;
	}

	@Override
	public Void visit(Less expression, Void context) {
		binaryExpressionTraversal(expression, context);
		return null;
	}

	@Override
	public Void visit(LessEqual expression, Void context) {
		binaryExpressionTraversal(expression, context);
		return null;
	}

	@Override
	public Void visit(Identifier expression, Void context) {
		identifiers.add(expression);
		return null;
	}

	@Override
	public Void visit(BooleanLiteral expression, Void context) {
		return null;
	}

	@Override
	public Void visit(IntegerLiteral expression, Void context) {
		return null;
	}

	@Override
	public Void visit(StringLiteral expression, Void context) {
		return null;
	}

	@Override
	public Void visit(MoneyLiteral expression, Void context) {
		return null;
	}

}