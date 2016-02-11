function setHandlers(){
	$("input").change(function(){
		var label = $(this).attr("name");
		if($(this).attr("type")=="checkbox"){
			getQuestion(label).value = $(this).is(":checked");
		}
		else{
			getQuestion(label).value = $(this).val();
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
				currentNode.value = evaluateStmt(currentNode.computedExpr);
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

function displayErrors(panel, set, critical){
	var html = "<ul>";
	var editorErrors = new Array();
	
	set.forEach(function(errObj) {
		editorErrors.push({
		  row: errObj.line-1,
		  text: errObj.error,
		  type: panel
		});
		
	  	html += "<li><a href='#' onClick='editor.gotoLine(" + (errObj.line) + ");'>" + errObj.error + "</a></li>";
	});

	editor.getSession().setAnnotations(editorErrors);

	html += "</ul>";

	$("#"+panel).html(html);
	$("#"+panel+"panel").show();

	if(critical){
		$("#formWrapper").hide();
	}
}
