package ql2.conflict;

import ql2.ast.Expr;
import ql2.ast.type.QuestionType;
import ql2.ast.type.UnknownType;
import ql2.conflict.Conflict.Level;

public class InvalidConditionType extends Conflict{

	private Expr condition;
	private QuestionType expected;
	private QuestionType detected; 
	
	public InvalidConditionType(Expr condition, QuestionType expected, QuestionType detected) {
		this.condition = condition;
		this.expected = expected;
		this.detected = detected;
		if(detected == null) {
			detected  = new UnknownType(); // prevent nullpointer if detected == null.
		}
		this.errorMsg = String.format("Condition is not a boolean! Expected '%s' but got '%s'", expected.getType(), detected.getType());
	}

	@Override
	public Level getConflictLevel() {
		return Level.ERROR;
	}
}
