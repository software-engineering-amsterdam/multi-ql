package ast.visitor;

import ast.model.binaryexpression.Addition;
import ast.model.binaryexpression.Conjunction;
import ast.model.binaryexpression.Disjunction;
import ast.model.binaryexpression.Division;
import ast.model.binaryexpression.Equal;
import ast.model.binaryexpression.GreaterThan;
import ast.model.binaryexpression.GreaterThanEqual;
import ast.model.binaryexpression.LessThan;
import ast.model.binaryexpression.LessThanEqual;
import ast.model.binaryexpression.Multiplication;
import ast.model.binaryexpression.NotEqual;
import ast.model.binaryexpression.Subtraction;
import ast.model.literal.BooleanLiteral;
import ast.model.literal.DecimalLiteral;
import ast.model.literal.Identifier;
import ast.model.literal.IntegerLiteral;
import ast.model.literal.StringLiteral;
import ast.model.unaryexpression.Negation;

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
	public T visit(DecimalLiteral decimalLiteral);
	public T visit(IntegerLiteral integerLiteral);
	public T visit(Identifier identifier);
}
