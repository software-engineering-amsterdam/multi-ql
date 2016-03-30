package sc.qls.ui.widget;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.UIManager;

import sc.ql.ast.Statement.Question;
import sc.ql.eval.Environment;
import sc.ql.ui.widget.AbstractUIWidget;
import sc.ql.ui.widget.UIWidgetChoice;
import sc.ql.ui.widget.UIWidgetChoices;
import sc.ql.ui.widget.UIWidgetStyle;
import sc.ql.value.Value;

public class UIDropDown extends AbstractUIWidget {

	private final UIWidgetChoices choices;
	private final Map<String, UIWidgetChoice> itemToChoice;
	private final JComboBox<String> comboBox;
	private final JPanel panel;

	private UIWidgetStyle style = new UIWidgetStyle(UIManager.getDefaults().getFont("JComboBox.font"),
			new Dimension(100, 10), Color.BLACK);

	public UIDropDown(Environment env, Question question, UIWidgetChoices choices) {
		super(env, question.name(), choices.defaultValue().getValue());

		this.choices = choices;

		comboBox = new JComboBox<>();
		comboBox.addActionListener(e -> setValue(getCurrentChoice().getValue()));

		itemToChoice = new HashMap<>();
		for (UIWidgetChoice choice : choices.values()) {
			itemToChoice.put(choice.getName(), choice);
			comboBox.addItem(choice.getName());
		}

		panel = new JPanel(new BorderLayout());
		panel.add(comboBox, BorderLayout.CENTER);
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
		comboBox.setFont(style.getFont());
		comboBox.setForeground(style.getColor());
		comboBox.setPreferredSize(new Dimension(style.getWidth(), style.getHeight()));
	}

	@Override
	public JComponent getComponent() {
		return panel;
	}

	private UIWidgetChoice getCurrentChoice() {
		return itemToChoice.get(comboBox.getSelectedItem());
	}

	@Override
	public Value getViewValue() {
		return getCurrentChoice().getValue();
	}

	@Override
	public void setViewValue(Value value) {
		UIWidgetChoice choice;

		choice = choices.getByValue(value);
		comboBox.setSelectedIndex(choices.indexOf(choice));
	}

	@Override
	public void setVisible(boolean visible) {
		panel.setVisible(visible);
	}

	@Override
	public void setEditable(boolean editable) {
		comboBox.setEditable(editable);
	}
}