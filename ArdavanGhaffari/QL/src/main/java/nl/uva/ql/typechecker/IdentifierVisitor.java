package nl.uva.ql.typechecker;

import java.util.HashSet;
import java.util.Set;

import nl.uva.ql.ast.expression.binaryexpression.Addition;
import nl.uva.ql.ast.expression.binaryexpression.BinaryExpression;
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
import nl.uva.ql.visitors.ExpressionVisitor;

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
		identifiers.add(identifier.getName());
		return identifiers;
	}
	
	@Override 
	public Set<String> visit(MoneyLiteral moneyLiteral) {
		return identifiers;
	}
	
	private Set<String> visitBinaryExpression(BinaryExpression expression) {
		expression.getLeftExpression().accept(this);
		expression.getRightExpression().accept(this);
		return identifiers;
	}

}
