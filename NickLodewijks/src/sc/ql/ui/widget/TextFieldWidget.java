package sc.ql.ui.widget;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import sc.ql.Environment;
import sc.ql.ast.Statement.Question;
import sc.ql.ast.value.Value;
import sc.ql.ui.UIWidgetStyle;

public class TextFieldWidget extends AbstractWidgetImpl {

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

	public TextFieldWidget(Environment context, Question q, Value defaultValue) {
		super(context, q.getId(), defaultValue);

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