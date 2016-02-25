package org.uva.sea.ql.ast.TaxForm;

import org.uva.sea.ql.ast.ASTNode;
import org.uva.sea.ql.ast.visitor.interfaces.QLNodeVisitor;
import org.uva.sea.ql.semantic.SymbolTable;

public class Form extends ASTNode {
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
		body.accept(qlPartVisitor);
		
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (Question q : body.getQuestions()) {
		    builder.append(q);
		    builder.append(":");
		}
		builder.setLength(builder.length() - 1);
		builder.append(":::");
		for (IFblock b : body.getStatements()) {
		    builder.append(b);
		    builder.append(":");
		}
		builder.setLength(builder.length() - 1);
		return builder.toString();
	}
}
