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

function renderQuestions(ast, environment) {
	ast.transverseAST((questionNode) => {
		$("#output").append(generateQuestionHTML(questionNode, environment));
	});
	registerQuestionChangeListeners(ast, environment);

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
