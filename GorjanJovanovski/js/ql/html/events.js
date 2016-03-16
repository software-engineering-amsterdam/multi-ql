function goToLine(line) {
	var editor = ace.edit("input");
	editor.gotoLine(line);
}

function registerQuestionChangeListeners(ast, environment) {
	$("input").change(function () {
		notifyListeners($(this), ast);
		refreshGUI(ast, environment);
	});
}

function notifyListeners(element, ast) {
	var value = element.val();
	if (element.attr("type") === "checkbox") {
		value = element.is(":checked");
	}
	ast.notify(element.attr("name"), value);
}

function setOnClickListeners(ast) {

	$("#save").click(function () {
		saveAnswers(ast);
	});

	$(window).keypress(function (event) {
		var editor = ace.edit("input");
		if (!(event.which === 115 && event.ctrlKey) && event.which !== 19) return true;
		initiate(editor.getValue());
		event.preventDefault();
		return false;
	});
}

function saveAnswers(ast) {
	var answerList = ast.getAnswerList();
	var blob = new Blob([answerList.toString()], {type: "text/plain;charset=utf-8"});
	fileSaverSaveAs(blob, "answers.json");
}

$("#generate").click(function () {
	var editor = ace.edit("input");
	initiate(editor.getValue());
});

