package ast.typechecker;

import java.util.HashSet;
import java.util.Set;

import ast.model.binaryexpression.Addition;
import ast.model.binaryexpression.BinaryExpression;
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
import ast.visitor.ExpressionVisitor;

public class IdentifierVisitor implements ExpressionVisitor<Set<String>> {

	private Set<String> identifiers;
	
	public IdentifierVisitor() {
		identifiers = new HashSet<>();
	}
	
	@Override
	public Set<String> visit(Negation negation) {
		return negation.getExpression().accept(this);
	}

	@Override
	public Set<String> visit(Addition addition) {
		return visitBinaryExpression(addition);
	}

	@Override
	public Set<String> visit(Subtraction subtraction) {
		return visitBinaryExpression(subtraction);
	}

	@Override
	public Set<String> visit(Multiplication multiplication) {
		return visitBinaryExpression(multiplication);
	}

	@Override
	public Set<String> visit(Division division) {
		return visitBinaryExpression(division);
	}

	@Override
	public Set<String> visit(Conjunction conjunction) {
		return visitBinaryExpression(conjunction);
	}

	@Override
	public Set<String> visit(Disjunction disjunction) {
		return visitBinaryExpression(disjunction);
	}

	@Override
	public Set<String> visit(Equal equal) {
		return visitBinaryExpression(equal);
	}

	@Override
	public Set<String> visit(NotEqual notEqual) {
		return visitBinaryExpression(notEqual);
	}

	@Override
	public Set<String> visit(GreaterThan greaterThan) {
		return visitBinaryExpression(greaterThan);
	}

	@Override
	public Set<String> visit(GreaterThanEqual greaterThanEqual) {
		return visitBinaryExpression(greaterThanEqual);
	}

	@Override
	public Set<String> visit(LessThan LessThan) {
		return visitBinaryExpression(LessThan);
	}

	@Override
	public Set<String> visit(LessThanEqual LessThanEqual) {
		return visitBinaryExpression(LessThanEqual);
	}

	@Override
	public Set<String> visit(BooleanLiteral booleanLiteral) {
		return identifiers;
	}

	@Override
	public Set<String> visit(StringLiteral stringLiteral) {
		return identifiers;
	}

	@Override
	public Set<String> visit(IntegerLiteral integerLiteral) {
		return identifiers;
	}

	@Override
	public Set<String> visit(Identifier identifier) {
		identifiers.add(identifier.getIdentifier());
		return identifiers;
	}
	
	@Override 
	public Set<String> visit(DecimalLiteral decimalLiteral) {
		return identifiers;
	}
	
	private Set<String> visitBinaryExpression(BinaryExpression expression) {
		expression.getLeftExpression().accept(this);
		expression.getRightExpression().accept(this);
		return identifiers;
	}

}
