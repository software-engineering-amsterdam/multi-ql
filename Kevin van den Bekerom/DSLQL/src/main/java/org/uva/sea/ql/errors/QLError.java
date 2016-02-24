package org.uva.sea.ql.errors;

import org.uva.sea.ql.ast.ASTNode;

public class QLError {
	private ASTNode node;
	private String message;
	
	public QLError(ASTNode n, String message) {
		this.node = n;
		this.message = message;
	}
	
	public String getErrorMessage() {
		StringBuilder sb = new StringBuilder();
		sb.append("[ERROR on line ")
		.append(node.getStartLine())
		.append("] : ")
		.append(node.toString())
		.append(" ")
		.append(message);
		return sb.toString();
	}
}	
