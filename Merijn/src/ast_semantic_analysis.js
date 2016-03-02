import { NodeVisitor } from 'src/ast';
import { Log, LineError } from 'src/log';
import * as types from 'src/types';
import * as type_interence from 'src/type_inference';

class AnalysisLog {
	constructor(log) {
		this.log = log;
	}
	prepareWarningMessage(message) {
		return "Semantic analysis warning:" + message;
	}
	prepareErrorMessage(message) {
		return "Semantic analysis error: " + message;
	}
	logError(message) {
		this.log.logError([], this.prepareErrorMessage(message));
	}
	logNodeError(node, message) {
		this.log.logError([node.line], this.prepareErrorMessage(message));
	}
	logNodeWarning(node, message) {
		this.log.logWarning([node.line], this.prepareWarningMessage(message));
	}
}

export class QuestionStore {
	constructor() {
		this._store = {};
		this._descriptionSet = new Set();
	}
	hasName(name) {
		return name in this._store;
	}
	getNames() {
		return Object.keys(this._store);
	}
	hasDescription(description) {
		return this._descriptionSet.has(description);
	}
	getType(name) {
		return this._store[name].type;
	}
	getDependencies(name) {
		return this._store[name].dependencies;
	}
	add(questionNode, dependencies) {
		let name = questionNode.name;

		if (!(name in this._store)) {
			this._store[name] = {
				type: questionNode.type,
				dependencies: []
			};
		}
		this._store[name].dependencies = this._store[name].dependencies.concat(dependencies);
		this._descriptionSet.add(questionNode.description);
	}
}

class FormAndBlockRecursingVisitor extends NodeVisitor {
	visitFormNode(formNode) {
		formNode.block.accept(this);
	}
	visitBlockNode(blockNode) {
		blockNode.statements.forEach((statement) => {
			statement.accept(this);
		});
	}
}

class ExprDependencyExtractingVisitor extends NodeVisitor {
	visitUnaryPrefixNode(unaryPrefixNode) {
		return unaryPrefixNode.operand.accept(this);
	}
	visitInfixNode(infixNode) {
		return infixNode.leftOperand.accept(this).concat(infixNode.rightOperand.accept(this));
	}
	visitLiteralNode(literalNode) {
		return [];
	}
	visitIdentifierNode(identifierNode) {
		return [identifierNode.name];
	}
}

class QuestionStoringVisitor extends FormAndBlockRecursingVisitor {
	constructor(analysisLog, questionStore, exprDependencyExtractingVisitor) {
		super();
		this.analysisLog = analysisLog;
		this.questionStore = questionStore;
		this.exprDependencyExtractingVisitor = exprDependencyExtractingVisitor;
	}
	handleQuestion(questionNode, dependencies) {
		let name = questionNode.name,
			description = questionNode.description,
			type = questionNode.type;

		if (this.questionStore.hasName(name)) {
			let storedType = this.questionStore.getType(name);

			if (!type.equals(storedType)) {
				this.analysisLog.logNodeError(questionNode, "Question `" + name + "` found with type `" + type + "`, but already stored as type `" + storedType + "`");
			}
		}
		if (this.questionStore.hasDescription(description)) {
			this.analysisLog.logNodeWarning(questionNode, "Duplicate question description `" + description + "`");
		}
		this.questionStore.add(questionNode, dependencies);
	}
	visitQuestionNode(questionNode) {
		this.handleQuestion(questionNode, []);
	}
	visitExprQuestionNode(exprQuestionNode) {
		this.handleQuestion(exprQuestionNode, exprQuestionNode.expr.accept(this.exprDependencyExtractingVisitor));
	}
}

class ExprTypeCheckingVisitor extends NodeVisitor {
	constructor(analysisLog, questionStore) {
		super();
		this.analysisLog = analysisLog;
		this.questionStore = questionStore;
	}
	handleUnaryPrefixOperation(unaryPrefixNode, typeInferer) {
		let operandType = unaryPrefixNode.operand.accept(this),
			resultType = operandType.dispatch(typeInferer);

		if (!operandType.is(new types.UndefinedType()) && resultType.is(new types.UndefinedType())) {
			this.analysisLog.logNodeError(unaryPrefixNode, "Cannot apply unary prefix operation `" + unaryPrefixNode + "` to operand of type `" + operandType + "`");
		}

		return resultType;
	}
	visitNegationNode(negationNode) {
		return this.handleUnaryPrefixOperation(negationNode, new type_interence.NegationTypeInferer());
	}
	visitNotNode(notNode) {
		return this.handleUnaryPrefixOperation(notNode, new type_interence.NotTypeInferer());
	}

	handleInfixOperation(infixNode, typeInferer) {
		let leftOperandType = infixNode.leftOperand.accept(this),
			rightOperandType = infixNode.rightOperand.accept(this),
			resultType = leftOperandType.dispatch(typeInferer, rightOperandType);

		if (!leftOperandType.is(new types.UndefinedType()) && !rightOperandType.is(new types.UndefinedType()) && resultType.is(new types.UndefinedType())) {
			this.analysisLog.logNodeError(infixNode, "Cannot apply infix operation `" + infixNode + "` to operands of type `" + leftOperandType + "` and `" + rightOperandType + "`");
		}

		return resultType;
	}
	visitAddNode(addNode) {
		return this.handleInfixOperation(addNode, new type_interence.AddTypeInferer());
	}
	visitSubtractNode(subtractNode) {
		return this.handleInfixOperation(subtractNode, new type_interence.SubtractTypeInferer());
	}
	visitMultiplyNode(multiplyNode) {
		return this.handleInfixOperation(multiplyNode, new type_interence.MultiplyTypeInferer());
	}
	visitDivideNode(divideNode) {
		return this.handleInfixOperation(divideNode, new type_interence.DivideTypeInferer());
	}
	visitGreaterNode(greaterNode) {
		return this.handleInfixOperation(greaterNode, new type_interence.GreaterTypeInferer());
	}
	visitGreaterEqualNode(greaterEqualNode) {
		return this.handleInfixOperation(greaterEqualNode, new type_interence.GreaterEqualTypeInferer());
	}
	visitLessNode(lessNode) {
		return this.handleInfixOperation(lessNode, new type_interence.LessTypeInferer());
	}
	visitLessEqualNode(lessEqualNode) {
		return this.handleInfixOperation(lessEqualNode, new type_interence.LessEqualTypeInferer());
	}
	visitEqualNode(equalNode) {
		return this.handleInfixOperation(equalNode, new type_interence.EqualTypeInferer());
	}
	visitNotEqualNode(notEqualNode) {
		return this.handleInfixOperation(notEqualNode, new type_interence.NotEqualTypeInferer());
	}
	visitAndNode(andNode) {
		return this.handleInfixOperation(andNode, new type_interence.AndTypeInferer());
	}
	visitOrNode(orNode) {
		return this.handleInfixOperation(orNode, new type_interence.OrTypeInferer());
	}
	visitLiteralNode(literalNode) {
		return literalNode.type;
	}

	visitIdentifierNode(identifierNode) {
		let name = identifierNode.name;

		if (this.questionStore.hasName(name) === false) {
			this.analysisLog.logNodeError(identifierNode, "Undefined identifier `" + name + "`");

			return new types.UndefinedType();
		}

		return this.questionStore.getType(name);
	}
}

class TypeCheckingVisitor extends FormAndBlockRecursingVisitor {
	constructor(analysisLog, exprTypeCheckingVisitor) {
		super();
		this.analysisLog = analysisLog;
		this.exprTypeCheckingVisitor = exprTypeCheckingVisitor;
	}
	visitIfNode(ifNode) {
		let conditionType = ifNode.condition.accept(this.exprTypeCheckingVisitor);

		if (!conditionType.is(new types.BooleanType())) {
			this.analysisLog.logNodeError(ifNode, "Expected condition to be boolean, but was `" + conditionType + "`");
		}
		ifNode.thenBlock.accept(this);
	}
	visitIfElseNode(ifNode) {
		this.visitIfNode(ifNode);
		ifNode.elseBlock.accept(this);
	}
	visitExprQuestionNode(exprQuestionNode) {
		let exprType = exprQuestionNode.expr.accept(this.exprTypeCheckingVisitor);

		if (!exprType.is(exprQuestionNode.type)) {
			this.analysisLog.logNodeError(exprQuestionNode, "Expected expression to be of type `" + exprQuestionNode.type + "`, but was `" + exprType + "`");
		}
	}
}

class CyclicDependencyChecker {
	constructor(analysisLog, questionStore) {
		this.analysisLog = analysisLog;
		this.questionStore = questionStore;
	}
	analysePath(path, name) {
		let newPath = path.concat(name);

		if (path.includes(name)) {
			if (path[0] === name) { // if the recurring element is not the first element, then the cycle will already be reported elsewhere
				this.analysisLog.logError("Found cyclic dependency for name `" + name + "`, through path [" + newPath.join(',') + "]");
			}
			return;
		}
		if (this.questionStore.hasName(name)) {
			this.questionStore.getDependencies(name).forEach((dependencyName) => {
				this.analysePath(newPath, dependencyName);
			});
		}
	}
	analyse() {
		this.questionStore.getNames().forEach((name) => {
			this.analysePath([], name);
		});
	}
}

export class SemanticAnalyser {
	analyse(node) {
		let log = new Log(),
			analysisLog = new AnalysisLog(log),
			questionStore = new QuestionStore(),
			questionStoringVisitor = new QuestionStoringVisitor(analysisLog, questionStore, new ExprDependencyExtractingVisitor()),
			typeCheckingVisitor = new TypeCheckingVisitor(analysisLog, new ExprTypeCheckingVisitor(analysisLog, questionStore)),
			cyclicDependencyChecker = new CyclicDependencyChecker(analysisLog, questionStore);

		node.accept(questionStoringVisitor);
		node.accept(typeCheckingVisitor);
		cyclicDependencyChecker.analyse();

		return log;
	}
}