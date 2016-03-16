package sc.ql.ui;

import sc.ql.value.Value;

public class UIWidgetChoice {

	private final String name;
	private final Value value;

	public UIWidgetChoice(String name, Value value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public Value getValue() {
		return value;
	}

	@Override
	public boolean equals(Object obj) {
		UIWidgetChoice other;

		if (!(obj instanceof UIWidgetChoice)) {
			return false;
		}

		other = (UIWidgetChoice) obj;

		return name.equals(other.name) && value.equals(other.value);
	}

	@Override
	public int hashCode() {
		return name.hashCode() + value.hashCode();
	}

	@Override
	public String toString() {
		return "UIWidgetChoice[" + name + ", " + value + "]";
	}
}
