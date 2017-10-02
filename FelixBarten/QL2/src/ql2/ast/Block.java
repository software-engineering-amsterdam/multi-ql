package ql2.ast;

import java.util.ArrayList;
import java.util.List;

import ql2.ASTNode;
import ql2.BaseVisitor;

public class Block extends ASTNode {
	
	private List<Statement> statementsList = new ArrayList<Statement>();
	private List<Question> questionsList	= new ArrayList<Question>();

	public Block(List<Statement> statements, List<Question> questions) {
		setQuestionsList(questions);
		setStatementsList(statements);
	}

	public List<Question> getQuestionsList() {
		return questionsList;
	}

	public void setQuestionsList(List<Question> questionsList) {
		this.questionsList = questionsList;
	}

	public List<Statement> getStatementsList() {
		return statementsList;
	}

	public void setStatementsList(List<Statement> statementsList) {
		this.statementsList = statementsList;
	}

	@Override
	public <T> T accept(BaseVisitor<T> visitor) {
		// TODO Auto-generated method stub
		return null;
	}
}
