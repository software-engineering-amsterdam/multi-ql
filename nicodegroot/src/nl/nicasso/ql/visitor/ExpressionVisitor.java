package nl.nicasso.ql.visitor;

import nl.nicasso.ql.ast.expression.Identifier;
import nl.nicasso.ql.ast.expression.Parenthesis;
import nl.nicasso.ql.ast.expression.additive.Addition;
import nl.nicasso.ql.ast.expression.additive.Subtraction;
import nl.nicasso.ql.ast.expression.conditional.And;
import nl.nicasso.ql.ast.expression.conditional.Not;
import nl.nicasso.ql.ast.expression.conditional.Or;
import nl.nicasso.ql.ast.expression.equality.Equal;
import nl.nicasso.ql.ast.expression.equality.NotEqual;
import nl.nicasso.ql.ast.expression.multiplicative.Division;
import nl.nicasso.ql.ast.expression.multiplicative.Multiplication;
import nl.nicasso.ql.ast.expression.relational.Greater;
import nl.nicasso.ql.ast.expression.relational.GreaterEqual;
import nl.nicasso.ql.ast.expression.relational.Less;
import nl.nicasso.ql.ast.expression.relational.LessEqual;
import nl.nicasso.ql.ast.literal.BooleanLit;
import nl.nicasso.ql.ast.literal.DecimalLit;
import nl.nicasso.ql.ast.literal.IntegerLit;
import nl.nicasso.ql.ast.literal.StringLit;

public interface ExpressionVisitor<T> extends Visitor<T> {
	
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
	public T visit(DecimalLit value);

}
