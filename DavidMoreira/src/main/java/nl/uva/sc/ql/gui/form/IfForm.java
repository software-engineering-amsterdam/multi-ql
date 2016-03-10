package nl.uva.sc.ql.gui.form;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

public class IfForm implements GuiInterface, Observer {
	
	private List<ConditionBlockForm> listConditionBlock;
	private List<Question> questions;
	private JFrame jFrame;
	
	public IfForm(JFrame jFrame){
		this.jFrame = jFrame;
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
	public void createGui() {
		for(ConditionBlockForm i : listConditionBlock){
			i.createGui();
		}
		
		for(Question q : questions){
			q.createGui();
			jFrame.add(q);
		}		
	}

	@Override
	public void updateGui() {
		boolean result = evaluateConditions();
		
		for(Question q : questions){
			q.setVisible(result);
		}		
	}
	
	private boolean evaluateConditions(){
		for(ConditionBlockForm i : listConditionBlock){
			if(i.getResultOfCondition()){
				i.updateGui();
				return false;
			}
		}
		return true;
	}
	
	@Override
	public void update() {
		updateGui();
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
