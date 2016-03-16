function initiate(inputString) {
	resetGUI();
	var tree = getAntrlParseTree(inputString);
	var visitor = getAntlrVisitor();
	visitor.visitForm = function (ctx) {
		var ast = ctx.FormNode;
		var environment = new Environment();
		ast.initializeQuestions(environment);
		if (performAstChecks(ast, environment)) {
			renderQuestions(ast, environment);
			refreshGUI(ast, environment);
			setOnClickListeners(ast);
		}
	};
	tree.accept(visitor);
}
