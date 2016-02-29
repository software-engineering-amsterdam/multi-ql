package nl.uva.sc.ql.gui.form;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import nl.uva.sc.ql.exceptions.NoValueException;
import nl.uva.sc.ql.parser.ast.Node;

public class IfCondition extends JPanel implements QLGuiForm, Observer, Subject {

	private static final long serialVersionUID = 1L;
	
    private List<Observer> observers = new ArrayList<Observer>();

	private Node condition;
	private List<Question> questions;
	
	public IfCondition(Node condition){
		this.condition = condition;
		condition.registerObserver(this);
		this.questions = new ArrayList<Question>();
	}

	public void addQuestion(Question question){
		this.questions.add(question);
	}
	
	@Override
	public boolean createGui() {	
		// condition wasn't evaluated
		if(!condition.eval()) {
			resetPanel();
			return false;
		}
		
		// try if the condition has a value
		try {
			// the condition is false
			if(! (boolean) condition.getValue()){
				resetPanel();
				return false;
			}
		} catch(NoValueException e){
			resetPanel();
			return false;
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
		notifyObservers();
		this.validate();
	}
	
	public void resetPanel(){
		this.removeAll();
		
		this.validate();
	}

	// methods related with observer pattern
	
    public void registerObserver(Observer o){
        this.observers.add(o);
    }
    
    public void removeObserver(Observer o){
        int i = this.observers.indexOf(o);
        if (i >= 0){
        	this.observers.remove(i);
        }
    }

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
