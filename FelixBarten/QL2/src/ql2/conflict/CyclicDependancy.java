package ql2.conflict;

import ql2.DependencyVisitor;
import ql2.ast.CalculatedQuestion;
import ql2.conflict.Conflict.Level;

public class CyclicDependancy extends Conflict {

	private CalculatedQuestion question;
	private String cycle;
	
	
	public CyclicDependancy(CalculatedQuestion dep, String id) {
		this.question = dep;
		this.cycle = id;
		this.errorMsg = String.format("Question %s has a cyclic dependency with question: %s", dep.getInput().getQuestionID(), id);
	}

	@Override
	public Level getConflictLevel() {
		return Level.CRITICAL;
	}
}
