package nl.uva.ql.visitors;

import nl.uva.ql.ast.expression.binaryexpression.Addition;
import nl.uva.ql.ast.expression.binaryexpression.Conjunction;
import nl.uva.ql.ast.expression.binaryexpression.Disjunction;
import nl.uva.ql.ast.expression.binaryexpression.Division;
import nl.uva.ql.ast.expression.binaryexpression.Equal;
import nl.uva.ql.ast.expression.binaryexpression.GreaterThan;
import nl.uva.ql.ast.expression.binaryexpression.GreaterThanEqual;
import nl.uva.ql.ast.expression.binaryexpression.LessThan;
import nl.uva.ql.ast.expression.binaryexpression.LessThanEqual;
import nl.uva.ql.ast.expression.binaryexpression.Multiplication;
import nl.uva.ql.ast.expression.binaryexpression.NotEqual;
import nl.uva.ql.ast.expression.binaryexpression.Subtraction;
import nl.uva.ql.ast.expression.unaryexpression.Negation;
import nl.uva.ql.ast.literal.BooleanLiteral;
import nl.uva.ql.ast.literal.Identifier;
import nl.uva.ql.ast.literal.IntegerLiteral;
import nl.uva.ql.ast.literal.MoneyLiteral;
import nl.uva.ql.ast.literal.StringLiteral;

public interface ExpressionVisitor<T> {
	public T visit(Negation negation);
	public T visit(Addition addition);
	public T visit(Subtraction subtraction);
	public T visit(Multiplication multiplication);
	public T visit(Division division);
	public T visit(Conjunction conjunction);
	public T visit(Disjunction disjunction);
	public T visit(Equal equal);
	public T visit(NotEqual notEqual);
	public T visit(GreaterThan greaterThan);
	public T visit(GreaterThanEqual greaterThanEqual);
	public T visit(LessThan lessThan);
	public T visit(LessThanEqual lessThanEqual);
	public T visit(BooleanLiteral booleanLiteral);
	public T visit(StringLiteral stringLiteral);
	public T visit(MoneyLiteral moneyLiteral);
	public T visit(IntegerLiteral integerLiteral);
	public T visit(Identifier identifier);
}
