package uva.ql.ast.question;

import uva.ql.ast.Node;
import uva.ql.ast.variable.Variable;


public class QuestionVanilla extends Question {
	
	public QuestionVanilla(Node parent, int startLine, int startColumn, String label, Variable variable) {
		super(parent, startLine, startColumn, label, variable);
	}
}
