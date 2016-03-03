package nl.uva.sc.ql.gui.form;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

public class Form extends JFrame implements GuiInterface {
	
	private static final long serialVersionUID = 1L;
	
	private String name;
	private List<Question> questions;
	private List<IfElse> ifElses;

	public Form(String name){
		this.name = name;
		this.questions = new ArrayList<Question>();
		this.ifElses = new ArrayList<IfElse>();
	}
	
	@Override
	public String getName(){
		return this.name;
	}
	
	public void addQuestion(Question question){
		this.questions.add(question);
	}
	
	public void addIfElse(IfElse ifElse){
		this.ifElses.add(ifElse);
	}

	@Override
	public boolean runGui() {		
		for(Question q : questions){
			q.runGui();
			this.add(q);
		}
		
		for(IfElse i : ifElses){
			i.runGui();
			this.add(i);
		}
		
		return true;
	}
	
	@Override
	public String toString(){
		String questionsText = "";
		String ifElsesText = "";
		
		for(Question q : questions){
			questionsText += q+"\n";
		}
		
		for(IfElse q : ifElses){
			ifElsesText += q+"\n";
		}

		return "Form name: "+name+"\n"+"Questions: "+questionsText+"IfElses: "+ifElsesText;
	}
}
