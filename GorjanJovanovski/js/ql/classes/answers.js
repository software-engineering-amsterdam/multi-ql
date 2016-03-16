class AnswerList {
	constructor() {
		this.answerList = [];
	}

	addQuestion(questionNode, environment) {
		this.answerList.push(new AnswerListQuestion(questionNode, environment));
	}

	toString() {
		return JSON.stringify(this.answerList, null, 2);
	}
}

class AnswerListQuestion {
	constructor(questionNode, environment) {
		this.label = questionNode.label;
		this.text = questionNode.text;
		this.value = environment.getValue(questionNode.label);
	}
}