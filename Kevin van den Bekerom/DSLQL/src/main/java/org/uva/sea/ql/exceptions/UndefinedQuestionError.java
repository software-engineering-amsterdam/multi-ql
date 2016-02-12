package org.uva.sea.ql.exceptions;

import org.uva.sea.ql.ast.ASTNode;

public class UndefinedQuestionError extends Error {
	String message;
	
	public UndefinedQuestionError(ASTNode n) {
		super("The question " + n.toString() + " at line TODO! can not be reached!");
	}
}
