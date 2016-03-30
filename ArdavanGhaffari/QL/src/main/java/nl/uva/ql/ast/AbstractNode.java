package nl.uva.ql.ast;

public class AbstractNode {
	private final int line;
	
	public AbstractNode(int line) {
		this.line = line;
	}
	
	public int getLine() {
		return line;
	}
}