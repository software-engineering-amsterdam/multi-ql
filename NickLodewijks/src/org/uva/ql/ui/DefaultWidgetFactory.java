package org.uva.ql.ui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.uva.ql.ast.expr.Context;
import org.uva.ql.ast.form.ComputedQuestion;
import org.uva.ql.ast.form.InputQuestion;

public class DefaultWidgetFactory implements WidgetFactory {

	@Override
	public JComponent create(ComputedQuestion q) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JComponent create(InputQuestion q) {
		JPanel panel;
		JLabel label;
		JComponent widget;
		String variableName;

		panel = new JPanel();

		variableName = q.getVariableId().getName();

		label = new JLabel(q.getLabel());
		panel.add(label);
		switch (q.getVariableId().getType()) {
		case BOOLEAN:
			widget = new DefaultBooleanWidget(variableName);
			break;
		case INTEGER:
			widget = new DefaultIntegerWidget(variableName);
			break;
		case STRING:
			widget = new DefaultStringWidget(variableName);
			break;
		default:
			widget = null;
			break;
		}

		panel.add(widget);

		return panel;
	}

	public static interface QLWidget {
		public void setContext(Context context);
	}

	private static class DefaultBooleanWidget extends JPanel implements QLWidget {
		private final String variableName;
		private final JRadioButton rbYes;
		private final JRadioButton rbNo;

		public DefaultBooleanWidget(String variableName) {
			ButtonGroup bg;

			this.variableName = variableName;

			rbYes = new JRadioButton("Yes");
			rbNo = new JRadioButton("No");

			bg = new ButtonGroup();
			bg.add(rbYes);
			bg.add(rbNo);

			add(rbYes);
			add(rbNo);
		}

		@Override
		public void setContext(Context context) {
			rbYes.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					context.setValue(variableName, true);
				}
			});

			rbNo.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					context.setValue(variableName, false);
				}
			});
		}
	}

	private static class DefaultIntegerWidget extends JPanel implements QLWidget {
		private final String variableName;
		private final JTextField textField;

		public DefaultIntegerWidget(String variableName) {
			ButtonGroup bg;

			this.variableName = variableName;

			textField = new JTextField();
			textField.setPreferredSize(new Dimension(100, 50));

			add(textField);
		}

		@Override
		public void setContext(Context context) {
			textField.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					Integer value;

					try {
						value = Integer.parseInt(textField.getText());
					} catch (NumberFormatException ex) {
						value = 0;
					}

					context.setValue(variableName, value);
				}
			});
		}
	}

	private static class DefaultStringWidget extends JPanel implements QLWidget {
		private final String variableName;
		private final JTextField textField;

		public DefaultStringWidget(String variableName) {
			ButtonGroup bg;

			this.variableName = variableName;

			textField = new JTextField();
			textField.setPreferredSize(new Dimension(100, 50));

			add(textField);
		}

		@Override
		public void setContext(Context context) {
			textField.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					context.setValue(variableName, textField.getText());
				}
			});
		}
	}
}