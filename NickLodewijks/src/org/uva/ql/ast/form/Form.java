package org.uva.ql.ast.form;

import org.antlr.v4.runtime.ParserRuleContext;
import org.uva.ql.ast.ASTNode;
import org.uva.ql.ast.ASTNodeVisitor;

public class Form extends ASTNode {

	private final String name;
	private final Block body;

	public Form(ParserRuleContext context, String id, Block body) {
		super(context);
		this.name = id;
		this.body = body;
	}

	public Block getBody() {
		return body;
	}

	public String getName() {
		return name;
	}

	@Override
	public <T, U> T accept(ASTNodeVisitor<T, U> visitor, U context) {
		return visitor.visit(this, context);
	}
}
