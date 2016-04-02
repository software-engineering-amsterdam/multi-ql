package nl.nicasso.ql.visitors;

import nl.nicasso.ql.ast.nodes.expressions.Identifier;
import nl.nicasso.ql.ast.nodes.expressions.Parenthesis;
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

public interface ExpressionVisitor<T, U> {

	public T visit(Addition expression, U context);
	public T visit(Subtraction expression, U context);
	public T visit(And expression, U context);
	public T visit(Or expression, U context);
	public T visit(Not expression, U context);
	public T visit(Parenthesis expression, U context);
	public T visit(Equal expression, U context);
	public T visit(NotEqual expression, U context);
	public T visit(Division expression, U context);
	public T visit(Multiplication expression, U context);
	public T visit(Greater expression, U context);
	public T visit(GreaterEqual expression, U context);
	public T visit(Less expression, U context);
	public T visit(LessEqual expression, U context);
	public T visit(Identifier identifier, U context);
	public T visit(BooleanLiteral literal, U context);
	public T visit(IntegerLiteral literal, U context);
	public T visit(StringLiteral literal, U context);
	public T visit(MoneyLiteral literal, U context);

}