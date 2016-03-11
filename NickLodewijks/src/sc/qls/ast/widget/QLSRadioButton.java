package sc.qls.ast.widget;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import sc.ql.ast.value.BooleanValue;
import sc.ql.ui.UIWidgetChoice;
import sc.ql.ui.UIWidgetChoices;

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
