package nl.uva.sc.ql.gui.state;

import java.util.ArrayList;
import java.util.List;

import nl.uva.sc.ql.compiler.parser.value.Value;

public class ElementMap implements Subject {

	private Value value;
    private List<Observer> observers = new ArrayList<Observer>();

	public ElementMap(Value value) {
		this.value = value;
	}
	
	public Value getValue(){
		return this.value;
	}
	
	public void setObservers(List<Observer> observers){
		this.observers = observers;
	}

	public List<Observer> getObservers() {
		return this.observers;
	}
	
	@Override
	public boolean equals(Object o){
        if(this == o) {
            return true;
        }

        ElementMap that = (ElementMap) o;

        return this.value.equals(that.getValue());
    }
	
    @Override
    public int hashCode() {
        return this.value.hashCode();
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
