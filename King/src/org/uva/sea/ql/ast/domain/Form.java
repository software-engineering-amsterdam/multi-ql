package org.uva.sea.ql.ast.domain;

import org.uva.sea.ql.ast.ASTNode;
import org.uva.sea.ql.ast.expr.type.Type;
import org.uva.sea.ql.ast.visitor.interfaces.QLNodeVisitor;

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
	public Type accept(QLNodeVisitor qlPartVisitor) {
		body.accept(qlPartVisitor);
		return qlPartVisitor.visit(this);
		
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
