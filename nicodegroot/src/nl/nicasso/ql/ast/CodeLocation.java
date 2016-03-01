package nl.nicasso.ql.ast;

import org.antlr.v4.runtime.ParserRuleContext;

public class CodeLocation {

	private final int startLine;
	private final int endLine;
	private final int startColumn;
	private final int endColumn;
	
	public CodeLocation(int startLine, int endLine, int startColumn, int endColumn) {
		this.startLine = startLine;
		this.endLine = endLine;
		this.startColumn = startColumn;
		this.endColumn = endColumn;
	}
	
	public int getEndLine() {
		return endLine;
	}
	
	public int getStartLine() {
		return startLine;
	}
	
	public int getStartColumn() {
		return startColumn;
	}

	public int getEndColumn() {
		return endColumn;
	}
	
	public String getLocation() {
		return "LOCATIE KOMT HIER";
	}
	
	public static CodeLocation getCodeLocation(ParserRuleContext ctx) {
		return new CodeLocation(ctx.getStart().getLine(), ctx.getStop().getLine(), ctx.getStart().getStartIndex(), ctx.getStop().getStopIndex());
	}
	
}
