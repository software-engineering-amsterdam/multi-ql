package sc.ql.ui.widget;

import java.awt.Dimension;
import java.awt.Font;
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
import sc.ql.ui.UIWidgetChoice;
import sc.ql.ui.UIWidgetChoices;
import sc.ql.ui.UIWidgetStyle;
import sc.ql.value.Value;

public class RadioButtonWidget extends AbstractUIWidget implements ActionListener {

	private final Map<UIWidgetChoice, JRadioButton> choiceToButton;
	private final UIWidgetChoices choices;
	private final JPanel panel;

	private UIWidgetStyle style = new UIWidgetStyle() {

		@Override
		public Font getFont() {
			return UIManager.getDefaults().getFont("JRadioButton.font");
		}

		@Override
		public int getWidth() {
			return 150;
		}

		@Override
		public int getHeight() {
			return 30;
		}
	};

	public RadioButtonWidget(Environment env, String variableName, UIWidgetChoices choices) {
		super(env, variableName, choices.getDefaultChoice().getValue());
		ButtonGroup bg;

		this.choiceToButton = new LinkedHashMap<>();
		this.choices = choices;

		bg = new ButtonGroup();

		panel = new JPanel();

		for (UIWidgetChoice choice : choices.getChoices()) {
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
		}

		panel.setPreferredSize(new Dimension(style.getWidth(), style.getHeight()));
	}

	@Override
	public JComponent getComponent() {
		return panel;
	}

	private UIWidgetChoice getCurrentChoice() {
		for (UIWidgetChoice choice : choices.getChoices()) {
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