package org.uva.ql.ast;

import org.antlr.v4.runtime.ParserRuleContext;

public abstract class ASTNode {

	private final SourceCodeInfo sourceInfo;

	public ASTNode(ParserRuleContext context) {
		sourceInfo = context == null ? SourceCodeInfo.NULL_OBJECT : new SourceCodeInfo(context);
	}

	public abstract <T, U> T accept(ASTNodeVisitor<T, U> visitor, U context);

	public final String getSourceLocation() {
		return sourceInfo.toString();
	}

	public final String getSourceText() {
		return sourceInfo.getText();
	}

	@Override
	public String toString() {
		return getSourceLocation() + " " + getSourceText();
	}

	private static class SourceCodeInfo {

		private static final SourceCodeInfo NULL_OBJECT = new SourceCodeInfo(null);

		private final int line;
		private final int column;
		private final String text;

		public SourceCodeInfo(ParserRuleContext context) {
			StringBuilder textBuilder;

			if (context == null) {
				text = "";
				line = -1;
				column = -1;

				return;
			}

			textBuilder = new StringBuilder();
			for (int i = 0; i < context.getChildCount(); i++) {
				textBuilder.append(context.getChild(i).getText());
				textBuilder.append(" ");
			}

			text = textBuilder.toString();
			line = context.getStart().getLine();
			column = context.getStart().getCharPositionInLine() + 1;
		}

		public String getText() {
			return text;
		}

		@Override
		public String toString() {
			return "[" + line + ": " + column + "]";
		}
	}
}
