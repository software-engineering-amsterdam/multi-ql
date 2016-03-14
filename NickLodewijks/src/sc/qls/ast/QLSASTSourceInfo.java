package sc.qls.ast;

import org.antlr.v4.runtime.ParserRuleContext;

public class QLSASTSourceInfo {

	private final int line;
	private final int column;
	private final String text;

	public QLSASTSourceInfo() {
		text = "";
		line = -1;
		column = -1;
	}

	public QLSASTSourceInfo(ParserRuleContext context) {
		StringBuilder textBuilder;

		textBuilder = new StringBuilder();
		for (int i = 0; i < context.getChildCount(); i++) {
			textBuilder.append(context.getChild(i).getText());
			textBuilder.append(" ");
		}

		text = textBuilder.toString();
		line = context.getStart().getLine();
		column = context.getStart().getCharPositionInLine() + 1;
	}

	public String getSourceLocation() {
		return "[" + line + ": " + column + "]";
	}

	public String getSourceText() {
		return text;
	}

	@Override
	public String toString() {
		return "[" + line + ": " + column + "] " + text;
	}
}