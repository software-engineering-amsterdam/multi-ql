package org.uva.ql.ui;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.swing.BoxLayout;
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
import org.uva.ql.ast.form.Form;
import org.uva.ql.ast.form.InputQuestion;
import org.uva.ql.ast.form.Question;

public class DefaultWidgetFactory implements WidgetFactory {

	@Override
	public QLQuestion create(ComputedQuestion q) {
		return createQuestion(q, q.getExpression());
	}

	@Override
	public QLQuestion create(InputQuestion q) {
		return createQuestion(q, null);
	}

	@Override
	public QLForm create(Form form) {
		return new DefaultQLForm(form.getName());
	}

	private QLQuestion createQuestion(Question q, Expr expr) {
		QLQuestion question;
		QLWidget label;
		QLWidget widget;
		String variableName;

		variableName = q.getVariableId().getName();

		label = new DefaultLabelWidget(q.getLabel());
		switch (q.getVariableId().getType()) {
		case BOOLEAN:
			widget = new DefaultBooleanWidget(variableName, expr);
			break;
		case INTEGER:
			widget = new DefaultIntegerWidget(variableName, expr);
			break;
		case STRING:
			widget = new DefaultStringWidget(variableName, expr);
			break;
		default:
			widget = null;
			break;
		}

		question = new DefaultQuestion(label, widget);

		return question;
	}

	private static class DefaultQLForm extends JPanel implements QLForm {
		private static final long serialVersionUID = 1L;

		private final String name;

		private List<QLQuestion> questions = new ArrayList<QLQuestion>();
		private Map<Expr, List<QLQuestion>> conditionalQuestionsMap = new HashMap<>();
		private Map<Expr, JPanel> conditionalQuestionPanel = new HashMap<>();

		public DefaultQLForm(String name) {
			this.name = name;

			setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		}

		@Override
		public void addQuestion(QLQuestion question) {
			addQuestion(question, null);
		}

		@Override
		public void addQuestion(QLQuestion question, Expr condition) {
			JPanel panel;

			panel = new JPanel();
			panel.setSize(100, 40);

			questions.add(question);

			panel.add(question.getLabelComponent());
			panel.add(question.getInputComponent());

			if (condition != null) {
				List<QLQuestion> conditionalQuestions;
				JPanel conditionPanel;

				conditionalQuestions = conditionalQuestionsMap.get(condition);
				if (conditionalQuestions == null) {
					conditionalQuestions = new ArrayList<>();
					conditionalQuestionsMap.put(condition, conditionalQuestions);
				}

				conditionPanel = conditionalQuestionPanel.get(condition);
				if (conditionPanel == null) {
					conditionPanel = new JPanel();
					conditionPanel.setLayout(new BoxLayout(conditionPanel, BoxLayout.PAGE_AXIS));
					conditionPanel.setVisible(false);
					conditionalQuestionPanel.put(condition, conditionPanel);

					add(conditionPanel);
				}

				conditionPanel.add(panel);
			} else {
				add(panel);
			}
		}

		@Override
		public void setContext(Context context) {
			for (QLQuestion q : questions) {
				q.setContext(context);
			}

			for (Map.Entry<Expr, JPanel> entry : conditionalQuestionPanel.entrySet()) {
				context.addContextListener(new ContextListener() {

					@Override
					public void contextChanged(Context context) {
						JPanel panell;

						panell = entry.getValue();

						panell.setVisible((Boolean) entry.getKey().interpret(context));
						panell.repaint();
						panell.revalidate();
					}
				});
			}
		}

		@Override
		public JComponent getComponent() {
			return this;
		}

	}

	private static class DefaultQuestion implements QLQuestion {

		private final QLWidget label;
		private final QLWidget input;

		public DefaultQuestion(QLWidget label, QLWidget input) {
			this.label = label;
			this.input = input;
		}

		public void setContext(Context context) {
			label.setContext(context);
			input.setContext(context);
		}

		public JComponent getLabelComponent() {
			return label.getComponent();
		}

		public JComponent getInputComponent() {
			return input.getComponent();
		}
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

		public DefaultIntegerWidget(String variableName, Expr valueExpr) {
			this.valueExpr = valueExpr;
			this.variableName = variableName;

			textField = new JTextField();
			textField.setPreferredSize(new Dimension(100, 20));

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

		public DefaultStringWidget(String variableName, Expr valueExpr) {
			this.valueExpr = valueExpr;
			this.variableName = variableName;

			textField = new JTextField();
			textField.setPreferredSize(new Dimension(100, 20));

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