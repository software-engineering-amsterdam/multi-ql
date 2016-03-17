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
	
	public T visit(Addition value);
	public T visit(Subtraction value);
	public T visit(And value);
	public T visit(Or value);
	public T visit(Not value);
	public T visit(Parenthesis value);
	public T visit(Equal value);
	public T visit(NotEqual value);
	public T visit(Division value);
	public T visit(Multiplication value);
	public T visit(Greater value);
	public T visit(GreaterEqual value);
	public T visit(Less value);
	public T visit(LessEqual value);
	
	public T visit(Identifier value);
	
	public T visit(BooleanLiteral value);
	public T visit(IntegerLiteral value);
	public T visit(StringLiteral value);
	public T visit(MoneyLiteral value);

}
