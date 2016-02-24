function refreshGUI() {
	$(".questionDiv").hide();

	ast.resetQuestionVisibility();

	ast.transverseAST((questionNode) => {
		if (questionNode instanceof ComputedQuestionNode) {
			questionNode.setValue(questionNode.computedExpr.compute());
		}
		questionNode.visible = true;
		$(".questionDiv[qllabel='" + questionNode.label + "']").show();
		$("input[name='" + questionNode.label + "']").val(questionNode.value);
	}, undefined, true);

}

function generateQuestionHTML(question) {
	var html = "<div class='questionDiv' qllabel='" + question.label + "'>";
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
	ast.transverseAST((questionNode) => {
		output += generateQuestionHTML(questionNode);
	});
	$("#output").html(output);
}

function resetErrorPanels() {
	editor.getSession().clearAnnotations();
	$("#error").html("");
	$("#warning").html("");

	$("#errorPanel").hide();
	$("#warningPanel").hide();

	$("#formWrapper").show();
}

function saveAnswers() {
	var answerList = ast.getAnswerList();
	var blob = new Blob([answerList.toString()], {type: "text/plain;charset=utf-8"});
	fileSaverSaveAs(blob, "answers.json");
}


function renderDebugMessage(type, line, message) {
	var editor = ace.edit("input");
	message = message.replace("<", "&lt;").replace(">", "&gt;")
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