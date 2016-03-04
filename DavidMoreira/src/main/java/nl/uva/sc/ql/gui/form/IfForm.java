package nl.uva.sc.ql.gui.form;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import nl.uva.sc.ql.gui.State;

public class IfForm extends JPanel implements GuiInterface, Observer {

	private static final long serialVersionUID = 1L;
	
	private State state;
	private List<ConditionBlockForm> listConditionBlock;
	private List<Question> questions;
	
	public IfForm(State state){
		this.state = state;
		this.listConditionBlock = new ArrayList<ConditionBlockForm>();
		this.questions = new ArrayList<Question>();
	}
	
	public void addConditionBlock(ConditionBlockForm conditionBlock){
		this.listConditionBlock.add(conditionBlock);
		conditionBlock.registerObserver(this);
	}
	
	public void addQuestion(Question question){
		this.questions.add(question);
	}
	
	@Override
	public boolean runGui() {
		for(ConditionBlockForm i : listConditionBlock){
			if(i.runGui()){
				resetPanel();
				this.add(i);
				return true;
			}
		}
		
		for(Question q : questions){
			q.runGui();
			this.add(q);
		}
				
		return true;
	}
	
	@Override
	public void update() {
		runGui();
		this.validate();
	}
	
	public void resetPanel(){
		this.removeAll();
		
		this.validate();
	}
	
	@Override
	public String toString(){
		String questionsText = "";
		String listConditionBlockText = "";

		for(Question q : questions){
			questionsText += q+"\n";
		}
		
		for(ConditionBlockForm q : listConditionBlock){
			listConditionBlockText += q+"\n";
		}
		
		return "IfForm_ifcondition: "+listConditionBlockText+"IfForm_questions: "+questionsText;
	}
}
