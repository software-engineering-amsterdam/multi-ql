$("#generate").click(function(){
	initiate(editor.getValue());
});

$("#save").click(function(){
	var answers = new Array();


	var stack = new Array();
	
	for(var i=0;i<ast.queries.length;i++){
		stack.push(ast.queries[i]);
	}

	while(stack.length>0){
		var currentNode = stack.pop();
		if(currentNode instanceof QuestionNode && currentNode.visible){
			var answer = {};
			answer.questionLabel = currentNode.label;
			answer.questionText = currentNode.text;
			answer.questionType = currentNode.type;
			answer.value = currentNode.value;
			answers.push(answer);
		}
		else if(currentNode instanceof ConditionNode){
			for (var i=0; i<currentNode.queries.length; i++) {
				stack.push(currentNode.queries[i]);
			}
			if(currentNode.elseQueries != undefined){
				for (var i=0; i<currentNode.elseQueries.length; i++) {
					stack.push(currentNode.elseQueries[i]);
				}
			}
		}
	}

	answers = answers.reverse();

	var blob = new Blob([JSON.stringify(answers, null, 2)], {type: "text/plain;charset=utf-8"});
	saveAs(blob, "answers.txt");
	
});

$(window).keypress(function(event) {
    if (!(event.which == 115 && event.ctrlKey) && !(event.which == 19)) return true;
    initiate(editor.getValue());
    event.preventDefault();
    return false;
});

//Chrome -.-
$(window).keydown(function(event) {
    if (!(event.which == 115 && event.ctrlKey) && !(event.which == 19)) return true;
    initiate(editor.getValue());
    event.preventDefault();
    return false;
});
