package org.uva.ql.ast;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

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
		return getSourceLocation();
	}

	private static class SourceCodeInfo {

		private static final SourceCodeInfo NULL_OBJECT = new SourceCodeInfo(null);

		private final int line;
		private final int charPositionInLine;
		private final String text;

		public SourceCodeInfo(ParserRuleContext context) {
			Token start;

			start = (context == null ? null : context.getStart());

			line = start == null ? -1 : start.getLine();
			charPositionInLine = start == null ? -1 : start.getCharPositionInLine() + 1;

			if (context == null) {
				text = "";
			} else {
				StringBuilder builder;

				builder = new StringBuilder();
				for (int i = 0; i < context.getChildCount(); i++) {
					builder.append(context.getChild(i).getText());
					builder.append(" ");
				}

				text = builder.toString();
			}
		}

		public int getLine() {
			return line;
		}

		public int getCharPos() {
			return charPositionInLine;
		}

		public String getText() {
			return text;
		}

		@Override
		public String toString() {
			return "[" + getLine() + ": " + getCharPos() + "]";
		}
	}
}
