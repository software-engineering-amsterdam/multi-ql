package org.uva.sea.ql.errors;

import org.uva.sea.ql.ast.ASTNode;

public class QLError {
	protected String errorMessage;
	
	public QLError(ASTNode n, String message) {
		StringBuilder sb = new StringBuilder();
		sb.append(n.toString())
		.append(" on line ")
		.append(n.getStartLine())
		.append(" ")
		.append(message);
		this.errorMessage = sb.toString();
	}
	
	public String getErrorMessage() {
		return this.errorMessage;
	}
}	
