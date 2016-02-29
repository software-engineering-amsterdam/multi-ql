function setHTMLEventHandlers() {
	$("input").change(function () {
		var label = $(this).attr("name");
		var value = $(this).val();
		if ($(this).attr("type") === "checkbox") {
			value = $(this).is(":checked");
		}
		ast.dataChanged(label, value);
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