package sc.qls.ui.widget;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;
import javax.swing.UIManager;

import sc.ql.ast.Statement.Question;
import sc.ql.eval.Environment;
import sc.ql.ui.widget.AbstractUIWidget;
import sc.ql.ui.widget.UIWidgetChoice;
import sc.ql.ui.widget.UIWidgetChoices;
import sc.ql.ui.widget.UIWidgetStyle;
import sc.ql.value.Value;

public class UISpinner extends AbstractUIWidget {

	private final UIWidgetChoices choices;
	private final Map<String, UIWidgetChoice> itemToChoice;
	private final JSpinner spinner;
	private final JPanel panel;

	private UIWidgetStyle style = new UIWidgetStyle(UIManager.getDefaults().getFont("JSpinner.font"),
			new Dimension(100, 10), Color.BLACK);

	public UISpinner(Environment env, Question question, UIWidgetChoices choices) {
		super(env, question.name(), choices.defaultValue().getValue());

		this.choices = choices;

		itemToChoice = new HashMap<>();
		for (UIWidgetChoice choice : choices.values()) {
			itemToChoice.put(choice.getName(), choice);
		}

		spinner = new JSpinner(new SpinnerListModel(itemToChoice.keySet().toArray()));
		spinner.addChangeListener(e -> {
			setValue(getCurrentChoice().getValue());
		});

		panel = new JPanel(new BorderLayout());
		panel.add(spinner, BorderLayout.CENTER);
		panel.setPreferredSize(new Dimension(150, 30));

		setViewValue(getDefaultValue());

		setStyle(style);
	}

	@Override
	public UIWidgetStyle getStyle() {
		return style;
	}

	@Override
	public void setStyle(UIWidgetStyle style) {
		spinner.setFont(style.getFont());
		spinner.setForeground(style.getColor());
		spinner.setPreferredSize(new Dimension(style.getWidth(), style.getHeight()));
	}

	@Override
	public JComponent getComponent() {
		return panel;
	}

	private UIWidgetChoice getCurrentChoice() {
		return itemToChoice.get(spinner.getValue());
	}

	@Override
	public Value getViewValue() {
		return getCurrentChoice().getValue();
	}

	@Override
	public void setViewValue(Value value) {
		spinner.setValue(choices.getByValue(value).getName());
	}

	@Override
	public void setVisible(boolean visible) {
		panel.setVisible(visible);
	}

	@Override
	public void setEditable(boolean editable) {
		spinner.setEnabled(editable);
	}
}