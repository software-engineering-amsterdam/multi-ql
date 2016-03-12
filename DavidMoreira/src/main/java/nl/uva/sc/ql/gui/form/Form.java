package nl.uva.sc.ql.gui.form;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class Form extends JPanel implements GuiInterface {
	
	private static final long serialVersionUID = 1L;
	
	private String name;
	private List<Question> questions;
	private List<IfForm> listIfForm;

	public Form(String name){
		this.name = name;
		this.questions = new ArrayList<Question>();
		this.listIfForm = new ArrayList<IfForm>();		
	}
	
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
	public void createGui() {	
		for(Question q : questions){
			q.createGui();
			this.add(q);
		}
		
		for(IfForm i : listIfForm){
			i.createGui();
		}
	}
	
	@Override
	public void updateGui() {
		for(Question q : questions){
			q.setVisible(true);
		}
		
		for(IfForm i : listIfForm){
			i.updateGui();
		}
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
