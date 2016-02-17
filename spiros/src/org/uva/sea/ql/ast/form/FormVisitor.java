package org.uva.sea.ql.ast.form;


public interface FormVisitor<ASTNode> {

	public ASTNode visit(Form form);
	
}
