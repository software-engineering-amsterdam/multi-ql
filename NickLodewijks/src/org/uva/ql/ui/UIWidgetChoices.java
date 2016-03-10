package org.uva.ql.ui;

import java.util.Collections;
import java.util.List;

import org.uva.ql.ast.value.Value;

public class UIWidgetChoices {

	private final List<UIWidgetChoice> choices;
	private final UIWidgetChoice defaultChoice;

	public UIWidgetChoices(List<UIWidgetChoice> choices, UIWidgetChoice defaultValue) {
		this.choices = choices;
		this.defaultChoice = defaultValue;
	}

	public List<UIWidgetChoice> getChoices() {
		return Collections.unmodifiableList(choices);
	}

	public UIWidgetChoice getDefaultChoice() {
		return defaultChoice;
	}

	public UIWidgetChoice getByName(String name) {
		for (UIWidgetChoice choice : choices) {
			if (choice.getName().equals(name)) {
				return choice;
			}
		}

		return null;
	}

	public UIWidgetChoice getByValue(Value value) {
		for (UIWidgetChoice choice : choices) {
			if (choice.getValue().equals(value)) {
				return choice;
			}
		}

		return null;
	}
}