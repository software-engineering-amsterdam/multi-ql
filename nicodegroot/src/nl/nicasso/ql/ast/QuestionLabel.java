package nl.nicasso.ql.ast;

public class QuestionLabel extends ASTNode {

	String label;

	public QuestionLabel(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

}