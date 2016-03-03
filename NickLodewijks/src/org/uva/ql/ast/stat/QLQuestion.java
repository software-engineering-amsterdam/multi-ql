package org.uva.ql.ast.stat;

import org.antlr.v4.runtime.ParserRuleContext;
import org.uva.ql.ast.ASTNode;
import org.uva.ql.ast.type.QLType;

public abstract class QLQuestion extends ASTNode {

	private final String id;
	private final QLType type;
	private final String label;

	public QLQuestion(ParserRuleContext context, QLType type, String id, String label) {
		super(context);
		this.id = id;
		this.type = type;
		this.label = label;
	}

	public String getId() {
		return id;
	}

	public QLType getType() {
		return type;
	}

	public String getLabel() {
		return label;
	}

	public abstract <T, U> T accept(QLQuestionVisitor<T, U> visitor, U context);
}
