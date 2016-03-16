package sc.qls.ast.widget;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import sc.ql.ui.UIWidgetChoice;
import sc.ql.ui.UIWidgetChoices;
import sc.ql.value.BooleanValue;

public class RadioButton extends Widget {

	private final List<String> options;
	private final String defaultOption;

	public RadioButton(List<String> options, String defaultOption) {
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

	@Override
	public <T, U> T accept(WidgetVisitor<T, U> visitor, U context) {
		return visitor.visit(this, context);
	}
}
