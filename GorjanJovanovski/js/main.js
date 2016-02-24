var ast;

var editor = ace.edit("input");
initiate(editor.getValue());

function saveAnswers() {
	var answerList = ast.getAnswerList();
	var blob = new Blob([answerList.toString()], {type: "text/plain;charset=utf-8"});
	fileSaverSaveAs(blob, "answers.json");
}
