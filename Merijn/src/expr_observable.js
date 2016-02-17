import * as ast from 'src/ast';
import { Observable } from 'src/observable';
import { Scope } from 'src/scope';

class ExprObservable extends Observable {
	constructor(value) {
		this.value = value;
	}
	getValue() {
		return this.value;
	}
	getType() {
		throw new Error("Override in subclasses");
	}
}

class ExprObservingObservable extends ExprObservable {
	notify() {
		throw new Error("Override in subclasses");
	}
	/** @protected */
	updateValue(newValue) {
		if (this.value !== newValue) {
			this.value = newValue;
			this.notifyObservers();
		}
	}
}

class UnaryPrefixObservable extends ExprObservingObservable {
	constructor(operandObservable) {
		super(this.applyUnaryPrefixOperation(operandObservable.getValue()));
		this.operandObservable = operandObservable;
		this.operandObservable.registerObserver(this);
	}
	notify() {
		this.updateValue(this.applyUnaryPrefixOperation(this.operandObservable.getValue()));
	}
	applyUnaryPrefixOperation(operandValue) {
		throw new Error("Override in subclasses");
	}
}

class NegationObservable extends UnaryPrefixObservable {
	applyUnaryPrefixOperation(operandValue) {
		return -operandValue;
	}
	getType() {
		return this.operandObservable.getType();
	}
}

class NotObservable extends UnaryPrefixObservable {
	applyUnaryPrefixOperation(operandValue) {
		return !operandValue;
	}
	getType() {
		return ast.TYPE_BOOLEAN;
	}
}

class InfixObservable extends ExprObservingObservable {
	constructor(leftOperandObservable, rightOperandObservable) {
		super(this.applyInfixOperation(leftOperandObservable.getValue(), rightOperandObservable.getValue()));
		this.leftOperandObservable = leftOperandObservable;
		this.leftOperandObservable.registerObserver(this);
		this.rightOperandObservable = rightOperandObservable;
		this.rightOperandObservable.registerObserver(this);
	}
	notify() {
		this.updateValue(this.applyInfixOperation(this.leftOperandObservable.getValue(), this.rightOperandObservable.getValue()));
	}
	getValue() {
		return this.applyInfixOperation(this.leftOperandObservable.getValue(), this.rightOperandObservable.getValue());
	}
	applyInfixOperation(leftOperandValue, rightOperandValue) {
		throw new Error("Override in subclasses");
	}
}

class AddObservable extends InfixObservable {
	applyInfixOperation(leftOperandValue, rightOperandValue) {
		return leftOperandValue + rightOperandValue;
	}
	getType() {
		return this.leftOperandObservable.getType();
	}
}

class SubtractObservable extends InfixObservable {
	applyInfixOperation(leftOperandValue, rightOperandValue) {
		return leftOperandValue - rightOperandValue;
	}
	getType() {
		return this.leftOperandObservable.getType();
	}
}

class MultiplyObservable extends InfixObservable {
	applyInfixOperation(leftOperandValue, rightOperandValue) {
		return leftOperandValue * rightOperandValue;
	}
	getType() {
		return this.leftOperandObservable.getType();
	}
}

class DivideObservable extends InfixObservable {
	applyInfixOperation(leftOperandValue, rightOperandValue) {
		return leftOperandValue / rightOperandValue;
	}
	getType() {
		return this.leftOperandObservable.getType();
	}
}

class ComparingObservable extends InfixObservable {
	getType() {
		return ast.TYPE_BOOLEAN;
	}
}

class GreaterObservable extends ComparingObservable {
	applyInfixOperation(leftOperandValue, rightOperandValue) {
		return leftOperandValue > rightOperandValue;
	}
}

class GreaterEqualObservable extends ComparingObservable {
	applyInfixOperation(leftOperandValue, rightOperandValue) {
		return leftOperandValue >= rightOperandValue;
	}
}

class LessObservable extends ComparingObservable {
	applyInfixOperation(leftOperandValue, rightOperandValue) {
		return leftOperandValue < rightOperandValue;
	}
}

class LessEqualObservable extends ComparingObservable {
	applyInfixOperation(leftOperandValue, rightOperandValue) {
		return leftOperandValue <= rightOperandValue;
	}
}

class EqualObservable extends ComparingObservable {
	applyInfixOperation(leftOperandValue, rightOperandValue) {
		return leftOperandValue === rightOperandValue;
	}
}

class NotEqualObservable extends ComparingObservable {
	applyInfixOperation(leftOperandValue, rightOperandValue) {
		return leftOperandValue !== rightOperandValue;
	}
}

class AndObservable extends ComparingObservable {
	applyInfixOperation(leftOperandValue, rightOperandValue) {
		return leftOperandValue && rightOperandValue;
	}
}

class OrObservable extends ComparingObservable {
	applyInfixOperation(leftOperandValue, rightOperandValue) {
		return leftOperandValue || rightOperandValue;
	}
}

class LiteralObservable extends ExprObservable {
	constructor(value, type) {
		super(value);
		this.type = type;
	}
	getType() {
		return this.type;
	}
}

class BooleanLiteralObservable extends LiteralObservable {
	constructor(value) {
		super(value, ast.TYPE_BOOLEAN);
	}
}

class StringLiteralObservable extends LiteralObservable {
	constructor(value) {
		super(value, ast.TYPE_STRING);
	}
}

class IntegerLiteralObservable extends LiteralObservable {
	constructor(value) {
		super(value, ast.TYPE_INTEGER);
	}
}

class FloatLiteralObservable extends LiteralObservable {
	constructor(value) {
		super(value, ast.TYPE_FLOAT);
	}
}

class MoneyLiteralObservable extends LiteralObservable {
	constructor(value) {
		super(value, ast.TYPE_MONEY);
	}
}

class ExprObservableFactoryVisitor extends ast.NodeVisitor {
	visitNegationNode (negationNode, scope) {
		return new NegationObservable(negationNode.operand.accept(this, scope));
	}
	visitNotNode (notNode, scope) {
		return new NotObservable(notNode.operand.accept(this, scope));
	}

	createInfixOperandObservables(infixNode, scope) {
		return [infixNode.leftOperand.accept(this, scope), infixNode.rightOperand.accept(this, scope)];
	}
	visitAddNode (addNode, scope) {
		return new AddObservable(...this.createInfixOperandObservables(addNode, scope));
	}
	visitSubtractNode (subtractNode, scope) {
		return new SubtractObservable(...this.createInfixOperandObservables(subtractNode, scope));
	}
	visitMultiplyNode (multiplyNode, scope) {
		return new MultiplyObservable(...this.createInfixOperandObservables(multiplyNode, scope));
	}
	visitDivideNode (divideNode, scope) {
		return new DivideObservable(...this.createInfixOperandObservables(divideNode, scope));
	}

	visitGreaterNode (greaterNode, scope) {
		return new GreaterObservable(...this.createInfixOperandObservables(greaterNode, scope));
	}
	visitGreaterEqualNode (greaterEqualNode, scope) {
		return new GreaterEqualObservable(...this.createInfixOperandObservables(greaterEqualNode, scope));
	}
	visitLessNode (lessNode, scope) {
		return new LessObservable(...this.createInfixOperandObservables(lessNode, scope));
	}
	visitLessEqualNode (lessEqualNode, scope) {
		return new LessEqualObservable(...this.createInfixOperandObservables(lessEqualNode, scope));
	}
	visitEqualNode (equalNode, scope) {
		return new EqualObservable(...this.createInfixOperandObservables(equalNode, scope));
	}
	visitNotEqualNode (notEqualNode, scope) {
		return new NotEqualObservable(...this.createInfixOperandObservables(notEqualNode, scope));
	}
	visitAndNode (andNode, scope) {
		return new AndObservable(...this.createInfixOperandObservables(andNode, scope));
	}
	visitOrNode (orNode, scope) {
		return new OrObservable(...this.createInfixOperandObservables(orNode, scope));
	}

	visitBooleanLiteralNode(booleanLiteralNode, scope) {
		return new BooleanLiteralObservable(booleanLiteralNode.value);
	}
	visitStringLiteralNode(stringLiteralNode, scope) {
		return new StringLiteralObservable(stringLiteralNode.value);
	}
	visitIntegerLiteralNode(integerLiteralNode, scope) {
		return new IntegerLiteralObservable(integerLiteralNode.value);
	}
	visitFloatLiteralNode(floatLiteralNode, scope) {
		return new FloatLiteralObservable(floatLiteralNode.value);
	}
	visitMoneyLiteralNode(moneyLiteralNode, scope) {
		return new MoneyLiteralObservable(moneyLiteralNode.value);
	}

	visitIdentifierNode (identifierNode, scope) {
		return scope.get(identifierNode.name);      // should resolve to an observable if semantic analysis was correct
	}
}

export class ExprObservableFactory {
	createExprObservable(expr, scope) {
		return expr.accept(new ExprObservableFactoryVisitor(), scope);
	}
}