package sc.ql.ui.widget;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import sc.ql.ast.Statement.Question;
import sc.ql.eval.Environment;
import sc.ql.ui.UIWidgetStyle;
import sc.ql.value.Value;

public class TextFieldWidget extends AbstractUIWidget {

	private final JTextField textField;
	private final JPanel panel;

	private UIWidgetStyle style = new UIWidgetStyle() {

		@Override
		public Font getFont() {
			return UIManager.getDefaults().getFont("JTextField.font");
		}

		@Override
		public int getWidth() {
			return 100;
		}

		@Override
		public int getHeight() {
			return 20;
		}
	};

	public TextFieldWidget(Environment env, Question q, Value defaultValue) {
		super(env, q.name(), defaultValue);

		panel = new JPanel();

		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent e) {
				setValue(getViewValue());
			}
		});
		panel.add(textField);

		setStyle(style);
	}

	@Override
	public UIWidgetStyle getStyle() {
		return style;
	}

	@Override
	public void setStyle(UIWidgetStyle style) {
		textField.setFont(style.getFont());
		textField.setPreferredSize(new Dimension(style.getWidth(), style.getHeight()));
	}

	@Override
	public JComponent getComponent() {
		return panel;
	}

	@Override
	protected Value getViewValue() {
		return getDefaultValue().parse(textField.getText());
	}

	@Override
	protected void setViewValue(Value value) {
		textField.setText(value == null ? "" : value.toString());
	}

	@Override
	public void setVisible(boolean visible) {
		textField.setVisible(visible);
	}

	@Override
	public void setEditable(boolean editable) {
		textField.setEditable(editable);
	}
}