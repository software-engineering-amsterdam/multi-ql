package nl.uva.sc.ql.parser.ast;

public abstract class VariableNode extends Node {

	private String name;
	private String question;

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return this.name;
	}
	
	public void setQuestion(String question){
		this.question = question;
	}

	public String getQuestion(){
		return this.question;
	}
	
}
