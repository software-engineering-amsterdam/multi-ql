function refreshGUI(ast, environment) {
	$(".questionDiv").hide();
	ast.transverseAST((questionNode) => {
		$(".questionDiv[qllabel='" + questionNode.label + "']").show();
		$("input[name='" + questionNode.label + "']").val(environment.getValue(questionNode.label));
	}, undefined, true, environment);
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