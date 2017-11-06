package ql2.conflict;

import ql2.ast.expression.Expr;
import ql2.ast.type.QuestionType;
import ql2.ast.type.UnknownType;
import ql2.conflict.Conflict.Level;

public class TypeMismatch extends Conflict {

	private Expr condition;
	private QuestionType expected;
	private QuestionType detected; 
	
	public TypeMismatch(Expr condition, QuestionType expected, QuestionType detected) {
		this.condition = condition;
		this.expected = expected;
		this.detected = detected;
		if(detected == null) {
			detected  = new UnknownType();
		}
		this.errorMsg = String.format("Expr type is not as expected! Expected '%s' but got '%s'", expected.getType(), detected.getType());
	}
	
	@Override
	public Level getConflictLevel() {
		return Level.ERROR;
	}
	
}
