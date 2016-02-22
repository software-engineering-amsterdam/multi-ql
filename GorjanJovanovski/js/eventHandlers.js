
function gotoLine(line){
	var editor = ace.edit("input");
	editor.gotoLine(line);
}

$("#generate").click(function(){
	var editor = ace.edit("input");
	initiate(editor.getValue());
});

$("#save").click(function(){
	saveAnswers();	
});

$(window).keypress(function(event) {
	var editor = ace.edit("input");
    if (!(event.which == 115 && event.ctrlKey) && !(event.which == 19)) return true;
    initiate(editor.getValue());
    event.preventDefault();
    return false;
});