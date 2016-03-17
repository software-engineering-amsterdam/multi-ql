package sc.qls.ast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import sc.ql.ast.ASTNode;
import sc.ql.ui.UIWidgetChoice;
import sc.ql.ui.UIWidgetChoices;
import sc.ql.value.BooleanValue;

public abstract class Widget extends ASTNode {

	public abstract <T, U> T accept(WidgetVisitor<T, U> visitor, U context);

	public static class TextField extends Widget {

		public TextField() {
		}

		@Override
		public <T, U> T accept(WidgetVisitor<T, U> visitor, U context) {
			return visitor.visit(this, context);
		}
	}

	public static class Spinbox extends Widget {

		public Spinbox() {

		}

		@Override
		public <T, U> T accept(WidgetVisitor<T, U> visitor, U context) {
			return visitor.visit(this, context);
		}
	}

	public static class Slider extends Widget {

		public Slider() {

		}

		@Override
		public <T, U> T accept(WidgetVisitor<T, U> visitor, U context) {
			return visitor.visit(this, context);
		}
	}

	public static class RadioButton extends Widget {

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

	public static class DropDown extends Widget {

		private final List<String> options;
		private final String defaultOption;

		public DropDown(List<String> options, String defaultOption) {
			this.options = options;
			this.defaultOption = defaultOption;
		}

		public List<String> getOptions() {
			return Collections.unmodifiableList(options);
		}

		@Override
		public <T, U> T accept(WidgetVisitor<T, U> visitor, U context) {
			return visitor.visit(this, context);
		}
	}

	public static class CheckBox extends Widget {

		private final List<String> options;
		private final String defaultOption;

		public CheckBox(List<String> options, String defaultOption) {
			this.options = options;
			this.defaultOption = defaultOption;
		}

		public List<String> getOptions() {
			return Collections.unmodifiableList(options);
		}

		@Override
		public <T, U> T accept(WidgetVisitor<T, U> visitor, U context) {
			return visitor.visit(this, context);
		}
	}

}
