package org.uva.ql.ast;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

public abstract class ASTNode {

	private Token token;

	public ASTNode(ParserRuleContext context) {
		token = context.getStart();
	}

	public abstract <T, U> T accept(ASTNodeVisitor<T, U> visitor, U context);

	public void setToken(Token start) {
		this.token = start;
	}

	public String getSourceLocation() {
		return "[" + token.getLine() + ": " + (token.getCharPositionInLine() + 1) + "]";
	}

	public String getSourceText() {
		return token.getText();
	}

	@Override
	public String toString() {
		return getSourceLocation();
	}
}
