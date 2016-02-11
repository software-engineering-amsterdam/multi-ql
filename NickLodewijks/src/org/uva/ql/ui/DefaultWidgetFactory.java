package org.uva.ql.ui;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Objects;

import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.uva.ql.ast.expr.Context;
import org.uva.ql.ast.expr.Context.ContextListener;
import org.uva.ql.ast.expr.Expr;
import org.uva.ql.ast.form.ComputedQuestion;
import org.uva.ql.ast.form.InputQuestion;

public class DefaultWidgetFactory implements WidgetFactory {

	@Override
	public QLQuestion create(ComputedQuestion q) {
		QLQuestion question;
		QLWidget label;
		QLWidget widget;
		String variableName;

		variableName = q.getVariableId().getName();

		label = new DefaultLabelWidget(q.getLabel());
		switch (q.getVariableId().getType()) {
		case BOOLEAN:
			widget = new DefaultBooleanWidget(variableName, q.getExpression());
			break;
		case INTEGER:
			widget = new DefaultIntegerWidget(variableName, q.getExpression());
			break;
		case STRING:
			widget = new DefaultStringWidget(variableName, q.getExpression());
			break;
		default:
			widget = null;
			break;
		}

		question = new QLQuestion(label, widget);

		return question;
	}

	@Override
	public QLQuestion create(InputQuestion q) {
		QLQuestion question;
		QLWidget label;
		QLWidget widget;
		String variableName;

		variableName = q.getVariableId().getName();

		label = new DefaultLabelWidget(q.getLabel());
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

		question = new QLQuestion(label, widget);

		return question;
	}

	private static class DefaultLabelWidget extends JLabel implements QLWidget {
		private static final long serialVersionUID = 1L;

		public DefaultLabelWidget(String label) {
			super(label);
		}

		@Override
		public void setContext(Context context) {
			// DefaultLabelWidget does not use the context
		}

		@Override
		public JComponent getComponent() {
			return this;
		}

	}

	private static class DefaultBooleanWidget extends JPanel implements QLWidget {
		private static final long serialVersionUID = 1L;
		
		private final String variableName;
		private final JRadioButton rbYes;
		private final JRadioButton rbNo;

		private final Expr valueExpr;

		private ContextListener contextListener;
		private ActionListener actionListener;

		public DefaultBooleanWidget(String variableName) {
			this(variableName, null);
		}

		public DefaultBooleanWidget(String variableName, Expr valueExpr) {
			ButtonGroup bg;

			this.variableName = variableName;
			this.valueExpr = valueExpr;

			rbYes = new JRadioButton("Yes");
			rbNo = new JRadioButton("No");

			bg = new ButtonGroup();
			bg.add(rbYes);
			bg.add(rbNo);

			add(rbYes);
			add(rbNo);

			if (valueExpr != null) {
				contextListener = new ContextListener() {
					@Override
					public void contextChanged(Context context) {
						Boolean value;

						value = (Boolean) valueExpr.interpret(context);

						if (rbNo.isSelected() != value) {
							rbNo.setSelected(value);
							context.setValue(variableName, false);
						}
					}
				};
			}
		}

		@Override
		public JComponent getComponent() {
			return this;
		}

		@Override
		public void setContext(Context context) {

			if (actionListener != null) {
				rbNo.removeActionListener(actionListener);
				rbYes.removeActionListener(actionListener);
			}

			actionListener = actionEvent -> context.setValue(variableName, rbYes.isSelected());
			rbYes.addActionListener(actionListener);
			rbNo.addActionListener(actionListener);

			if (valueExpr != null) {
				context.addContextListener(contextListener);
			}
		}
	}

	private static class DefaultIntegerWidget extends JPanel implements QLWidget {
		private static final long serialVersionUID = 1L;
		
		private final String variableName;
		private final JTextField textField;

		private final Expr valueExpr;

		private KeyListener keyListener;
		private ContextListener contextListener;

		public DefaultIntegerWidget(String variableName) {
			this(variableName, null);
		}

		public DefaultIntegerWidget(String variableName, Expr valueExpr) {
			this.valueExpr = valueExpr;
			this.variableName = variableName;

			textField = new JTextField();
			textField.setPreferredSize(new Dimension(100, 50));

			add(textField);

			if (valueExpr != null) {
				contextListener = new ContextListener() {
					@Override
					public void contextChanged(Context context) {
						Integer value;

						value = (Integer) valueExpr.interpret(context);

						if (!Objects.equals(textField.getText(), value.toString())) {
							textField.setText(value.toString());
							context.setValue(variableName, value);
						}
					}
				};
			}
		}

		@Override
		public JComponent getComponent() {
			return this;
		}

		@Override
		public void setContext(Context context) {

			if (keyListener != null) {
				textField.removeKeyListener(keyListener);
			}

			keyListener = new KeyAdapter() {
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
			};

			textField.addKeyListener(keyListener);

			if (valueExpr != null) {
				context.addContextListener(contextListener);
			}
		}
	}

	private static class DefaultStringWidget extends JPanel implements QLWidget {
		private static final long serialVersionUID = 1L;
		
		private final String variableName;
		private final JTextField textField;
		private final Expr valueExpr;

		private KeyListener keyListener;
		private ContextListener contextListener;

		public DefaultStringWidget(String variableName) {
			this(variableName, null);
		}

		public DefaultStringWidget(String variableName, Expr valueExpr) {
			this.valueExpr = valueExpr;
			this.variableName = variableName;

			textField = new JTextField();
			textField.setPreferredSize(new Dimension(100, 50));

			add(textField);

			if (valueExpr != null) {
				contextListener = new ContextListener() {
					@Override
					public void contextChanged(Context context) {
						String value;

						value = (String) valueExpr.interpret(context);

						if (!Objects.equals(textField.getText(), value)) {
							textField.setText(value.toString());
							context.setValue(variableName, value);
						}
					}
				};
			}
		}

		@Override
		public JComponent getComponent() {
			return this;
		}

		@Override
		public void setContext(Context context) {

			if (keyListener != null) {
				textField.removeKeyListener(keyListener);
			}

			keyListener = new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					context.setValue(variableName, textField.getText());
				}
			};

			textField.addKeyListener(keyListener);

			if (valueExpr != null) {
				context.addContextListener(contextListener);
			}
		}
	}
}