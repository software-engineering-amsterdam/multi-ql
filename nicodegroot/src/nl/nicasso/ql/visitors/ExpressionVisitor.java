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

public interface ExpressionVisitor<T> {
	
	public T visit(Addition expression);
	public T visit(Subtraction expression);
	public T visit(And expression);
	public T visit(Or expression);
	public T visit(Not expression);
	public T visit(Parenthesis expression);
	public T visit(Equal expression);
	public T visit(NotEqual expression);
	public T visit(Division expression);
	public T visit(Multiplication expression);
	public T visit(Greater expression);
	public T visit(GreaterEqual expression);
	public T visit(Less expression);
	public T visit(LessEqual expression);
	
	public T visit(Identifier identifier);
	
	public T visit(BooleanLiteral literal);
	public T visit(IntegerLiteral literal);
	public T visit(StringLiteral literal);
	public T visit(MoneyLiteral literal);

}
