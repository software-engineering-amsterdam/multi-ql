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

function generateQuestionHTML(questionNode) {
	var html = "<div class='questionDiv' qllabel='" + questionNode.label + "'>";
	html += "<label class='question'>" + questionNode.text + " ";
	html += "<input name='" + questionNode.label + "' type='";

	if (questionNode.type instanceof NumberType || questionNode.type instanceof DecimalType) {
		html += "number";
	}
	else if (questionNode.type instanceof BooleanType) {
		html += "checkbox";
	}
	else {
		html += "text";
	}

	html += "'";


	if (questionNode instanceof ComputedQuestionNode) {
		html += " disabled";
	}

	html += "/></label></div>";

	return html;
}

function renderQuestions() {
	ast.transverseAST((questionNode) => {
		$("#output").append(generateQuestionHTML(questionNode));
		$("input[name=" + questionNode.label + "]").change(function () {
			var value = $(this).val();
			if ($(this).attr("type") === "checkbox") {
				value = $(this).is(":checked");
			}
			questionNode.notify(value);
			refreshGUI();
		});
	});

}

function resetGUI() {

	var editor = ace.edit("input");
	editor.getSession().clearAnnotations();
	$("#error").html("");
	$("#warning").html("");

	$("#output").html("");

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
	message = message.replace("<", "&lt;").replace(">", "&gt;");
	var html;
	if (line > 0) {
		html = "<li><a href='#' onClick='goToLine(" + line + ");'>[line " + line + "] " + message + "</a></li>";

	}
	else {
		html = "<li><a href='#' >" + message + "</a></li>";
	}

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