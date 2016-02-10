import {NodeListener, NodeWalker} from 'src/ast_walking';
import * as ast from 'src/ast';

let TYPE_UNKNOWN = 'unknown';       // used when types cannot be resolved - ie. for undefined identifiers

class ScopeStack {
	constructor() {
		this.stack = [];
	}
	has (name) {
		for (let scope of this.stack) {
			if (name in scope) {
				return true;
			}
		}
		return false;
	}
	typeOf (name) {
		for (let scope of this.stack) {
			if (name in scope) {
				return scope[name];
			}
		}

		throw new Error("No name `" + name + "` in scope");
	}
	set (name, type) {
		this.stack[0][name] = type;
	}
	enterScope () {
		this.stack.unshift({});
	}
	leaveScope () {
		if (this.stack.length <= 0) {
			throw new Error("No scopes left to leave");
		}
		this.stack.shift();
	}
}

class NodeErrorLog {
	constructor() {
		this.errors = [];
	}
	logError(node, message) {
		this.errors.push("Line " + node.line + ": " + message);
	}
}

class SementicAnalysisVisitor extends ast.NodeVisitor {
	constructor(nodeErrorlog) {
		this.scope_stack = new ScopeStack();
		this.nodeErrorlog = nodeErrorlog;
	}
	visitBlockNode (blockNode) {
		this.scope_stack.enterScope();
		for (let statement of blockNode.statements) {
			statement.accept(this);
		}
		this.scope_stack.leaveScope();
	}
	visitIfNode(ifNode) {
		let conditionType = ifNode.condition.accept(this);

		if ([TYPE_UNKNOWN, ast.TYPE_BOOLEAN].indexOf(conditionType) === -1) {
			this.nodeErrorlog.logError(ifNode, "Expected condition to be of type `" + ast.TYPE_BOOLEAN + "`, but was `" + conditionType + "`");
		}

		ifNode.thenBlock.accept(this);
		if (ifNode.elseBlock !== null) {
			ifNode.elseBlock.accept(this);
		}
	}
	visitQuestionNode(questionNode) {
		let name = questionNode.name,
			type = questionNode.type;

		if (this.scope_stack.has(name) === true) {
			this.nodeErrorlog.logError(questionNode, "Duplicate question name `" + name + "`");
		}

		this.scope_stack.set(name, type);
	}

	// the following methods are for expressions and return their resolved types
	visitUnaryPrefixNode(unaryPrefixNode) {
		let operandType = unaryPrefixNode.operand.accept(this),
			acceptableTypes = [TYPE_UNKNOWN, ast.TYPE_INTEGER, ast.TYPE_FLOAT, ast.TYPE_MONEY];

		if (acceptableTypes.indexOf(operandType) === -1) {
			this.nodeErrorlog.logError(unaryPrefixNode, "Expected operand type to be one of [" + acceptableTypes.join(',') + "], but was `" + operandType + "`");

			return TYPE_UNKNOWN;
		}

		return operandType;
	}
	visitInfixNode(infixNode) {
		let leftOperandType = infixNode.leftOperand.accept(this),
			rightOperandType = infixNode.rightOperand.accept(this),
			acceptableTypes;

		// already logged somewhere else
		if (leftOperandType === TYPE_UNKNOWN || rightOperandType === TYPE_UNKNOWN) {
			return TYPE_UNKNOWN;
		}

		switch (infixNode.operation) {
			case '*':
			case '/':
				acceptableTypes = [ast.TYPE_INTEGER, ast.TYPE_FLOAT];
				break;
			case '+':
			case '-':
			case '>':
			case '>=':
			case '<':
			case '<=':
				acceptableTypes = [ast.TYPE_INTEGER, ast.TYPE_FLOAT, ast.TYPE_MONEY];
				break;
			case '==':
			case '!=':
				acceptableTypes = [ast.TYPE_BOOLEAN, ast.TYPE_STRING, ast.TYPE_INTEGER, ast.TYPE_FLOAT, ast.TYPE_MONEY];
				break;
			default:
				throw new Error("Unexpected infix operation `" + infixNode.operation + "`");
		}

		if (acceptableTypes.indexOf(leftOperandType) === -1) {
			this.nodeErrorlog.logError(infixNode, "Incompatible type `" + infixNode.operation + "` for infix operation `" + infixNode.operation + "`");

			return TYPE_UNKNOWN;
		}

		if (leftOperandType !== rightOperandType) {
			this.nodeErrorlog.logError(infixNode, "Incompatible types `" + leftOperandType + "` and `" + rightOperandType + "` for infix operation `" + infixNode.operation + "`");

			return TYPE_UNKNOWN;
		}

		return leftOperandType;
	}
	visitLiteralNode(literalNode) {
		return literalNode.type;
	}
	visitIdentifierNode(identifierNode) {
		let name = identifierNode.name;

		if (this.scope_stack.has(name) === false) {
			this.nodeErrorlog.logError(identifierNode, "Undefined identifier `" + name + "`");

			return TYPE_UNKNOWN;
		}

		return this.scope_stack.typeOf(name);
	}
}

export class SemanticAnalyser {
	analyse(node) {
		let nodeErrorlog = new NodeErrorLog(),
			visitor = new SementicAnalysisVisitor(nodeErrorlog);

		node.accept(visitor);
		return nodeErrorlog.errors;
	}
}