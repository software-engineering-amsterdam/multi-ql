function setHTMLEventHandlers() {
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

function goToLine(line) {
	var editor = ace.edit("input");
	editor.gotoLine(line);
}

$("#generate").click(function () {
	var editor = ace.edit("input");
	initiate(editor.getValue());
});

$("#save").click(function () {
	saveAnswers();
});

$(window).keypress(function (event) {
	var editor = ace.edit("input");
	if (!(event.which === 115 && event.ctrlKey) && event.which !== 19) return true;
	initiate(editor.getValue());
	event.preventDefault();
	return false;
});