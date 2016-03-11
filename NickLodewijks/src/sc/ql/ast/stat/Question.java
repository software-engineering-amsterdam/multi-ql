package sc.ql.ast.stat;

import sc.ql.ast.type.ValueType;

public abstract class Question extends Statement {

	private final String id;
	private final ValueType type;
	private final String label;

	public Question(ValueType type, String id, String label) {
		this.id = id;
		this.type = type;
		this.label = label;
	}

	public String getId() {
		return id;
	}

	public ValueType getType() {
		return type;
	}

	public String getLabel() {
		return label;
	}
}
