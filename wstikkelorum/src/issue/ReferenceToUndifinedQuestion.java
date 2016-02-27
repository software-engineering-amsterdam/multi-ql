package issue;

public class ReferenceToUndifinedQuestion extends Issue{
	public ReferenceToUndifinedQuestion(String identifier, int lineNumber){
		super.errorMessage = String.format("Reference to undifined question. Identifier: %s. On line number: %d",
				identifier, lineNumber);
	}
}
