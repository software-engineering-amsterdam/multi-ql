package org.uva.qls.ast.widget;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.uva.ql.ast.value.BooleanValue;
import org.uva.ql.ui.UIWidgetChoice;
import org.uva.ql.ui.UIWidgetChoices;

public class QLSRadioButton extends QLSWidgetType {

	private final List<String> options;
	private final String defaultOption;

	public QLSRadioButton(List<String> options, String defaultOption) {
		this.options = options;
		this.defaultOption = defaultOption;
	}

	public List<String> getOptions() {
		return Collections.unmodifiableList(options);
	}

	public UIWidgetChoices getChoices() {
		List<UIWidgetChoice> choices;
		UIWidgetChoice defaultChoice;

		choices = new ArrayList<>();

		for (String option : options) {
			choices.add(new UIWidgetChoice(option, new BooleanValue(option)));
		}

		defaultChoice = new UIWidgetChoice(defaultOption, new BooleanValue(defaultOption));

		return new UIWidgetChoices(choices, defaultChoice);
	}

}
