package org.uva.sea.ql.ast.TaxForm;

import org.uva.sea.ql.ast.ASTNode;
import org.uva.sea.ql.ast.VarDeclaration;
import org.uva.sea.ql.ast.visitor.interfaces.QLNodeVisitor;
import org.uva.sea.ql.semantic.SymbolTable;

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
	public void accept(QLNodeVisitor qlPartVisitor) {
		qlPartVisitor.visit(this);
		
		
		
	}
}
