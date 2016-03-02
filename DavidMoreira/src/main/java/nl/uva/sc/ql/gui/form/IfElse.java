package nl.uva.sc.ql.gui.form;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class IfElse extends JPanel implements QLGuiForm, Observer {

	private static final long serialVersionUID = 1L;
	
	private List<IfCondition> ifConditions;
	private List<Question> questions;
	
	public IfElse(){
		this.ifConditions = new ArrayList<IfCondition>();
		this.questions = new ArrayList<Question>();
	}
	
	public void addIfCondition(IfCondition ifCondition){
		this.ifConditions.add(ifCondition);
		ifCondition.registerObserver(this);
	}
	
	public void addQuestion(Question question){
		this.questions.add(question);
	}
	
	@Override
	public boolean createGui() {
		for(IfCondition i : ifConditions){
			if(i.createGui()){
				resetPanel();
				this.add(i);
				return true;
			}
		}
		
		for(Question q : questions){
			q.createGui();
			this.add(q);
		}
				
		return true;
	}
	
	@Override
	public void update() {
		createGui();
		this.validate();
	}
	
	public void resetPanel(){
		this.removeAll();
		
		this.validate();
	}
	
	@Override
	public String toString(){
		String questionsText = "";
		String ifConditionsText = "";

		for(Question q : questions){
			questionsText += q+"\n";
		}
		
		for(IfCondition q : ifConditions){
			ifConditionsText += q+"\n";
		}
		
		return "IfElse_ifcondition: "+ifConditionsText+"IfElse_questions: "+questionsText;
	}
}
