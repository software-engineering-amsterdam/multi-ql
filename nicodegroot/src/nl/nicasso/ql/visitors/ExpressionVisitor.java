package nl.nicasso.ql.visitors;

import nl.nicasso.ql.ast.expressions.Identifier;
import nl.nicasso.ql.ast.expressions.Parenthesis;
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
	
	public T visit(BooleanLit value);
	public T visit(IntegerLit value);
	public T visit(StringLit value);
	public T visit(MoneyLit value);

}
