package nl.nicasso.ql.ast;

public class CodeLocation {

	private final int startLine;
	private final int startColumn;
	
	public CodeLocation(int startLine, int startColumn) {
		// @TODO Not sure if correct, but seems like the startline is one off. 
		this.startLine = startLine+1;
		this.startColumn = startColumn;
	}
	
	public int getStartLine() {
		return startLine;
	}
	
	public int getStartColumn() {
		return startColumn;
	}

	@Override
	public String toString() {
		return "Line: " + startLine +" Column: " + startColumn;
	}
	
}
