import {NodeListener, NodeWalker} from 'src/ast_walking';
import * as ast from 'src/ast';
import { LineError } from 'src/error';

let TYPE_UNKNOWN = 'unknown';       // used when types cannot be resolved - ie. for undefined identifiers

class Scope {
	constructor(parent_scope) {
		this.parent_scope = parent_scope || null;
		this.scope = {};
	}
	has (name) {
		return name in this.scope || this.parent_scope !== null && this.parent_scope.has(name);
	}
	typeOf (name) {
		if (name in this.scope) {
			return this.scope[name];
		}

		if (this.parent_scope !== null) {
			return this.parent_scope.typeOf(name);
		}

		throw new Error("No name `" + name + "` in scope");
	}
	set (name, type) {
		if (this.has(name)) {
			throw new Error("Name `" + name + "` already defined");
		}
		this.scope[name] = type;
	}
}

class NodeErrorLog {
	constructor() {
		this.errors = [];
	}
	logError(node, message) {
		this.errors.push(new LineError(node.line, "Semantic analysis error: " + message));
	}
}

class SementicAnalysisVisitor extends ast.NodeVisitor {
	constructor(nodeErrorlog) {
		super();
		this.nodeErrorlog = nodeErrorlog;
	}
	visitBlockNode (blockNode, scope) {
		let blockScope = new Scope(scope);
		for (let statement of blockNode.statements) {
			statement.accept(this, blockScope);
		}
	}
	visitIfNode(ifNode, scope) {
		let conditionType = ifNode.condition.accept(this, scope);

		if ([TYPE_UNKNOWN, ast.TYPE_BOOLEAN].indexOf(conditionType) === -1) {
			this.nodeErrorlog.logError(ifNode, "Expected condition to be of type `" + ast.TYPE_BOOLEAN + "`, but was `" + conditionType + "`");
		}

		ifNode.thenBlock.accept(this, scope);
		if (ifNode.elseBlock !== null) {
			ifNode.elseBlock.accept(this, scope);
		}
	}

	handleQuestion(questionNode, type, scope) {
		let name = questionNode.name;

		if (scope.has(name) === true) {
			this.nodeErrorlog.logError(questionNode, "Duplicate question name `" + name + "`");
			return false;
		}

		scope.set(name, type);
	}
	visitInputQuestionNode(inputQuestionNode, scope) {
		this.handleQuestion(inputQuestionNode, inputQuestionNode.type, scope);
	}
	visitExprQuestionNode(exprQuestionNode, scope) {
		this.handleQuestion(exprQuestionNode, exprQuestionNode.expr.accept(this, scope), scope);
	}

	// the following methods are for expressions and return their resolved types
	handleUnaryPrefixOperation(unaryPrefixNode, unaryPrefixOperation, acceptableTypes, scope) {
		let operandType = unaryPrefixNode.operand.accept(this, scope);

		if (acceptableTypes.indexOf(operandType) === -1) {
			if (operandType !== TYPE_UNKNOWN) {
				this.nodeErrorlog.logError(unaryPrefixNode, "Expected operand of unary prefix operatoin `" + unaryPrefixOperation + "` to be one of types [" + acceptableTypes.join(', ') + "], but was `" + operandType + "`");
			}
			return TYPE_UNKNOWN;
		}
		return operandType;
	}
	visitNegationNode(negationNode, scope) {
		return this.handleUnaryPrefixOperation(negationNode, '-', [ast.TYPE_INTEGER, ast.TYPE_FLOAT], scope);
	}
	visitNotNode(notNode, scope) {
		let operandType = this.handleUnaryPrefixOperation(notNode, '!', [ast.TYPE_BOOLEAN], scope);
	}

	handleInfixOperation(infixNode, infixOperation, acceptableTypes, scope) {
		let leftOperandType = infixNode.leftOperand.accept(this, scope),
			rightOperandType = infixNode.rightOperand.accept(this, scope);

		if (acceptableTypes.indexOf(leftOperandType) === -1) {
			if (leftOperandType !== TYPE_UNKNOWN) {
				this.nodeErrorlog.logError(infixNode, "Expected left operand of infix operation `" + infixOperation + "` to be one of types [" + acceptableTypes.join(', ') + "], but was `" + leftOperandType + "`");
			}
			return TYPE_UNKNOWN;
		}
		if (acceptableTypes.indexOf(rightOperandType) === -1) {
			if (rightOperandType !== TYPE_UNKNOWN) {
				this.nodeErrorlog.logError(infixNode, "Expected right operand of infix operation `" + infixOperation + "` to be one of types [" + acceptableTypes.join(', ') + "], but was `" + rightOperandType + "`");
			}
			return TYPE_UNKNOWN;
		}
		if (leftOperandType !== rightOperandType) {
			if (rightOperandType !== TYPE_UNKNOWN) {
				this.nodeErrorlog.logError(infixNode, "Expected left and right operands of infix operation `" + infixOperation +"` to be of equal types, but left was `" + leftOperandType + "` and right was `" + rightOperandType +"`");
			}
			return TYPE_UNKNOWN;
		}

		return leftOperandType;
	}
	handleInfixOperationWithFixedReturnType(infixNode, infixOperation, acceptableTypes, scope, returnType) {
		let operandsType = this.handleInfixOperation(infixNode, infixOperation, acceptableTypes, scope);

		return operandsType !== TYPE_UNKNOWN ? returnType : TYPE_UNKNOWN;
	}
	visitAddNode(addNode, scope) {
		return this.handleInfixOperation(addNode, '+', [ast.TYPE_INTEGER, ast.TYPE_FLOAT], scope);
	}
	visitSubtractNode(addNode, scope) {
		return this.handleInfixOperation(addNode, '-', [ast.TYPE_INTEGER, ast.TYPE_FLOAT], scope);
	}
	visitMultiplyNode(addNode, scope) {
		return this.handleInfixOperation(addNode, '*', [ast.TYPE_INTEGER, ast.TYPE_FLOAT], scope);
	}
	visitDivideNode(addNode, scope) {
		return this.handleInfixOperation(addNode, '/', [ast.TYPE_FLOAT], scope);
	}
	visitGreaterNode(greaterNode, scope) {
		return this.handleInfixOperationWithFixedReturnType(greaterNode, '>', [ast.TYPE_INTEGER, ast.TYPE_FLOAT], scope, ast.TYPE_BOOLEAN);
	}
	visitGreaterEqualNode(greaterEqualNode, scope) {
		return this.handleInfixOperationWithFixedReturnType(greaterEqualNode, '>=', [ast.TYPE_INTEGER, ast.TYPE_FLOAT], scope, ast.TYPE_BOOLEAN);
	}
	visitLessNode(lessNode, scope) {
		return this.handleInfixOperationWithFixedReturnType(lessNode, '<', [ast.TYPE_INTEGER, ast.TYPE_FLOAT], scope, ast.TYPE_BOOLEAN);
	}
	visitLessEqualNode(lessEqualNode, scope) {
		return this.handleInfixOperationWithFixedReturnType(lessEqualNode, '<=', [ast.TYPE_INTEGER, ast.TYPE_FLOAT], scope, ast.TYPE_BOOLEAN);
	}
	visitEqualNode(equalNode, scope) {
		return this.handleInfixOperationWithFixedReturnType(equalNode, '==', [ast.TYPE_INTEGER, ast.TYPE_FLOAT], scope, ast.TYPE_BOOLEAN);
	}
	visitNotEqualNode(notEqualNode, scope) {
		return this.handleInfixOperationWithFixedReturnType(notEqualNode, '!=', [ast.TYPE_INTEGER, ast.TYPE_FLOAT], scope, ast.TYPE_BOOLEAN);
	}
	visitAndNode(andNode, scope) {
		return this.handleInfixOperationWithFixedReturnType(andNode, '&&', [ast.TYPE_BOOLEAN], scope, ast.TYPE_BOOLEAN);
	}
	visitOrNode(orNode, scope) {
		return this.handleInfixOperationWithFixedReturnType(orNode, '||', [ast.TYPE_BOOLEAN], scope, ast.TYPE_BOOLEAN);
	}

	visitBooleanLiteralNode() {
		return ast.TYPE_BOOLEAN;
	}
	visitStringLiteralNode() {
		return ast.TYPE_STRING;
	}
	visitIntegerLiteralNode() {
		return ast.TYPE_INTEGER;
	}
	visitFloatLiteralNode() {
		return ast.TYPE_FLOAT;
	}
	visitMoneyLiteralNode() {
		return ast.TYPE_MONEY;
	}

	visitIdentifierNode(identifierNode, scope) {
		let name = identifierNode.name;

		if (scope.has(name) === false) {
			this.nodeErrorlog.logError(identifierNode, "Undefined identifier `" + name + "`");

			return TYPE_UNKNOWN;
		}

		return scope.typeOf(name);
	}
}

export class SemanticAnalyser {
	analyse(node) {
		let nodeErrorlog = new NodeErrorLog(),
			visitor = new SementicAnalysisVisitor(nodeErrorlog);

		node.accept(visitor, new Scope());
		return nodeErrorlog.errors;
	}
}