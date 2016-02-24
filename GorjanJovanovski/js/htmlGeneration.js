function setHandlers() {
	$("input").change(function () {
		var label = $(this).attr("name");

		var questionNode = ast.getQuestion(label);
		if (questionNode === undefined) return;

		if ($(this).attr("type") === "checkbox") {
			questionNode.setValue($(this).is(":checked"));
		}
		else {
			questionNode.setValue($(this).val());
		}
		refreshGUI();
	});
}

function refreshGUI() {
	var stack = [];

	$(".questionDiv").hide();
	ast.resetQuestionVisibility();

	for (var i = 0; i < ast.block.length; i++) {
		stack.push(ast.block[i]);
	}

	while (stack.length > 0) {
		var currentNode = stack.shift();
		if (currentNode instanceof QuestionNode) {
			if (currentNode instanceof ComputedQuestionNode) {
				currentNode.setValue(currentNode.computedExpr.compute());
			}
			currentNode.visible = true;
			$(".questionDiv[label='" + currentNode.label + "']").show();
			$("input[name='" + currentNode.label + "']").val(currentNode.value);
		}
		else {
			var evalResult = currentNode.condition.compute();
			if (evalResult) {
				for (i = 0; i < currentNode.ifBlock.length; i++) {
					stack.push(currentNode.ifBlock[i]);
				}
			}
			else if (currentNode.elseBlock !== undefined) {
				for (i = 0; i < currentNode.elseBlock.length; i++) {
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
	ast.transverseAST(function (questionNode) {
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

function renderDebugMessage(type, line, message) {
	var editor = ace.edit("input");
	var html = "<li><a href='#' onClick='goToLine(" + line + ");'>[line " + line + "] " + message + "</a></li>";
	
	var debugAnnotationList = editor.getSession().getAnnotations();
	if (debugAnnotationList === undefined || typeof debugAnnotationList !== "object") {
		debugAnnotationList = [];
	}

	debugAnnotationList.push({
		row: line - 1,
		text: message,
		type: type
	});

	editor.getSession().setAnnotations(debugAnnotationList);

	editor.gotoLine(line);

	$("#" + type).append(html);
	$("#" + type + "Panel").show();
	$("#formWrapper").hide();

	
}