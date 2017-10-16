package ql2.conflict;

import ql2.ast.Question;
import ql2.conflict.Conflict.Level;

public class DuplicateQuestionID extends Conflict {
	private Question question;
	private String id;
	
	public DuplicateQuestionID(Question question, String ID) {
		this.question = question;
		this.id = ID;
		this.errorMsg = String.format("There is already a question defined with ID: %s", id);
	}
	
	@Override
	public Level getConflictLevel() {
		return Level.ERROR;
	}
}
