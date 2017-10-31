package ql2.conflict;

import ql2.ast.Question;
import ql2.ast.type.QuestionType;
import ql2.conflict.Conflict.Level;

public class RedefinedQuestion extends Conflict {
	private Question question;
	private String id;
	
	public RedefinedQuestion(Question question, String ID, QuestionType altType) {
		this.question = question;
		this.id = ID;
		this.errorMsg = String.format("There is already a question defined with this ID: %s and Type: ", id, altType);
	}
	
	@Override
	public Level getConflictLevel() {
		return Level.SEVERE;
	}
}
