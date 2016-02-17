package org.uva.ql.ast.form;

import org.antlr.v4.runtime.ParserRuleContext;
import org.uva.ql.ast.ASTNode;
import org.uva.ql.ast.VariableType;

public abstract class Question extends ASTNode {

	private final String id;
	private final VariableType type;
	private final String label;

	public Question(ParserRuleContext context, VariableType type, String id, String label) {
		super(context);
		this.id = id;
		this.type = type;
		this.label = label;
	}

	public String getId() {
		return id;
	}

	public VariableType getType() {
		return type;
	}

	public String getLabel() {
		return label;
	}
}
