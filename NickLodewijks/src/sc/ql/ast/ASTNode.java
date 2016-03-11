package sc.ql.ast;

import org.antlr.v4.runtime.ParserRuleContext;

public abstract class ASTNode {

	private SourceLocation sourceInfo = new SourceLocation();

	public ASTNode() {

	}

	public void setSourceInfo(SourceLocation info) {
		sourceInfo = info;
	}

	public final String getSourceLocation() {
		return sourceInfo.getSourceLocation();
	}

	public final String getSourceText() {
		return sourceInfo.getSourceText();
	}

	@Override
	public String toString() {
		return getSourceLocation() + " " + getSourceText();
	}

	public static class SourceLocation {

		private final int line;
		private final int column;
		private final String text;

		public SourceLocation() {
			text = "";
			line = -1;
			column = -1;
		}

		public SourceLocation(ParserRuleContext context) {
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
			return getSourceLocation() + " " + getSourceText();
		}
	}
}
