package ql2.conflict;

import ql2.ast.Question;

public class DuplicateLabel extends Conflict{

	private Question question;
	private String label;
	
	public DuplicateLabel(Question question, String label) {
		this.question = question;
		this.label = label;
		this.errorMsg = String.format("There is already a question defined with label: %s", label);
	}
	
	@Override
	public Level getConflictLevel() {
		return Level.WARNING;
	}
}
