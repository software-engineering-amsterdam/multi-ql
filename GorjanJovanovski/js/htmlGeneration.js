function setHandlers(){
	$("input").change(function(){
		var label = $(this).attr("name");
		if($(this).attr("type")=="checkbox"){
			getQuestion(label).setValue($(this).is(":checked"));
		}
		else{
			getQuestion(label).setValue($(this).val());
		}
		refreshGUI();
	});
}

function refreshGUI(){
	var stack = new Array();

	$(".questionDiv").hide();
	resetQuestionVisibility();

	for(var i=0;i<ast.queries.length;i++){
		stack.push(ast.queries[i]);
	}

	while(stack.length>0){
		var currentNode = stack.pop();
		if(currentNode instanceof QuestionNode){
			if(currentNode.computedExpr != undefined){
				currentNode.setValue(evaluateStmt(currentNode.computedExpr));
			}
			currentNode.visible = true;
			$(".questionDiv[label='"+currentNode.label+"']").show();
			$("input[name='"+currentNode.label+"']").val(currentNode.value);
		}
		else if(currentNode instanceof ConditionNode){
			var evalResult = evaluateStmt(currentNode.condition);
			if (evalResult) {
				for (var i=0; i<currentNode.queries.length; i++) {
					stack.push(currentNode.queries[i]);
				}	
			}
			else if(currentNode.elseQueries != undefined){
				for (var i=0; i<currentNode.elseQueries.length; i++) {
					stack.push(currentNode.elseQueries[i]);
				}	
			}
		}
	}
}

function generateQuestionHTML(question){
	var html = "<div class='questionDiv' label='" + question.label + "'>";
	html += "<label class='question'>" + question.text + " ";
    html += "<input name='"+ question.label + "' type='";

	switch(question.type){
		case "integer": 	html += "number";
							break;
		case "decimal": 	html += "number";
							break;
		case "money": 		html += "number";
							break;
		case "currency": 		html += "number";
							break;
		case "string": 		html += "text";
							break;
		case "date": 		html += "text";
							break;
		case "boolean": 	html += "checkbox";
							break;
		default: 			html += "text";
							break;
	}

	html += "'";

	if(question.computedExpr != undefined){
		html += " disabled";
	}

	html += "/></label></div>";

	return html;
}

function renderQuestions(){
	var questions = new Array();
	var stack = new Array();

	for(var i=0;i<ast.queries.length;i++){
		stack.push(ast.queries[i]);
	}

	while(stack.length>0){
		var currentNode = stack.pop();
		if(currentNode instanceof QuestionNode){
			questions.push(currentNode);
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

	questions = questions.reverse();

	var output = "";

	for(var i=0;i<questions.length;i++){
		output += generateQuestionHTML(questions[i]);
	}

	$("#output").html(output);
}

function resetInfoPanels(){
	$("#error").html("");
	$("#warning").html("");

	$("#errorPanel").hide();
	$("#warningPanel").hide();

	$("#formWrapper").show();
}

function renderError(panel, errorObj){
	var html = "<li><a href='#' onClick='editor.gotoLine(" + (errorObj.line) + ");'>[line " + errorObj.line + "] " + errorObj.msg + "</a></li>";

	if($("#error").html().indexOf(html)>=0) return;
	
	var errorList = editor.getSession().getAnnotations();
	if(errorList==undefined || typeof errorList != "object"){
		errorList = new Array();
	}

	errorList.push({
	  row: errorObj.line-1,
	  text: errorObj.msg,
	  type: "error"
	});

	editor.getSession().setAnnotations(errorList);

	editor.gotoLine(errorObj.line);

	$("#error").append(html);
	$("#errorPanel").show();
	$("#formWrapper").hide();

	
}

function renderWarning(panel, warningObj){
	var html = "<li><a href='#' onClick='editor.gotoLine(" + (warningObj.line) + ");'>[line " + warningObj.line + "] " + warningObj.msg + "</a></li>";

	if($("#warning").html().indexOf(html)>=0) return;

	var warningList = editor.getSession().getAnnotations();
	if(warningList==undefined || typeof warningList != "object"){
		warningList = new Array();
	}

	warningList.push({
	  row: warningObj.line-1,
	  text: warningObj.msg,
	  type: "warning"
	});

	editor.getSession().setAnnotations(warningList);

	editor.gotoLine(warningObj.line);

	$("#warning").append(html);
	$("#warningPanel").show();

}
