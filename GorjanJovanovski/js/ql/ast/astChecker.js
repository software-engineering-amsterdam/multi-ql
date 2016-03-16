function performAstChecks(ast) {
	var textSet = new Set();
	var identifierMap = [];

	var noErrors = true;

	ast.transverseAST(
		(questionNode) => {

			checkQuestionTextDuplicate(questionNode, textSet);

			if (isQuestionIdentifierDuplicate(questionNode, identifierMap)) {
				noErrors = false;
			}
			else {
				identifierMap[questionNode.label] = questionNode.getTypeString();
			}

			if (!isExpressionDefined(questionNode)) {
				noErrors = false;
			}
			else if (!isExpressionExpectedType(questionNode)) {
				noErrors = false;
			}

			textSet.add(questionNode.text);
		},
		(conditionNode) => {
			if (!isExpressionDefined(conditionNode)) {
				noErrors = false;
			}
			else if (!isExpressionExpectedType(conditionNode)) {
				noErrors = false;
			}
		}
	);

	if (noErrors) {
		noErrors = checkDependencies(ast);
	}

	return noErrors;
}

function isQuestionIdentifierDuplicate(questionNode, identifierMap) {
	if (identifierMap[questionNode.label] !== undefined && identifierMap[questionNode.label] !== questionNode.getTypeString()) {
		throwError(questionNode.line, "Question error: Question identifier '" + questionNode.label + "' of type '" + questionNode.getTypeString() + "' is already defined as of another type");
		return true;
	}
	return false;
}

function checkQuestionTextDuplicate(questionNode, textSet) {
	if (textSet.has(questionNode.text)) {
		throwWarning(questionNode.line, "Question warning: Text '" + questionNode.text + "' for question '" + questionNode.label + "' is already defined");
	}
}

function isExpressionDefined(astNode) {
	if (!astNode.isExpressionDefined()) {
		throwError(astNode.line, "Type error: Computed expression '" + astNode.exprString() + "' is undefined");
		return false;
	}
	return true;
}


function isExpressionExpectedType(astNode) {
	if (!astNode.checkExpressionType()) {
		throwError(astNode.line, "Type error: Computed expression '" + astNode.exprString() + "' must evaluate to " + type);
		return false;
	}
	return true;
}