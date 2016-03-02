package semanticAction.evaluation;

import semanticAction.evaluation.ValueHolder;

import semanticAction.tree.expressionNode.calculation.Add;
import semanticAction.tree.expressionNode.calculation.Division;
import semanticAction.tree.expressionNode.calculation.SUB;
import semanticAction.tree.expressionNode.calculation.Time;
import semanticAction.tree.expressionNode.comparison.Equal;
import semanticAction.tree.expressionNode.comparison.GreaterEqual;
import semanticAction.tree.expressionNode.comparison.GreaterThan;
import semanticAction.tree.expressionNode.comparison.LessEqual;
import semanticAction.tree.expressionNode.comparison.LessThan;
import semanticAction.tree.expressionNode.comparison.NotEqual;
import semanticAction.tree.expressionNode.literal.Booleanliteral;
import semanticAction.tree.expressionNode.literal.Identifier;
import semanticAction.tree.expressionNode.literal.Integerliteral;
import semanticAction.tree.expressionNode.literal.Stringliteral;
import semanticAction.tree.expressionNode.logical.And;
import semanticAction.tree.expressionNode.logical.OR;
import semanticAction.tree.expressionNode.unary.Minus;
import semanticAction.tree.expressionNode.unary.NOT;
import semanticAction.tree.expressionNode.unary.Plus;
import semanticAction.tree.intermediate.InterfaceVisitExpression;

public class EvalVisit implements InterfaceVisitExpression<Value> {

	private final ValueHolder valueHolder;
	public EvalVisit(ValueHolder valueHolder){
		this.valueHolder=valueHolder;
	}
	
	@Override
	public Value visit(Identifier e) {
		
		return  valueHolder.getValue(e.getID());
	}

	@Override
	public Value visit(Booleanliteral bool) {
		
		return new BooleanValue(bool.getVariable());
	}

	@Override
	public Value visit(Stringliteral string) {
		
		return new StringValue(string.getVariable());
	}

	@Override
	public Value visit(Integerliteral integer) {
		
		return new IntegerValue(integer.getVariable());
	}

	@Override
	public Value visit(And e) {
		
	Value left=e.getLeftExpression().accept(this);
	Value right=e.getRightExpression().accept(this);
	return  left.and(right);
	}
	
	@Override
	public Value visit(OR orExpr) {
		Value left=orExpr.getLeftExpression().accept(this);
		Value right=orExpr.getRightExpression().accept(this);
		return left.or(right);
	}
	@Override
	public Value visit(Time mulExpr) {
		Value left=mulExpr.getLeftExpression().accept(this);
		Value right=mulExpr.getRightExpression().accept(this);
		return  left.time(right);
	}

	@Override
	public Value visit(Division divisionExpr) {
		Value left=divisionExpr.getLeftExpression().accept(this);
		Value right=divisionExpr.getRightExpression().accept(this);
		return  left.div(right);
	}
	@Override
	public Value visit(Add addExpr) {
		Value left=addExpr.getLeftExpression().accept(this);
		Value right=addExpr.getRightExpression().accept(this);
		return  left.add(right);
	}

	@Override
	public Value visit(SUB subExpr) {
		Value left=subExpr.getLeftExpression().accept(this);
		Value right=subExpr.getRightExpression().accept(this);
		return  left.sub(right);
	}
	@Override
	public Value visit(NotEqual notExpr) {
		Value left=notExpr.getLeftExpression().accept(this);
		Value right=notExpr.getRightExpression().accept(this);
		return  left.notEqual(right);
	}

	@Override
	public Value visit(LessEqual lessEqualExpr) {
		
		Value left=lessEqualExpr.getLeftExpression().accept(this);
		Value right=lessEqualExpr.getRightExpression().accept(this);
		return  left.lessEqual(right);
	}

	@Override
	public Value visit(LessThan lessThanExpr) {
		Value left=lessThanExpr.getLeftExpression().accept(this);
		Value right=lessThanExpr.getRightExpression().accept(this);
		return  left.lessThan(right);
	}
	
	
	
	@Override
	public Value visit(GreaterThan greaterThanExpr) {
		Value left=greaterThanExpr.getLeftExpression().accept(this);
		Value right=greaterThanExpr.getRightExpression().accept(this);
		return  left.greaterThan(right);
	}

	@Override
	public Value visit(Plus plusExpr) {
		Value value=plusExpr.getUnaryExpression().accept(this);

		
		return value.plus();
	}

	@Override
	public Value visit(Minus minusExpr) {
		Value value= minusExpr.getUnaryExpression().accept(this);
		return value.minus();
	}

	@Override
	public Value visit(NOT notExpr) {
		Value value=notExpr.getUnaryExpression().accept(this);
		return value.not();
	}

	@Override
	public Value visit(GreaterEqual greaterExpr) {
		Value left= greaterExpr.getLeftExpression().accept(this);
		Value right=greaterExpr.getRightExpression().accept(this);
		return left.greaterThan(right);
	}

	@Override
	public Value visit(Equal equalExpr) {
		Value left=equalExpr.getLeftExpression().accept(this);
		Value right=equalExpr.getRightExpression().accept(this);
		return left.equal(right);
	}
}
	
