package ql.issue.problem;

public class ReferenceToUndefinedQuestion extends Problem {
	public ReferenceToUndefinedQuestion(String identifier, int lineNumber) {
		super.errorMessage = String
				.format("Reference to undifined question. Identifier: %s. On line number: %d",
						identifier, lineNumber);
	}
}