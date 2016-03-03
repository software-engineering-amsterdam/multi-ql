package nl.uva.sc.ql.parser.ast;

import java.util.ArrayList;
import java.util.List;

import nl.uva.sc.ql.exceptions.NoValueException;
import nl.uva.sc.ql.gui.form.Observer;
import nl.uva.sc.ql.gui.form.Subject;
import nl.uva.sc.ql.parser.Visitor;

public abstract class Node implements Subject {
    private List<Observer> observers = new ArrayList<Observer>();
	
	private Node left = null;
	private Node right = null;
	private int line;
	private Object value = null;

	public void init(Node left, Node right){
		this.left = left;
		this.right = right;
	}
	
	public Node getLeft(){
		return this.left;
	}
	
	public Node getRight(){
		return this.right;
	}
	
	public void setLine(int line){
		this.line = line;
	}
	
	public int getLine(){
		return this.line;
	}

	public Object getValue() {
		if (this.value == null) {
			throw new NoValueException();
		}
		return this.value;
	}
	
	public void setValue(Object value) {
		Object oldValue = this.value; 
		
		if (oldValue != null && oldValue.equals(value)){
			return;
		}
		
		this.value = value;
		notifyObservers();
	}
	
	@Override
	public boolean equals(Object node){
        if(value == node) {
            return true;
        }

        if(value == null || node == null || node.getClass() != value.getClass()) {
            return false;
        }

        Node that = (Node) node;

        return this.value.equals(that.value);	
    }
		
	public abstract String getType();
	public abstract void accept(Visitor visitor);
	
	public abstract void dump();
	
	public boolean eval() {
		return true;
	}
	
	// methods related with observer pattern
	
	@Override
    public void registerObserver(Observer o){
        this.observers.add(o);
        
        if (left != null) { left.registerObserver(o); }
        if (right != null) { right.registerObserver(o); }
    }
    
	@Override
    public void removeObserver(Observer o){
        int i = this.observers.indexOf(o);
        if (i >= 0){
        	this.observers.remove(i);
        	
            if (left != null) { left.removeObserver(o); }
            if (right != null) { right.removeObserver(o); }
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
