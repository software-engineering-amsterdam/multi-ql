package ql.issue;

public class ReferenceToUndefinedQuestion extends Issue{
	public ReferenceToUndefinedQuestion(String identifier, int lineNumber){
		super.errorMessage = String.format("Reference to undifined question. Identifier: %s. On line number: %d",
				identifier, lineNumber);
	}
}
