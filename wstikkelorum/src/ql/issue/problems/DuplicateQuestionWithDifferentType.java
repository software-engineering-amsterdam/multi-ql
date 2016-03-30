package ql.issue.problems;

import ql.ast.statement.Question;
import ql.issue.Issue;

public class DuplicateQuestionWithDifferentType extends Issue {

	public DuplicateQuestionWithDifferentType(Question question) {
		super.errorMessage = String
				.format("Duplicate question with a different type. Identifier: %s. At line number: %d",
						question.getVariable().getIdentifier(),
						question.getLineNumber());
	}
}
