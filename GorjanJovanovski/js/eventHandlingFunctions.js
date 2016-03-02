function goToLine(line) {
	var editor = ace.edit("input");
	editor.gotoLine(line);
}

function registerQuestionChangeListeners(ast) {
	$("input").change(function () {
		notifyListeners($(this), ast);
		refreshGUI(ast);
	});
}

function notifyListeners(element, ast) {
	var value = element.val();
	if (element.attr("type") === "checkbox") {
		value = element.is(":checked");
	}
	ast.listener.notify(element.attr("name"), value);
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

$("#generate").click(function () {
	var editor = ace.edit("input");
	initiate(editor.getValue());
});

