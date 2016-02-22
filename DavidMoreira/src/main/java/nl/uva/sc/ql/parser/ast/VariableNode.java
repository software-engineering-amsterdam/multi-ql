package nl.uva.sc.ql.parser.ast;

import javax.swing.JComponent;

import nl.uva.sc.ql.parser.Visitor;

public abstract class VariableNode extends Node {

	private String name;
	private String question;
	private boolean hasDoneQuestion = false;

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
	
	public void doneQuestion(){
		this.hasDoneQuestion = true;
	}
	
	public boolean hasDoneQuestion(){
		return this.hasDoneQuestion;
	}
	
	public abstract JComponent getComponent();

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
	
}
