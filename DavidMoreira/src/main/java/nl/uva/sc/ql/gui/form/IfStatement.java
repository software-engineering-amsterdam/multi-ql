package nl.uva.sc.ql.gui.form;

import java.util.ArrayList;
import java.util.List;

import nl.uva.sc.ql.gui.state.Observer;

public class IfStatement implements GuiRepresentation, Observer {
	
	private List<ConditionBlock> listConditionBlock;
	private List<Question> questions;
	
	private Form form;
	
	public IfStatement(Form form){
		this.form = form;
		this.listConditionBlock = new ArrayList<ConditionBlock>();
		this.questions = new ArrayList<Question>();
	}
	
	public void addConditionBlock(ConditionBlock conditionBlock){
		this.listConditionBlock.add(conditionBlock);
		conditionBlock.registerObserver(this);
	}
	
	public void addQuestion(Question question){
		this.questions.add(question);
	}
	
	@Override
	public void createGui() {
		for(ConditionBlock i : listConditionBlock){
			i.createGui();
		}
		
		for(Question q : questions){
			q.createGui();
			form.add(q);
		}
	}

	@Override
	public void updateGui() {
		boolean result = !wasConditionEvaluated();
		
		for(Question q : questions){
			q.setVisible(result);
		}
	}
	
	private boolean wasConditionEvaluated(){
		boolean evaluated = false;
		
		for(ConditionBlock i : listConditionBlock){
			if(!evaluated && i.getResultOfCondition()){
				i.setVisibility(true);
				evaluated = true;
			
			// if there was an evaluated if statement, make the rest invisible
			} else {
				i.setVisibility(false);
			}	
		}
		return evaluated;
	}
	
	@Override
	public void update() {
		updateGui();
		form.revalidate();
	}
	
	@Override
	public String toString(){
		String questionsText = "";
		String listConditionBlockText = "";

		for(Question q : questions){
			questionsText += q+"\n";
		}
		
		for(ConditionBlock q : listConditionBlock){
			listConditionBlockText += q+"\n";
		}
		
		return "IfForm_ifcondition: "+listConditionBlockText+"IfForm_questions: "+questionsText;
	}
}
