package nl.uva.sc.ql.parser.ast;

import nl.uva.sc.ql.exceptions.NoValueException;
import nl.uva.sc.ql.parser.Visitor;

public abstract class Node {
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
		this.value = value;
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
}
