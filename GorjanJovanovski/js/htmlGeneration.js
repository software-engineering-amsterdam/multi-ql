function setHandlers() {
	$("input").change(function () {
		var label = $(this).attr("name");
		if ($(this).attr("type") == "checkbox") {
			getQuestion(label).setValue($(this).is(":checked"));
		}
		else {
			getQuestion(label).setValue($(this).val());
		}
		refreshGUI();
	});
}

function refreshGUI() {
	var stack = [];

	$(".questionDiv").hide();
	resetQuestionVisibility();

	for (var i = 0; i < ast.block.length; i++) {
		stack.push(ast.block[i]);
	}

	while (stack.length > 0) {
		var currentNode = stack.shift();
		if (currentNode instanceof QuestionNode) {
			if (currentNode instanceof ComputedQuestionNode) {
				currentNode.setValue(evaluateStmt(currentNode.computedExpr));
			}
			currentNode.visible = true;
			$(".questionDiv[label='" + currentNode.label + "']").show();
			$("input[name='" + currentNode.label + "']").val(currentNode.value);
		}
		else {
			var evalResult = evaluateStmt(currentNode.condition);
			if (evalResult) {
				for (var i = 0; i < currentNode.ifBlock.length; i++) {
					stack.push(currentNode.ifBlock[i]);
				}
			}
			else if (currentNode.elseBlock != undefined) {
				for (var i = 0; i < currentNode.elseBlock.length; i++) {
					stack.push(currentNode.elseBlock[i]);
				}
			}
		}
	}
}

function generateQuestionHTML(question) {
	var html = "<div class='questionDiv' label='" + question.label + "'>";
	html += "<label class='question'>" + question.text + " ";
	html += "<input name='" + question.label + "' type='";

	if (question.type instanceof NumberType || question.type instanceof DecimalType) {
		html += "number";
	}
	else if (question.type instanceof BooleanType) {
		html += "checkbox";
	}
	else {
		html += "text";
	}

	html += "'";


	if (question instanceof ComputedQuestionNode) {
		html += " disabled";
	}

	html += "/></label></div>";

	return html;
}

function renderQuestions() {
	var output = "";
	transverseAST(function (questionNode) {
		output += generateQuestionHTML(questionNode);
	});
	$("#output").html(output);
}

function resetErrorPanels() {
	$("#error").html("");
	$("#warning").html("");

	$("#errorPanel").hide();
	$("#warningPanel").hide();

	$("#formWrapper").show();
}

function renderError(line, message) {
	var editor = ace.edit("input");
	var html = "<li><a href='#' onClick='goToLine(" + line + ");'>[line " + line + "] " + message + "</a></li>";
	
	var errorList = editor.getSession().getAnnotations();
	if (errorList == undefined || typeof errorList != "object") {
		errorList = [];
	}

	errorList.push({
		row: line - 1,
		text: message,
		type: "error"
	});

	editor.getSession().setAnnotations(errorList);

	editor.gotoLine(line);

	$("#error").append(html);
	$("#errorPanel").show();
	$("#formWrapper").hide();

	
}

function renderWarning(line, message) {
	var editor = ace.edit("input");
	var html = "<li><a href='#' onClick='goToLine(" + line + ");'>[line " + line + "] " + message + "</a></li>";

	var warningList = editor.getSession().getAnnotations();
	if (warningList == undefined || typeof warningList != "object") {
		warningList = [];
	}

	warningList.push({
		row: line - 1,
		text: message,
		type: "warning"
	});

	editor.getSession().setAnnotations(warningList);

	editor.gotoLine(line);

	$("#warning").append(html);
	$("#warningPanel").show();

}
