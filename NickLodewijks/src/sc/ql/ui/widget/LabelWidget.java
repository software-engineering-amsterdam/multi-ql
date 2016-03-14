package sc.ql.ui.widget;

import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.UIManager;

import sc.ql.ast.value.StringValue;
import sc.ql.ast.value.Value;
import sc.ql.ui.UIWidget;
import sc.ql.ui.UIWidgetStyle;

public class LabelWidget implements UIWidget {

	private final JLabel label;

	private UIWidgetStyle style = new UIWidgetStyle() {

		@Override
		public int getHeight() {
			return 20;
		}

		@Override
		public int getWidth() {
			return 150;
		}

		@Override
		public Font getFont() {
			return UIManager.getDefaults().getFont("JLabel.font");
		}
	};

	public LabelWidget(String text) {
		label = new JLabel(text);
		label.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

		setStyle(style);
	}

	@Override
	public UIWidgetStyle getStyle() {
		return style;
	}

	@Override
	public void setStyle(UIWidgetStyle style) {
		label.setFont(style.getFont());
		label.setPreferredSize(new Dimension(style.getWidth(), style.getHeight()));
	}

	@Override
	public JComponent getComponent() {
		return label;
	}

	@Override
	public StringValue getValue() {
		return new StringValue(label.getText());
	}

	@Override
	public void setValue(Value value) {
		label.setText(value.toString());
	}

	@Override
	public void setVisible(boolean visible) {
		label.setVisible(visible);
	}

	@Override
	public void setEditable(boolean editable) {
		// NOOP
	}
}