package nl.uva.sc.ql.gui.form;

import java.util.ArrayList;
import java.util.List;

import nl.uva.sc.ql.gui.state.Observer;
import nl.uva.sc.ql.gui.state.State;
import nl.uva.sc.ql.gui.state.Subject;
import nl.uva.sc.ql.parser.ast.ExpressionNode;
import nl.uva.sc.ql.parser.value.Value;

public class ConditionBlock implements GuiRepresentation, Observer, Subject {
	
    private List<Observer> observers = new ArrayList<Observer>();

	private ExpressionNode condition;
	private List<Question> questions;
	private State state;

	private Form form;
	
	public ConditionBlock(Form form, State state, ExpressionNode condition){
		this.form = form;
		this.condition = condition;
		this.questions = new ArrayList<Question>();
		this.state = state;
		
		state.registerObserverForExpressionNode(this, condition);
	}

	public void addQuestion(Question question){
		this.questions.add(question);
	}
	
	public boolean getResultOfCondition(){
		Value value = condition.eval(state);
		if (value == null){
			return false;
		}
		return (boolean) value.getValue();
	}
	
	@Override
	public void createGui() {		
		for(Question q : questions){
			q.createGui();
			form.add(q);
		}	
	}
	
	@Override
	public void updateGui() {
		notifyObservers();
	}
	
	public void setVisibility(boolean visible){
		for(Question q : questions){
			q.setVisible(visible);
		}
	}
	
	@Override
	public void update() {
		updateGui();
		form.revalidate();
	}
	
	// methods related with observer pattern
	
	@Override
    public void registerObserver(Observer o){
        this.observers.add(o);
    }
    
	@Override
    public void removeObserver(Observer o){
        int i = this.observers.indexOf(o);
        if (i >= 0){
        	this.observers.remove(i);
        }
    }

	@Override
    public void notifyObservers(){
        for(int i = 0; i < this.observers.size(); i++){
            Observer o = (Observer) this.observers.get(i);
            o.update();
        }
    }
	
	@Override
	public String toString(){
		String questionsText = "";
		
		for(Question q : questions){
			questionsText += q+"\n";
		}
		
		return "IfCondition_questions: "+questionsText;
	}
}
