package org.uva.ql.ast.form;

import org.antlr.v4.runtime.ParserRuleContext;
import org.uva.ql.ast.ASTNode;
import org.uva.ql.ast.ASTNodeVisitor;
import org.uva.ql.ast.QLFormVisitor;

public class QLForm extends ASTNode {

	private final String name;
	private final QLBlock body;

	public QLForm(ParserRuleContext context, String id, QLBlock body) {
		super(context);
		this.name = id;
		this.body = body;
	}

	public QLBlock getBody() {
		return body;
	}

	public String getName() {
		return name;
	}

	@Override
	public <T, U> T accept(ASTNodeVisitor<T, U> visitor, U context) {
		return visitor.visit(this, context);
	}

	public <T, U> T accept(QLFormVisitor<T, U> visitor, U context) {
		return visitor.visit(this, context);
	}
}
