package nl.uva.sc.ql.gui.form;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import nl.uva.sc.ql.gui.State;
import nl.uva.sc.ql.parser.ast.ExpressionNode;
import nl.uva.sc.ql.parser.value.Value;

public class ConditionBlockForm extends JPanel implements GuiInterface, Observer, Subject {

	private static final long serialVersionUID = 1L;
	
    private List<Observer> observers = new ArrayList<Observer>();

	private ExpressionNode condition;
	private List<Question> questions;
	private State state;
	
	public ConditionBlockForm(State state, ExpressionNode condition){
		this.condition = condition;
		this.questions = new ArrayList<Question>();
		this.state = state;
		state.registerObserver(this);
	}

	public void addQuestion(Question question){
		this.questions.add(question);
	}
	
	@Override
	public boolean runGui() {	
		
		Value value = condition.eval(state);
		if(value == null || (boolean) value.getValue() == false) {
			resetPanel();
			return false;
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
		notifyObservers();
		this.validate();
	}
	
	public void resetPanel(){
		this.removeAll();
		
		this.validate();
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
