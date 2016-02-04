package org.uva.sea.ql.ast.form;

import org.uva.sea.ql.ast.ASTNode;
import org.uva.sea.ql.ast.ASTNodeVisitor;
import org.uva.sea.ql.ast.Result;

public class Form extends ASTNode {

	private String name;
	private Block body;

	public Form(String id, Block body) {
		this.name = id;
		this.body = body;
	}

	public String getName() {
		return name;
	}

	@Override
	public void accept(ASTNodeVisitor visitor) {
		visitor.visit(this);

		body.accept(visitor);
	}

	@Override
	public Result validate() {
		return Result.TRUE();
	}
}
