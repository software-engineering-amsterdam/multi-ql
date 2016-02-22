function initiate(inputString) {

	var antlr4 = require('antlr4/index');
	var MyGrammerLexer = require('MyGrammerLexer');
	var MyGrammerParser = require('MyGrammerParser');
	var MyGrammerListener = require('MyGrammerListener');

	var characters = new antlr4.InputStream(inputString);
	var lexer = new MyGrammerLexer.MyGrammerLexer(characters);
	var tokens = new antlr4.CommonTokenStream(lexer);
	var parserANTLR = new MyGrammerParser.MyGrammerParser(tokens);

	var editor = ace.edit("input");
	editor.getSession().clearAnnotations();
	resetErrorPanels();

	var ErrorListener = function () {
		antlr4.error.ErrorListener.call(this);
		return this;
	};
	ErrorListener.prototype = Object.create(antlr4.error.ErrorListener.prototype);
	ErrorListener.prototype.constructor = ErrorListener;
	ErrorListener.prototype.syntaxError = function (rec, sym, line, col, msg, e) {
		throwError(line, "Parse error: " + msg);
	};
	lexer.removeErrorListeners();
	parserANTLR.removeErrorListeners();
	lexer.addErrorListener(new ErrorListener());
	parserANTLR.addErrorListener(new ErrorListener());

	parserANTLR.buildParseTrees = true;
	var tree = parserANTLR.form();
	createAst(tree);
}

function createAst(parseTree) {
	
	var MyGrammerVisitor = require('MyGrammerVisitor');

	var Visitor = function () {
		MyGrammerVisitor.MyGrammerVisitor.call(this);
		return this;
	};

	Visitor.prototype = Object.create(MyGrammerVisitor.MyGrammerVisitor.prototype);

	Visitor.prototype.visitForm = function (ctx) {
		ast = ctx.FormNode;
		if (preformASTCheck()) {
			renderQuestions();
			setHandlers();
			refreshGUI();
			console.log("Generated AST: ");
			console.log(ast);
		}
	};

	var visitor = new Visitor();
	parseTree.accept(visitor);
}


function preformASTCheck() {
	var texts = new Set();
	var labels = new Set();

	var noErrors = true;

	transverseAST(
		function (questionNode) {
			if (labels.has(questionNode.label)) {
				throwError(questionNode.line, "Label '" + questionNode.label + "' is already defined");
				noErrors = false;
			}
			if (texts.has(questionNode.text)) {
				throwWarning(questionNode.line, "Text '" + questionNode.text + "' is already defined");
			}
			if (questionNode instanceof ComputedQuestionNode && evaluateStmt(questionNode.computedExpr) == undefined) {
				throwError(questionNode.computedExpr.line, "Computed expression '" + questionNode.computedExpr.toString() + "' is undefined");
				noErrors = false;
			}
			labels.add(questionNode.label);
			texts.add(questionNode.text);
		},
		function (conditionNode) {
			var evalResult = evaluateStmt(conditionNode.condition);
			if (typeof evalResult !== "boolean") {
				noErrors = false;
				throwError(conditionNode.line, "Condition '" + conditionNode.condition.toString() + "' is not boolean");
			}
		}
	);

	return noErrors;
}

function getQuestion(label) {
	return transverseAST(function (questionNode) {
		if (questionNode.label == label) {
			return questionNode;
		}
	});
}

function resetQuestionVisibility() {
	transverseAST(function (questionNode) {
		questionNode.visible = false;
	});
}

function evaluateStmt(statement) {
	var evaluation = statement.compute();
	if (evaluation == undefined) {
		throwError(statement.line, "Statement is undefined");
	}
	return evaluation;
}

function saveAnswers() {
	var answers = [];

	transverseAST(function (questionNode) {
		if (questionNode.visible) {
			var answer = {};
			answer.questionLabel = questionNode.label;
			answer.questionText = questionNode.text;
			answer.questionType = questionNode.type;
			answer.value = questionNode.value;
			answers.push(answer);
		}
	});

	var blob = new Blob([JSON.stringify(answers, null, 2)], {type: "text/plain;charset=utf-8"});
	saveAs(blob, "answers.json");
}


function transverseAST(questionFunction, conditionFunction) {
	var queue = [];
	for (var i = 0; i < ast.block.length; i++) {
		queue.push(ast.block[i]);
	}
	while (queue.length > 0) {
		var currentNode = queue.shift();
		if (currentNode instanceof QuestionNode) {
			if (questionFunction != undefined) {
				var result = questionFunction(currentNode);
				if (result != undefined) return result;
			}
		}
		else {
			if (conditionFunction != undefined) {
				var result = conditionFunction(currentNode);
				if (result != undefined) return result;
			}
			for (var i = 0; i < currentNode.ifBlock.length; i++) {
				queue.push(currentNode.ifBlock[i]);
			}
			if (currentNode.elseBlock != undefined) {
				for (var i = 0; i < currentNode.elseBlock.length; i++) {
					queue.push(currentNode.elseBlock[i]);
				}
			}
		}
	}
}

function throwError(line, errorMsg) {
	renderError(line, errorMsg);
}

function throwWarning(line, warningMsg) {
	renderWarning(line, warningMsg);
}