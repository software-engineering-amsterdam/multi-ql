package ql2.conflict;

import ql2.ast.Question;
import ql2.conflict.Conflict.Level;

public class DuplicateQuestionID extends Conflict {

	public DuplicateQuestionID(Question question, String ID) {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Level getConflictLevel() {
		return Level.WARNING;
	}
}
