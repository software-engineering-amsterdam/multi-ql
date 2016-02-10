package org.uva.sea.ql.ast;

import org.antlr.v4.runtime.Token;
import org.uva.sea.ql.ast.expr.Context;

public abstract class ASTNode {

	private Token token;

	public abstract void accept(ASTNodeVisitor visitor);

	public abstract Result validate();

	public void setToken(Token start) {
		this.token = start;
	}

	public int getLineIndex() {
		return token.getLine();
	}

	public int getCharIndex() {
		return token.getCharPositionInLine() + 1;
	}

	public String getText() {
		return token.getText();
	}

	@Override
	public String toString() {
		return "[" + getLineIndex() + ": " + getCharIndex() + "]";
	}
}
