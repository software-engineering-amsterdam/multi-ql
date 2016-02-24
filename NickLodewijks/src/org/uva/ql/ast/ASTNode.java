package org.uva.ql.ast;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

public abstract class ASTNode {

	private final SourceCodeInfo token;

	public ASTNode(ParserRuleContext context) {
		token = context == null ? SourceCodeInfo.NULL_OBJECT : new SourceCodeInfo(context.getStart());
	}

	public abstract <T, U> T accept(ASTNodeVisitor<T, U> visitor, U context);

	public String getSourceLocation() {
		return token.toString();
	}

	public String getSourceText() {
		return token.getText();
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

		public SourceCodeInfo(Token token) {
			line = token == null ? -1 : token.getLine();
			charPositionInLine = token == null ? -1 : token.getCharPositionInLine() + 1;
			text = token == null ? "" : token.getText();
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
