package sc.ql.ui.widget;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.UIManager;

import sc.ql.eval.Environment;
import sc.ql.value.Value;

public class UIRadioButton extends AbstractUIWidget implements ActionListener {

	private final Map<UIWidgetChoice, JRadioButton> choiceToButton;
	private final UIWidgetChoices choices;
	private final JPanel panel;

	private UIWidgetStyle style = new UIWidgetStyle(UIManager.getDefaults().getFont("JRadioButton.font"),
			new Dimension(150, 30), Color.BLACK);

	public UIRadioButton(Environment env, String variableName, UIWidgetChoices choices) {
		super(env, variableName, choices.defaultValue().getValue());
		ButtonGroup bg;

		this.choiceToButton = new LinkedHashMap<>();
		this.choices = choices;

		bg = new ButtonGroup();

		panel = new JPanel();

		for (UIWidgetChoice choice : choices.values()) {
			JRadioButton rb;
			String name;

			name = choice.getName();

			rb = new JRadioButton(name);
			rb.setActionCommand(name);
			rb.addActionListener(this);
			bg.add(rb);

			choiceToButton.put(choice, rb);
			panel.add(rb);
		}

		setViewValue(getDefaultValue());

		setStyle(style);
	}

	@Override
	public UIWidgetStyle getStyle() {
		return style;
	}

	@Override
	public void setStyle(UIWidgetStyle style) {
		for (JRadioButton rb : choiceToButton.values()) {
			rb.setFont(style.getFont());
			rb.setForeground(style.getColor());
		}

		panel.setPreferredSize(new Dimension(style.getWidth(), style.getHeight()));
	}

	@Override
	public JComponent getComponent() {
		return panel;
	}

	private UIWidgetChoice getCurrentChoice() {
		for (UIWidgetChoice choice : choices.values()) {
			if (choiceToButton.get(choice).isSelected()) {
				return choice;
			}
		}

		return null;
	}

	@Override
	public Value getViewValue() {
		return getCurrentChoice().getValue();
	}

	@Override
	public void setViewValue(Value value) {
		UIWidgetChoice choice;

		choice = choices.getByValue(value);
		choiceToButton.get(choice).setSelected(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		setValue(choices.getByName(e.getActionCommand()).getValue());
	}

	@Override
	public void setVisible(boolean visible) {
		panel.setVisible(visible);
	}

	@Override
	public void setEditable(boolean editable) {
		for (JRadioButton rb : choiceToButton.values()) {
			rb.setEnabled(editable);
		}
	}
}