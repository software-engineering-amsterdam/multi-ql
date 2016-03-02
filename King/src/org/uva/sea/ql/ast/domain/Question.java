package org.uva.sea.ql.ast.domain;

import org.uva.sea.ql.ast.ASTNode;
import org.uva.sea.ql.ast.VarDeclaration;
import org.uva.sea.ql.ast.expr.type.Type;
import org.uva.sea.ql.ast.visitor.interfaces.QLNodeVisitor;

public class Question extends ASTNode {
	private final VarDeclaration varDeclaration;
	private final String text;

	public Question(VarDeclaration varDeclaration, String text) {
		this.varDeclaration = varDeclaration;
		this.text = text;
	}

	public VarDeclaration getVariableId() {
		return varDeclaration;
	}

	public String getText() {
		return text;
	}

	@Override
	public Type accept(QLNodeVisitor qlPartVisitor) {
		return qlPartVisitor.visit(this);
		
		
		
	}
}
