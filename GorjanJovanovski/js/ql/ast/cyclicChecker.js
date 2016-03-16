function checkDependencies(ast) {
	var map = [];

	ast.transverseAST(
		(questionNode) => {
			if (questionNode instanceof ComputedQuestionNode) {
				map[questionNode.label] = questionNode.computedExpr.getLabelsInExpression();
			}
		});

	var madeChange = false;

	do {
		madeChange = false;
		for (var elementA in map) {
			if (map.hasOwnProperty(elementA)) {
				for (let elementB of map[elementA]) {
					if (map[elementB] !== undefined) {
						if (map[elementB].indexOf(elementA) !== -1) {
							throwError(0, "Cyclic dependencies detected between question '" + elementA + "' and question '" + elementB + "'");
							return false;
						}
						for (let elementC of map[elementB]) {
							if (map[elementA].indexOf(elementC) === -1) {
								map[elementA].push(elementC);
								madeChange = true;
							}
						}

					}
				}
			}
		}
	}
	while (madeChange);

	return true;
}