package org.uva.ql.ast.form;

import java.util.List;

import org.antlr.v4.runtime.ParserRuleContext;
import org.uva.ql.ast.ASTNode;
import org.uva.ql.ast.stat.QLQuestion;

public class QLForm extends ASTNode {

	private final String name;
	private final QLBlock body;

	public QLForm(ParserRuleContext context, String id, QLBlock body) {
		super(context);
		this.name = id;
		this.body = body;
	}

	public List<QLQuestion> getQuestions() {
		return body.getQuestions();
	}

	public QLBlock getBody() {
		return body;
	}

	public String getName() {
		return name;
	}

	public <T, U> T accept(QLFormVisitor<T, U> visitor, U context) {
		return visitor.visit(this, context);
	}
}
