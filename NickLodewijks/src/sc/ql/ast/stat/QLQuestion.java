package sc.ql.ast.stat;

import sc.ql.ast.type.QLType;

public abstract class QLQuestion extends QLStatement {

	private final String id;
	private final QLType type;
	private final String label;

	public QLQuestion(QLType type, String id, String label) {
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
}
