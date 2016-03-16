package nl.uva.sc.ql.gui.form;

import java.util.ArrayList;
import java.util.List;

import nl.uva.sc.ql.gui.state.Observer;

public class IfForm implements GuiInterface, Observer {
	
	private List<ConditionBlockForm> listConditionBlock;
	private List<Question> questions;
	
	private Form form;
	
	public IfForm(Form form){
		this.form = form;
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
			form.add(q);
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
		boolean evaluated = false;
		
		for(ConditionBlockForm i : listConditionBlock){
			if(!evaluated && i.getResultOfCondition()){
				i.setVisibility(true);
				evaluated = true;
			
			// if there was an evaluated if statement, make the rest invisible
			} else {
				i.setVisibility(false);
			}	
		}
		return true;
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
		
		for(ConditionBlockForm q : listConditionBlock){
			listConditionBlockText += q+"\n";
		}
		
		return "IfForm_ifcondition: "+listConditionBlockText+"IfForm_questions: "+questionsText;
	}
}
