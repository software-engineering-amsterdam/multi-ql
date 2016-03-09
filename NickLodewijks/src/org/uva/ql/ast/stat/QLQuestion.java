package org.uva.ql.ast.stat;

import org.uva.ql.ast.ASTSourceInfo;
import org.uva.ql.ast.type.QLType;

public abstract class QLQuestion extends QLStatement {

	private final String id;
	private final QLType type;
	private final String label;

	public QLQuestion(ASTSourceInfo context, QLType type, String id, String label) {
		super(context);
		this.id = id;
		this.type = type;
		this.label = label.replace("\"", "");
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
}
