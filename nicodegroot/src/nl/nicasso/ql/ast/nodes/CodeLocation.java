package nl.nicasso.ql.ast.nodes;

public class CodeLocation {

	private final int startLine;
	private final int startColumn;

	public CodeLocation(int startLine, int startColumn) {
		this.startLine = startLine;
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
		return "Line: " + startLine + " Column: " + startColumn;
	}

}
