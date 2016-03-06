package nl.uva.sc.ql.gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nl.uva.sc.ql.gui.form.Observer;
import nl.uva.sc.ql.gui.form.Subject;
import nl.uva.sc.ql.parser.ast.IdentifierNode;
import nl.uva.sc.ql.parser.value.Value;

public class State implements Subject {
    private List<Observer> observers = new ArrayList<Observer>();

	private Map<IdentifierNode, Value> map;
		
	public State() {
		this.map = new HashMap<IdentifierNode, Value>();
	}

    public Value add(IdentifierNode node, Value value) {
    	Value oldValue = map.put(node, value);
		
    	if(oldValue != null && !oldValue.equals(value)){
    		notifyObservers();
		} else if (oldValue != value){
			notifyObservers();
		}
    	
		return oldValue;
    }

	public Value lookup(IdentifierNode node) {
		return map.get(node);
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
}
