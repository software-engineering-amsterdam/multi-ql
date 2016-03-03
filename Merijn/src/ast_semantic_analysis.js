import { NodeVisitor } from 'src/ast';
import { Log, LineError } from 'src/log';
import * as types from 'src/types';
import * as type_interence from 'src/type_inference';

class AnalysisLogHelper {
	constructor(log) {
		this.log = log;
	}
	nodesToLines(nodes) {
		return nodes.map((node) => node.line);
	}
	logError(nodes, message) {
		this.log.logError(this.nodesToLines(nodes), message);
	}
	logWarning(nodes, message) {
		this.log.logWarning(this.nodesToLines(nodes), message);
	}
}

export class QuestionStore {
	constructor() {
		this._questions = [];
		this._byName = {};
		this._byDescription = {};
	}
	getQuestions() {
		return this._questions;
	}
	hasName(name) {
		return name in this._byName;
	}
	getNames() {
		return Object.keys(this._byName);
	}
	getQuestionsByName(name) {
		return this._byName[name];
	}
	hasDescription(description) {
		return description in this._byDescription;
	}
	getDescriptions() {
		return Object.keys(this._byDescription);
	}
	getQuestionsByDescription(description) {
		return this._byDescription[description];
	}
	addQuestion(questionNode) {
		let name = questionNode.name,
			description = questionNode.description;

		this._questions.push(questionNode);
		if (!(name in this._byName)) {
			this._byName[name] = [];
		}
		this._byName[name].push(questionNode);
		if (!(description in this._byDescription)) {
			this._byDescription[description] = [];
		}
		this._byDescription[description].push(questionNode);
	}
}

class FormAndBlockRecursingVisitor extends NodeVisitor {
	visitFormNode(formNode, ...args) {
		formNode.block.accept(this, ...args);
	}
	visitBlockNode(blockNode, ...args) {
		for (let statement of blockNode.statements){
			statement.accept(this, ...args);
		}
	}
}

class QuestionCollector extends FormAndBlockRecursingVisitor {
	collect(node, questionStore) {
		node.accept(this, questionStore);
	}
	visitQuestionNode(questionNode, questionStore) {
		questionStore.addQuestion(questionNode);
	}
}

/**
 * Set container that uses equals() functions on values to check for value equality
 */
class EqualsSet {
	constructor() {
		this._store = [];
	}
	add(type) {
		let contains = this._store.reduce((seed, element) => seed || element.equals(type), false);
		if (!contains) {
			this._store.push(type);
		}
	}
	size() {
		return this._store.length;
	}
	getArray() {
		return this._store.length;
	}
}

class QuestionDuplicationChecker {
	analyse(questionStore, analysisLog) {
		for (let name of questionStore.getNames()) {
			let questionNodes = questionStore.getQuestionsByName(name),
				types = new EqualsSet();

			for (let questionNode of questionNodes) {
				types.add(questionNode.type);
			}

			if (types.size() > 1) {
				let strTypes = types.getArray().map((type) => "" + type);
				strTypes.sort();
				analysisLog.logError(questionNodes, "Question `" + name + "` found with conflicting types [" + strTypes.join(', ') + "]");
			}
		}
		for (let description of questionStore.getDescriptions()) {
			let questionNodes = questionStore.getQuestionsByDescription(description),
				names = questionNodes.reduce((seed, questionNode) => seed.add(questionNode.name), new Set());

			if (names.size > 1) { // we accept duplicate descriptions if they also have the same identifier
				let strNames = Array.from(names.values());
				strNames.sort();
				analysisLog.logWarning(questionNodes, "Duplicate description `" + description + "` for questions [" + strNames.join(', ') + "]");
			}
		}
	}
}

class ExprTypeChecker extends NodeVisitor {
	analyse(node, questionStore, analysisLog) {
		return node.accept(this, questionStore, analysisLog);
	}
	handleUnaryPrefixOperation(unaryPrefixNode, questionStore, analysisLog, typeInferer) {
		let operandType = unaryPrefixNode.operand.accept(this, questionStore, analysisLog),
			resultType = typeInferer.inferResultType(operandType);

		if (!operandType.is(new types.UndefinedType()) && resultType.is(new types.UndefinedType())) {
			analysisLog.logError([unaryPrefixNode], "Cannot apply unary prefix operation `" + unaryPrefixNode + "` to operand of type `" + operandType + "`");
		}

		return resultType;
	}
	visitNegationNode(negationNode, questionStore, analysisLog) {
		return this.handleUnaryPrefixOperation(negationNode, questionStore, analysisLog, new type_interence.NegationTypeInferer());
	}
	visitNotNode(notNode, questionStore, analysisLog) {
		return this.handleUnaryPrefixOperation(notNode, questionStore, analysisLog, new type_interence.NotTypeInferer());
	}

	handleInfixOperation(infixNode, questionStore, analysisLog, typeInferer) {
		let leftOperandType = infixNode.leftOperand.accept(this, questionStore, analysisLog),
			rightOperandType = infixNode.rightOperand.accept(this, questionStore, analysisLog),
			resultType = typeInferer.inferResultType(leftOperandType, rightOperandType);

		if (!leftOperandType.is(new types.UndefinedType()) && !rightOperandType.is(new types.UndefinedType()) && resultType.is(new types.UndefinedType())) {
			analysisLog.logError([infixNode], "Cannot apply infix operation `" + infixNode + "` to operands of type `" + leftOperandType + "` and `" + rightOperandType + "`");
		}

		return resultType;
	}
	visitAddNode(addNode, questionStore, analysisLog) {
		return this.handleInfixOperation(addNode, questionStore, analysisLog, new type_interence.AddTypeInferer());
	}
	visitSubtractNode(subtractNode, questionStore, analysisLog) {
		return this.handleInfixOperation(subtractNode, questionStore, analysisLog, new type_interence.SubtractTypeInferer());
	}
	visitMultiplyNode(multiplyNode, questionStore, analysisLog) {
		return this.handleInfixOperation(multiplyNode, questionStore, analysisLog, new type_interence.MultiplyTypeInferer());
	}
	visitDivideNode(divideNode, questionStore, analysisLog) {
		return this.handleInfixOperation(divideNode, questionStore, analysisLog, new type_interence.DivideTypeInferer());
	}
	visitGreaterNode(greaterNode, questionStore, analysisLog) {
		return this.handleInfixOperation(greaterNode, questionStore, analysisLog, new type_interence.GreaterTypeInferer());
	}
	visitGreaterEqualNode(greaterEqualNode, questionStore, analysisLog) {
		return this.handleInfixOperation(greaterEqualNode, questionStore, analysisLog, new type_interence.GreaterEqualTypeInferer());
	}
	visitLessNode(lessNode, questionStore, analysisLog) {
		return this.handleInfixOperation(lessNode, questionStore, analysisLog, new type_interence.LessTypeInferer());
	}
	visitLessEqualNode(lessEqualNode, questionStore, analysisLog) {
		return this.handleInfixOperation(lessEqualNode, questionStore, analysisLog, new type_interence.LessEqualTypeInferer());
	}
	visitEqualNode(equalNode, questionStore, analysisLog) {
		return this.handleInfixOperation(equalNode, questionStore, analysisLog, new type_interence.EqualTypeInferer());
	}
	visitNotEqualNode(notEqualNode, questionStore, analysisLog) {
		return this.handleInfixOperation(notEqualNode, questionStore, analysisLog, new type_interence.NotEqualTypeInferer());
	}
	visitAndNode(andNode, questionStore, analysisLog) {
		return this.handleInfixOperation(andNode, questionStore, analysisLog, new type_interence.AndTypeInferer());
	}
	visitOrNode(orNode, questionStore, analysisLog) {
		return this.handleInfixOperation(orNode, questionStore, analysisLog, new type_interence.OrTypeInferer());
	}
	visitLiteralNode(literalNode, questionStore, analysisLog) {
		return literalNode.type;
	}

	visitIdentifierNode(identifierNode, questionStore, analysisLog) {
		let name = identifierNode.name;

		if (questionStore.hasName(name) === false) {
			analysisLog.logError([identifierNode], "Undefined identifier `" + name + "`");

			return new types.UndefinedType();
		}

		return questionStore.getQuestionsByName(name)[0].type;   // assumes that any differences in types have already been reported
	}
}

class TypeChecker extends FormAndBlockRecursingVisitor {
	constructor(exprTypeChecker) {
		super();
		this.exprTypeChecker = exprTypeChecker;
	}
	analyse(node, questionStore, analysisLog) {
		node.accept(this, questionStore, analysisLog);
	}
	visitIfNode(ifNode, questionStore, analysisLog) {
		let conditionType = this.exprTypeChecker.analyse(ifNode.condition, questionStore, analysisLog);

		if (!conditionType.is(new types.UndefinedType()) && !conditionType.is(new types.BooleanType())) {
			analysisLog.logError([ifNode], "Expected condition to be boolean, but was `" + conditionType + "`");
		}
		ifNode.thenBlock.accept(this, questionStore, analysisLog);
	}
	visitIfElseNode(ifNode, questionStore, analysisLog) {
		this.visitIfNode(ifNode, questionStore, analysisLog);
		ifNode.elseBlock.accept(this, questionStore, analysisLog);
	}
	visitExprQuestionNode(exprQuestionNode, questionStore, analysisLog) {
		let exprType = this.exprTypeChecker.analyse(exprQuestionNode.expr, questionStore, analysisLog);

		if (!exprType.is(new types.UndefinedType()) && !exprType.is(exprQuestionNode.type)) {
			analysisLog.logError([exprQuestionNode], "Expected expression to be of type `" + exprQuestionNode.type + "`, but was `" + exprType + "`");
		}
	}
}

class DependencyStore {
	constructor() {
		this._byName = {};
	}
	getNames() {
		return Object.keys(this._byName);
	}
	hasName(name) {
		return name in this._byName;
	}
	getDependencySet(name) {
		return this._byName[name];
	}
	add(name, dependencyNames) {
		if (!(name in this._byName)) {
			this._byName[name] = new Set();
		}
		for (let dependencyName of dependencyNames) {
			this._byName[name].add(dependencyName);
		}
	}
}

class ExprDependencyCollector extends NodeVisitor {
	collect(node) {
		return node.accept(this);
	}
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

class DependencyCollector extends NodeVisitor {
	constructor(exprDependecyCollector) {
		super();
		this.exprDependecyCollector = exprDependecyCollector;
	}
	collect(questionStore, dependencyStore) {
		for (let questionNode of questionStore.getQuestions()) {
			questionNode.accept(this, dependencyStore);
		}
	}
	visitExprQuestionNode(exprQuestionNode, dependencyStore) {
		dependencyStore.add(exprQuestionNode.name, this.exprDependecyCollector.collect(exprQuestionNode.expr));
	}
}

class CyclicDependencyChecker {
	constructor(dependencyCollector) {
		this.dependencyCollector = dependencyCollector;
	}
	findCyclePaths(dependencyStore, analysisLog, path, name) {
		let newPath = path.concat([name]),
			cyclePaths = [];

		if (path.includes(name)) {
			if (path[0] === name) { // if the recurring element is not the first element, then the cycle will already be reported elsewhere
				cyclePaths.push(newPath);
			}
			return cyclePaths;
		}
		if (dependencyStore.hasName(name)) {
			for (let dependencyName of dependencyStore.getDependencySet(name)) {
				cyclePaths = cyclePaths.concat(this.findCyclePaths(dependencyStore, analysisLog, newPath, dependencyName));
			}
		}
		return cyclePaths;
	}
	analyse(questionStore, analysisLog) {
		let dependencyStore = new DependencyStore();

		this.dependencyCollector.collect(questionStore, dependencyStore);
		for (let name of dependencyStore.getNames()) {
			let cyclePaths = this.findCyclePaths(dependencyStore, analysisLog, [], name);

			for (let cyclePath of cyclePaths) {
				analysisLog.logError(cyclePath.reduce((seed, name) => seed.concat(questionStore.getQuestionsByName(name)), []), "Found cyclic dependency for name `" + name + "`, through path [" + cyclePath.join(',') + "]");
			}
		}
	}
}

export class SemanticAnalyser {
	constructor() {
		this.questionCollector = new QuestionCollector();
		this.questionDuplicationChecker = new QuestionDuplicationChecker();
		this.typeChecker = new TypeChecker(new ExprTypeChecker());
		this.cyclicDependencyChecker = new CyclicDependencyChecker(new DependencyCollector(new ExprDependencyCollector()));
	}
	analyse(node, log) {
		let analysisLog = new AnalysisLogHelper(log),
			questionStore = new QuestionStore();

		this.questionCollector.collect(node, questionStore);
		this.questionDuplicationChecker.analyse(questionStore, analysisLog);
		this.typeChecker.analyse(node, questionStore, analysisLog);
		this.cyclicDependencyChecker.analyse(questionStore, analysisLog);
	}
}