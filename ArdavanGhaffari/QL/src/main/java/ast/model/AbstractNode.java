package ast.model;

public class AbstractNode {
	private int line;
	
	public AbstractNode(int line) {
		this.line = line;
	}
	
	public int getLine() {
		return line;
	}
}