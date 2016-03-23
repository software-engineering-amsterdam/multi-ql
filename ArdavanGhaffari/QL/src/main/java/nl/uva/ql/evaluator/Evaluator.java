package nl.uva.ql.evaluator;

import java.util.HashMap;

import javax.swing.JOptionPane;

import nl.uva.ql.ast.expression.Expression;
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
import nl.uva.ql.evaluator.value.BooleanValue;
import nl.uva.ql.evaluator.value.IntegerValue;
import nl.uva.ql.evaluator.value.MoneyValue;
import nl.uva.ql.evaluator.value.StringValue;
import nl.uva.ql.evaluator.value.UnknownValue;
import nl.uva.ql.evaluator.value.Value;
import nl.uva.ql.visitors.ExpressionVisitor;

public class Evaluator implements ExpressionVisitor<Value> {
	
	private HashMap<String, Value> identifierValueMap = new HashMap<>();
	
	public Value evaluate(Expression expression){
		return expression.accept(this);
	}

	@Override
	public Value visit(Negation negation) {
		Value operand = negation.getExpression().accept(this);
		return operand.negate();
	}

	@Override
	public Value visit(Addition addition) {
		Value leftOperand = addition.getLeftExpression().accept(this);
		Value rightOperand = addition.getRightExpression().accept(this);
		return leftOperand.add(rightOperand);
	}

	@Override
	public Value visit(Subtraction subtraction) {
		Value leftOperand = subtraction.getLeftExpression().accept(this);
		Value rightOperand = subtraction.getRightExpression().accept(this);
		return leftOperand.subtract(rightOperand);
	}

	@Override
	public Value visit(Multiplication multiplication) {
		Value leftOperand = multiplication.getLeftExpression().accept(this);
		Value rightOperand = multiplication.getRightExpression().accept(this);
		return leftOperand.multiply(rightOperand);
	}

	@Override
	public Value visit(Division division) {
		Value leftOperand = division.getLeftExpression().accept(this);
		Value rightOperand = division.getRightExpression().accept(this);
		
		Value isZero = rightOperand.equal(new IntegerValue(0));
		if (!isZero.isUnknownValue() && ((BooleanValue) isZero).getValue()) {
			JOptionPane.showMessageDialog(null, "In Divide operation, left hand operand should not be zero");
			return new UnknownValue();
		}
		
		return leftOperand.divide(rightOperand);
	}

	@Override
	public Value visit(Conjunction conjunction) {
		Value leftOperand = conjunction.getLeftExpression().accept(this);
		Value rightOperand = conjunction.getRightExpression().accept(this);
		return leftOperand.and(rightOperand);
	}

	@Override
	public Value visit(Disjunction disjunction) {
		Value leftOperand = disjunction.getLeftExpression().accept(this);
		Value rightOperand = disjunction.getRightExpression().accept(this);
		return leftOperand.or(rightOperand);
	}

	@Override
	public Value visit(Equal equal) {
		Value leftOperand = equal.getLeftExpression().accept(this);
		Value rightOperand = equal.getRightExpression().accept(this);
		return leftOperand.equal(rightOperand);
	}

	@Override
	public Value visit(NotEqual notEqual) {
		Value leftOperand = notEqual.getLeftExpression().accept(this);
		Value rightOperand = notEqual.getRightExpression().accept(this);
		return leftOperand.notEqual(rightOperand);
	}

	@Override
	public Value visit(GreaterThan greaterThan) {
		Value leftOperand = greaterThan.getLeftExpression().accept(this);
		Value rightOperand = greaterThan.getRightExpression().accept(this);
		return leftOperand.greaterThan(rightOperand);
	}

	@Override
	public Value visit(GreaterThanEqual greaterThanEqual) {
		Value leftOperand = greaterThanEqual.getLeftExpression().accept(this);
		Value rightOperand = greaterThanEqual.getRightExpression().accept(this);
		return leftOperand.greaterThanEqual(rightOperand);
	}

	@Override
	public Value visit(LessThan lessThan) {
		Value leftOperand = lessThan.getLeftExpression().accept(this);
		Value rightOperand = lessThan.getRightExpression().accept(this);
		return leftOperand.lessThan(rightOperand);
	}

	@Override
	public Value visit(LessThanEqual lessThanEqual) {
		Value leftOperand = lessThanEqual.getLeftExpression().accept(this);
		Value rightOperand = lessThanEqual.getRightExpression().accept(this);
		return leftOperand.lessThanEqual(rightOperand);
	}

	@Override
	public Value visit(BooleanLiteral booleanLiteral) {
		return new BooleanValue(booleanLiteral.getValue());
	}

	@Override
	public Value visit(StringLiteral stringLiteral) {
		return new StringValue(stringLiteral.getValue());
	}

	@Override
	public Value visit(MoneyLiteral moneyLiteral) {
		return new MoneyValue(moneyLiteral.getValue());
	}

	@Override
	public Value visit(IntegerLiteral integerLiteral) {
		return new IntegerValue(integerLiteral.getValue());
	}

	@Override
	public Value visit(Identifier identifier) {
		return getValue(identifier);
	}
	
	public void addValue(Identifier identifier, Value value) {
		identifierValueMap.put(identifier.getName(), value);
	}
	
	public Value getValue(Identifier identifier) {
		String identifierString = identifier.getName();
		if (identifierValueMap.containsKey(identifierString)) {
			return identifierValueMap.get(identifierString);
		}
		return new UnknownValue();
	}

}
