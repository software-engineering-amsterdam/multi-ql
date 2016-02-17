package org.uva.sea.ql.ast.TaxForm;

import org.uva.sea.ql.ast.ASTNODE;
import org.uva.sea.ql.ast.TaxForm.interfaces.QLPart;
import org.uva.sea.ql.ast.TaxForm.interfaces.QLNodeVisitor;

public class Form extends ASTNODE implements QLPart{
	private String id;
	private Block body;

	public Form(String id, Block body) {
		this.id = id;
		this.body = body;
	}

	public String getName() {
		return id;
	}
	
	public Block getBody() {
		return body;
	}

	@Override
	public void accept(QLNodeVisitor qlPartVisitor) {
		qlPartVisitor.visit(this);
		
	}
}
