package ql.ast;

public class ComputedQuestion extends Question {

	private Expr expression;
	
	public ComputedQuestion(String q_text, String identity, QuestionType typ) {
		super(q_text, identity, typ);
		// TODO Auto-generated constructor stub
	}
	
	public ComputedQuestion(String q_text, String identity, QuestionType type, Expr expr) {
		super(q_text, identity, type);
		// place extra field
		this.setExpression(expr);
	}

	public Expr getExpression() {
		return expression;
	}

	public void setExpression(Expr expression) {
		this.expression = expression;
	}

}
