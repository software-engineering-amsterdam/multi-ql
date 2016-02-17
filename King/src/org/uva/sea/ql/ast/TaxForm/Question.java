package org.uva.sea.ql.ast.TaxForm;

import org.uva.sea.ql.ast.ASTNODE;
import org.uva.sea.ql.ast.VarDeclaration;
import org.uva.sea.ql.ast.TaxForm.interfaces.QLPart;
import org.uva.sea.ql.ast.TaxForm.interfaces.QLNodeVisitor;

public class Question extends ASTNODE implements QLPart {
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
