package ql.issue.problem;

import ql.ast.statement.question.Question;

public class DuplicateQuestionWithDifferentType extends Problem {

	public DuplicateQuestionWithDifferentType(Question question) {
		super.errorMessage = String
				.format("Duplicate question with a different type. Identifier: %s. At line number: %d",
						question.getVariable().getIdentifier(),
						question.getLineNumber());
	}
}
