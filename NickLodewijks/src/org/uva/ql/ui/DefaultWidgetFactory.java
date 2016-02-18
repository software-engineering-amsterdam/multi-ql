package org.uva.ql.ui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import org.uva.ql.ast.BooleanType;
import org.uva.ql.ast.IntegerType;
import org.uva.ql.ast.StringType;
import org.uva.ql.ast.VariableType;
import org.uva.ql.ast.expr.Context;
import org.uva.ql.ast.expr.Context.ContextListener;
import org.uva.ql.ast.expr.Expr;
import org.uva.ql.ast.form.ComputedQuestion;
import org.uva.ql.ast.form.Form;
import org.uva.ql.ast.form.InputQuestion;
import org.uva.ql.ast.form.Question;
import org.uva.ql.ast.stat.IFStat;

public class DefaultWidgetFactory implements WidgetFactory {

	@Override
	public QLQuestion create(ComputedQuestion q) {
		QLQuestion question;
		QLComponent label;
		QLComponent widget;
		String id;
		VariableType type;

		id = q.getId();
		type = q.getType();

		label = new DefaultLabelWidget(q.getLabel());

		if (type instanceof BooleanType) {
			widget = new DefaultComputedBooleanWidget(id, q.getExpr());
		} else if (type instanceof IntegerType) {
			widget = new DefaultIntegerWidget(id, q.getExpr());
		} else if (type instanceof StringType) {
			widget = new DefaultStringWidget(id, q.getExpr());
		} else {
			throw new IllegalStateException("Undefined question type '" + type + "'");
		}

		question = new DefaultQuestion(label, widget);

		return question;
	}

	@Override
	public QLQuestion create(InputQuestion q) {
		return createQuestion(q, null);
	}

	@Override
	public QLForm create(Form form) {
		return new DefaultQLForm(form.getName());
	}

	@Override
	public QLSection create(IFStat condition) {
		return new DefaultQLSection(condition.getExpr());
	}

	private QLQuestion createQuestion(Question q, Expr expr) {
		QLQuestion question;
		QLComponent label;
		QLComponent widget;
		String id;
		VariableType type;

		id = q.getId();
		type = q.getType();

		label = new DefaultLabelWidget(q.getLabel());

		if (type instanceof BooleanType) {
			widget = new DefaultBooleanWidget(id);
		} else if (type instanceof IntegerType) {
			widget = new DefaultIntegerWidget(id, expr);
		} else if (type instanceof StringType) {
			widget = new DefaultStringWidget(id, expr);
		} else {
			throw new IllegalStateException("Undefined question type '" + type + "'");
		}

		question = new DefaultQuestion(label, widget);

		return question;
	}

	private static class DefaultQLForm extends JPanel implements QLForm {

		private static final long serialVersionUID = 1L;

		private final String name;

		private final List<QLQuestion> questions = new ArrayList<QLQuestion>();
		private final List<QLSection> sections = new ArrayList<>();

		private final JScrollPane pane;

		public DefaultQLForm(String name) {
			this.name = name;

			setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

			setSize(250, 200);
			pane = new JScrollPane(this, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
					JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		}

		@Override
		public String getName() {
			return name;
		}

		@Override
		public void addSection(QLSection section) {
			sections.add(section);

			add(section.getComponent());
		}

		@Override
		public void addQuestion(QLQuestion question) {
			JPanel panel;

			panel = new JPanel();
			panel.setSize(100, 40);

			questions.add(question);

			panel.add(question.getLabelComponent());
			panel.add(question.getInputComponent());

			add(panel);
		}

		@Override
		public void setContext(Context context) {
			questions.stream().forEach(q -> q.setContext(context));
			sections.stream().forEach(s -> s.setContext(context));
		}

		@Override
		public JComponent getComponent() {
			return pane;
		}
	}

	private static class DefaultQLSection extends JPanel implements QLSection, ContextListener {

		private static final long serialVersionUID = 1L;

		private final Expr expr;
		private final List<QLQuestion> questions = new ArrayList<>();
		private final List<QLSection> subSections = new ArrayList<>();

		public DefaultQLSection(Expr expr) {
			this.expr = expr;

			setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
			setVisible(false);
		}

		@Override
		public void addQuestion(QLQuestion question) {
			JPanel panel;

			panel = new JPanel();
			panel.setSize(100, 40);

			questions.add(question);

			panel.add(question.getLabelComponent());
			panel.add(question.getInputComponent());

			add(panel);
		}

		@Override
		public void addSubSection(QLSection section) {
			subSections.add(section);

			add(section.getComponent());
		}

		@Override
		public void setContext(Context context) {

			context.addContextListener(this);

			questions.stream().forEach(q -> q.setContext(context));
			subSections.stream().forEach(s -> s.setContext(context));
		}

		@Override
		public JComponent getComponent() {
			return this;
		}

		@Override
		public void contextChanged(Context context) {
			boolean value;

			value = (Boolean) expr.interpret(context);

			SwingUtilities.invokeLater(() -> {
				setVisible(value);
			});
		}
	}

	private static class DefaultQuestion implements QLQuestion {

		private final QLComponent label;
		private final QLComponent input;

		public DefaultQuestion(QLComponent label, QLComponent input) {
			this.label = label;
			this.input = input;
		}

		@Override
		public void setContext(Context context) {
			label.setContext(context);
			input.setContext(context);
		}

		@Override
		public JComponent getLabelComponent() {
			return label.getComponent();
		}

		@Override
		public JComponent getInputComponent() {
			return input.getComponent();
		}
	}

	private static class DefaultLabelWidget extends JLabel implements QLComponent {

		private static final long serialVersionUID = 1L;

		public DefaultLabelWidget(String label) {
			super(label.replaceAll("\"", ""));
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

	private static class DefaultComputedBooleanWidget extends DefaultBooleanWidget implements ContextListener {

		private static final long serialVersionUID = 1L;

		private final Expr valueExpr;

		public DefaultComputedBooleanWidget(String variableName, Expr valueExpr) {
			super(variableName);

			this.valueExpr = valueExpr;
		}

		@Override
		public void setContext(Context context) {
			super.setContext(context);

			context.addContextListener(this);
		}

		@Override
		public void contextChanged(Context context) {
			setValue((Boolean) valueExpr.interpret(context));
		}
	}

	private static class DefaultBooleanWidget extends JPanel implements QLBooleanWidget, ActionListener {

		private static final long serialVersionUID = 1L;
		private final String variableName;
		private final JRadioButton rbYes;
		private final JRadioButton rbNo;

		private Context context;

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
		public JComponent getComponent() {
			return this;
		}

		@Override
		public void setContext(Context context) {

			this.context = context;

			context.setValue(variableName, Boolean.FALSE);

			rbYes.addActionListener(this);
			rbNo.addActionListener(this);
		}

		@Override
		public boolean getValue() {
			return rbYes.isSelected();
		}

		@Override
		public boolean setValue(boolean value) {
			if (getValue() == value) {
				return false;
			}

			// Call doClick to set the value, this will trigger an
			// actionPerformed.
			if (value) {
				rbYes.doClick();
			} else {
				rbNo.doClick();
			}

			return true;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			context.setValue(variableName, rbYes.isSelected());
		}
	}

	private static class DefaultIntegerWidget extends JPanel implements QLComponent {

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

			context.setValue(variableName, 0);

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

	private static class DefaultStringWidget extends JPanel implements QLComponent {

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

			context.setValue(variableName, "");

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