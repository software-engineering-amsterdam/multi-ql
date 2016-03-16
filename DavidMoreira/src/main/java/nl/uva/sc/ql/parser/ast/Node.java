package nl.uva.sc.ql.parser.ast;

import nl.uva.sc.ql.parser.Visitor;

public abstract class Node {
	private int line;
	
	public void setLine(int line){
		this.line = line;
	}
	
	public int getLine(){
		return this.line;
	}
		
	public abstract String getType();
	public abstract void accept(Visitor visitor);
	
	public abstract void dump();
}
