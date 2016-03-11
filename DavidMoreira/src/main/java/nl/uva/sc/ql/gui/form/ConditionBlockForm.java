package nl.uva.sc.ql.gui.form;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import nl.uva.sc.ql.gui.State;
import nl.uva.sc.ql.parser.ast.ExpressionNode;
import nl.uva.sc.ql.parser.value.Value;

public class ConditionBlockForm implements GuiInterface, Observer, Subject {
	
    private List<Observer> observers = new ArrayList<Observer>();

	private ExpressionNode condition;
	private List<Question> questions;
	private State state;

	private JFrame jFrame;
	
	public ConditionBlockForm(JFrame jFrame, State state, ExpressionNode condition){
		//super(new BorderLayout());
		this.jFrame = jFrame;

		this.condition = condition;
		this.questions = new ArrayList<Question>();
		this.state = state;
		state.registerObserver(this);
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
			jFrame.add(q);
		}			
	}
	
	@Override
	public void updateGui() {
		for(Question q : questions){
			if(getResultOfCondition() == false){
				q.setVisible(false);
			} else {
				q.setVisible(true);
			}
		}
	}
	
	@Override
	public void update() {
		updateGui();
		notifyObservers();		
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
