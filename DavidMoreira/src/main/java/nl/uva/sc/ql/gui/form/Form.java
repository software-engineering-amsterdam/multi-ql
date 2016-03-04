package nl.uva.sc.ql.gui.form;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import nl.uva.sc.ql.gui.State;

public class Form extends JFrame implements GuiInterface {
	
	private static final long serialVersionUID = 1L;
	
	private String name;
	private List<Question> questions;
	private List<IfForm> listIfForm;
	private State state;

	public Form(State state, String name){
		this.state = state;
		this.name = name;
		this.questions = new ArrayList<Question>();
		this.listIfForm = new ArrayList<IfForm>();
	}
	
	@Override
	public String getName(){
		return this.name;
	}
	
	public void addQuestion(Question question){
		this.questions.add(question);
	}
	
	public void addIfForm(IfForm ifForm){
		this.listIfForm.add(ifForm);
	}

	@Override
	public boolean runGui() {		
		for(Question q : questions){
			q.runGui();
			this.add(q);
		}
		
		for(IfForm i : listIfForm){
			i.runGui();
			this.add(i);
		}
		
		return true;
	}
	
	@Override
	public String toString(){
		String questionsText = "";
		String listIfFormText = "";
		
		for(Question q : questions){
			questionsText += q+"\n";
		}
		
		for(IfForm q : listIfForm){
			listIfFormText += q+"\n";
		}

		return "Form name: "+name+"\n"+"Questions: "+questionsText+"ListIfForm: "+listIfFormText;
	}
}
