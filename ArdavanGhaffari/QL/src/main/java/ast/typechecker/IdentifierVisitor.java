package ast.typechecker;

import java.util.LinkedList;
import java.util.List;

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
import ast.model.literal.Identifier;
import ast.model.literal.IntegerLiteral;
import ast.model.literal.StringLiteral;
import ast.model.unaryexpression.Negation;
import ast.visitor.ExpressionVisitor;

public class IdentifierVisitor implements ExpressionVisitor<List<Identifier>> {

	private List<Identifier> identifiers;
	
	public IdentifierVisitor() {
		identifiers = new LinkedList<>();
	}
	
	@Override
	public List<Identifier> visit(Negation negation) {
		return negation.getExpression().accept(this);
	}

	@Override
	public List<Identifier> visit(Addition addition) {
		return visitBinaryExpression(addition);
	}

	@Override
	public List<Identifier> visit(Subtraction subtraction) {
		return visitBinaryExpression(subtraction);
	}

	@Override
	public List<Identifier> visit(Multiplication multiplication) {
		return visitBinaryExpression(multiplication);
	}

	@Override
	public List<Identifier> visit(Division division) {
		return visitBinaryExpression(division);
	}

	@Override
	public List<Identifier> visit(Conjunction conjunction) {
		return visitBinaryExpression(conjunction);
	}

	@Override
	public List<Identifier> visit(Disjunction disjunction) {
		return visitBinaryExpression(disjunction);
	}

	@Override
	public List<Identifier> visit(Equal equal) {
		return visitBinaryExpression(equal);
	}

	@Override
	public List<Identifier> visit(NotEqual notEqual) {
		return visitBinaryExpression(notEqual);
	}

	@Override
	public List<Identifier> visit(GreaterThan greaterThan) {
		return visitBinaryExpression(greaterThan);
	}

	@Override
	public List<Identifier> visit(GreaterThanEqual greaterThanEqual) {
		return visitBinaryExpression(greaterThanEqual);
	}

	@Override
	public List<Identifier> visit(LessThan LessThan) {
		return visitBinaryExpression(LessThan);
	}

	@Override
	public List<Identifier> visit(LessThanEqual LessThanEqual) {
		return visitBinaryExpression(LessThanEqual);
	}

	@Override
	public List<Identifier> visit(BooleanLiteral booleanLiteral) {
		return identifiers;
	}

	@Override
	public List<Identifier> visit(StringLiteral stringLiteral) {
		return identifiers;
	}

	@Override
	public List<Identifier> visit(IntegerLiteral integerLiteral) {
		return identifiers;
	}

	@Override
	public List<Identifier> visit(Identifier identifier) {
		identifiers.add(identifier);
		return identifiers;
	}
	
	private List<Identifier> visitBinaryExpression(BinaryExpression expression) {
		expression.getLeftExpression().accept(this);
		expression.getRightExpression().accept(this);
		return identifiers;
	}

}
