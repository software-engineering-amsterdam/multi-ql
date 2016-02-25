import { NodeVisitor } from 'src/ast';
import { LineError } from 'src/error';
import { BooleanType, StringType, IntegerType, FloatType, MoneyType, UndefinedType } from 'src/types';
import { NegationTypeInferer, NotTypeInferer, AddTypeInferer, SubtractTypeInferer, MultiplyTypeInferer, DivideTypeInferer, GreaterTypeInferer, GreaterEqualTypeInferer, LessTypeInferer, LessEqualTypeInferer, EqualTypeInferer, NotEqualTypeInferer, AndTypeInferer, OrTypeInferer } from 'src/type_inference';

class NodeErrorLog {
	constructor() {
		this.errors = [];
		this.warnings = [];
	}
	logError(node, message) {
		this.errors.push(new LineError(node.line, "Semantic analysis error: " + message));
	}
	logWarning(node, message) {
		this.warnings.push(new LineError(node.line, "Semantic analysis warning:" + message));
	}
}

export class Scope {
	constructor() {
		this.byName = {};
		this.byDescription = {};
	}
	hasName(name) {
		return name in this.byName;
	}
	hasDescription(description) {
		return description in this.byDescription;
	}
	getByName(name) {
		if (!(name in this.byName)) {
			throw new Error("No name `" + name + "` in scope");
		}
		return this.byName[name];
	}
	getByDescription(description) {
		if (!(description in this.byDescription)) {
			throw new Error("No name `" + name + "` in scope");
		}
		return this.byDescription[description];
	}
	store(questionNode) {
		let name = questionNode.name,
			description = questionNode.description;

		if (!(name in this.byName)) {
			this.byName[name] = questionNode;
		}
		if (!(description in this.byDescription)) {
			this.byDescription[description] = questionNode;
		}
	}
}

class FormAndBlockRecursingVisitor extends NodeVisitor {
	visitForm(formNode) {
		formNode.block.accept(this);
	}
	visitBlock(blockNode) {
		blockNode.statements.forEach((statement) => {
			statement.accept(this);
		});
	}
}

class QuestionStoringVisitor extends FormAndBlockRecursingVisitor {
	constructor(log, scope) {
		this.log = log;
		this.scope = scope;
	}
	visitQuestionNode(questionNode) {
		let name = questionNode.name,
			description = questionNode.description,
			type = questionNode.type;

		if (this.scope.hasName(name)) {
			let storedType = this.scope.getByName(name).type;

			if (type !== storedType) {
				this.log.logError(questionNode, "Duplicate question name `" + name + "`");
			}
		}
		if (this.scope.hasDescription(description)) {
			this.log.logWarning(questionNode, "Duplicate question description `" + description + "`");
		}
		this.scope.store(questionNode);
	}
}

class ExprTypeCheckingVisitor extends NodeVisitor {
	constructor(log, scope) {
		this.log = log;
		this.scope = scope;
	}
	handleUnaryPrefixOperation(unaryPrefixNode, typeInferer) {
		let operandType = unaryPrefixNode.operand.accept(this),
			resultType = operandType.dispatch(typeInferer);

		if (!operandType.is(new UndefinedType()) && resultType.is(new UndefinedType())) {
			this.log.logError(unaryPrefixNode, "Cannot apply unary prefix operation `" + unaryPrefixNode + "` to operand of type `" + operandType + "`");
		}

		return resultType;
	}
	visitNegationNode(negationNode) {
		return this.handleUnaryPrefixOperation(negationNode, new NegationTypeInferer());
	}
	visitNotNode(notNode) {
		return this.handleUnaryPrefixOperation(notNode, new NotTypeInferer());
	}

	handleInfixOperation(infixNode, typeInferer) {
		let leftOperandType = infixNode.leftOperand.accept(this),
			rightOperandType = infixNode.rightOperand.accept(this),
			resultType = leftOperandType.dispatch(typeInferer, rightOperandType);

		if (!leftOperandType.is(new UndefinedType()) && !rightOperandType.is(new UndefinedType()) && resultType.is(new UndefinedType())) {
			this.log.logError(infixNode, "Cannot apply infix operation `" + infixNode + "` to operands of type `" + leftOperandType + "` and `" + rightOperandType + "`");
		}

		return leftOperandType;
	}
	visitAddNode(addNode) {
		return this.handleInfixOperation(addNode, new AddTypeInferer());
	}
	visitSubtractNode(subtractNode) {
		return this.handleInfixOperation(subtractNode, new SubtractTypeInferer());
	}
	visitMultiplyNode(multiplyNode) {
		return this.handleInfixOperation(multiplyNode, new MultiplyTypeInferer());
	}
	visitDivideNode(divideNode) {
		return this.handleInfixOperation(divideNode, new DivideTypeInferer());
	}
	visitGreaterNode(greaterNode) {
		return this.handleInfixOperation(greaterNode, new GreaterTypeInferer());
	}
	visitGreaterEqualNode(greaterEqualNode) {
		return this.handleInfixOperation(greaterEqualNode, new GreaterEqualTypeInferer());
	}
	visitLessNode(lessNode) {
		return this.handleInfixOperation(lessNode, new LessTypeInferer());
	}
	visitLessEqualNode(lessEqualNode) {
		return this.handleInfixOperation(lessEqualNode, new LessEqualTypeInferer());
	}
	visitEqualNode(equalNode) {
		return this.handleInfixOperation(equalNode, new LessEqualTypeInferer());
	}
	visitNotEqualNode(notEqualNode) {
		return this.handleInfixOperation(notEqualNode, new NotEqualTypeInferer());
	}
	visitAndNode(andNode) {
		return this.handleInfixOperation(andNode, new AndTypeInferer());
	}
	visitOrNode(orNode) {
		return this.handleInfixOperation(orNode, new OrTypeInferer());
	}
	visitLiteral(literalNode) {
		return literalNode.type;
	}

	visitIdentifierNode(identifierNode) {
		let name = identifierNode.name;

		if (this.scope.has(name) === false) {
			this.log.logError(identifierNode, "Undefined identifier `" + name + "`");

			return new UndefinedType();
		}

		return this.scope.get(name);
	}
}

class TypeCheckingVisitor extends FormAndBlockRecursingVisitor {
	constructor(log, exprTypeCheckingVisitor) {
		this.log = log;
		this.exprTypeCheckingVisitor = exprTypeCheckingVisitor;
	}
	visitIfNode(ifNode) {
		let conditionType = ifNode.condition.accept(this);

		if (!conditionType.is(new BooleanType())) {
			this.log.logError(ifNode, "Expected condition to be boolean, but was `" + conditionType + "`");
		}

		ifNode.thenBlock.accept(this);
		if (ifNode.elseBlock !== null) {
			ifNode.elseBlock.accept(this);
		}
	}
	visitExprQuestion(exprQuestionNode) {
		let exprType = exprQuestionNode.expr.accept(this.exprTypeCheckingVisitor);

		if (!exprType.is(exprQuestionNode.type)) {
			this.log.logError(exprQuestionNode, "Expected expression to be of type `" + exprQuestionNode.type + "`, but was `" + exprType + "`");
		}
	}
}

export class SemanticAnalyser {
	analyse(node) {
		let log = new NodeErrorLog(),
			scope = new Scope(),
			questionStoringVisitor = new QuestionStoringVisitor(scope),
			typeCheckingVisitor = new TypeCheckingVisitor(log, new ExprTypeCheckingVisitor(log, scope));

		node.accept(questionStoringVisitor);
		node.accept(typeCheckingVisitor);

		return log.errors;
	}
}