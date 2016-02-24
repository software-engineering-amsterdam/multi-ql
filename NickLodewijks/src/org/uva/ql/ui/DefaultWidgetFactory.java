package org.uva.ql.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
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
import org.uva.ql.ast.QLExpressionInterpreter;
import org.uva.ql.ast.StringType;
import org.uva.ql.ast.VariableType;
import org.uva.ql.ast.expr.Context;
import org.uva.ql.ast.expr.Context.ContextListener;
import org.uva.ql.ast.expr.Expr;
import org.uva.ql.ast.form.ComputedQuestion;
import org.uva.ql.ast.form.Form;
import org.uva.ql.ast.form.InputQuestion;
import org.uva.ql.ast.stat.IFStat;

public class DefaultWidgetFactory implements WidgetFactory {

	@Override
	public QLQuestion create(ComputedQuestion q) {
		QLQuestion question;
		QLComponent label;
		QLWidget widget;

		label = new DefaultLabelWidget(q.getLabel());

		widget = createWidget(q.getId(), q.getType());
		widget = new ComputedWidget(widget, q.getExpr());

		question = new DefaultQuestion(label, widget);

		return question;
	}

	private QLWidget createWidget(String variableName, VariableType type) {

		if (type instanceof BooleanType) {
			return new DefaultBooleanWidget(variableName);
		} else if (type instanceof IntegerType) {
			return new DefaultIntegerWidget(variableName);
		} else if (type instanceof StringType) {
			return new DefaultStringWidget(variableName);
		} else {
			throw new IllegalStateException("Undefined question type '" + type + "'");
		}
	}

	@Override
	public QLQuestion create(InputQuestion q) {
		QLQuestion question;
		QLComponent label;
		QLWidget widget;

		label = new DefaultLabelWidget(q.getLabel());

		widget = createWidget(q.getId(), q.getType());

		question = new DefaultQuestion(label, widget);

		return question;
	}

	@Override
	public QLForm create(Form form) {
		return new DefaultQLForm(form.getName());
	}

	@Override
	public QLSection create(IFStat condition) {
		return new DefaultQLSection(condition.getExpr());
	}

	private static class DefaultQLForm extends JPanel implements QLForm {

		private static final long serialVersionUID = 1L;

		private final String name;

		private final List<QLQuestion> questions = new ArrayList<QLQuestion>();
		private final List<QLSection> sections = new ArrayList<>();

		private final JScrollPane pane;

		public DefaultQLForm(String name) {
			this.name = name;

			setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

			pane = new JScrollPane(this, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
					JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

			setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.red), this.getBorder()));
		}

		@Override
		public String getName() {
			return name;
		}

		@Override
		public void addSection(QLSection section) {
			sections.add(section);

			add(section.getComponent());
			add(Box.createRigidArea(new Dimension(0, 2)));
		}

		@Override
		public void addQuestion(QLQuestion question) {
			JPanel panel;

			panel = new JPanel(new BorderLayout());

			questions.add(question);

			panel.add(question.getLabelComponent(), BorderLayout.CENTER);
			panel.add(question.getInputComponent(), BorderLayout.EAST);
			panel.setMaximumSize(new Dimension(400, 40));
			panel.setMinimumSize(new Dimension(400, 40));

			panel.setBorder(
					BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.red), panel.getBorder()));

			add(panel);
			add(Box.createRigidArea(new Dimension(0, 2)));
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

			setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

			setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.red), this.getBorder()));
		}

		@Override
		public void addQuestion(QLQuestion question) {
			JPanel panel;

			questions.add(question);

			panel = new JPanel(new BorderLayout());

			panel.add(question.getLabelComponent(), BorderLayout.CENTER);
			panel.add(question.getInputComponent(), BorderLayout.EAST);
			panel.setMaximumSize(new Dimension(400, 40));
			panel.setMinimumSize(new Dimension(400, 40));

			panel.setBorder(
					BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.red), panel.getBorder()));

			add(panel);

			add(Box.createRigidArea(new Dimension(0, 2)));
		}

		@Override
		public void addSubSection(QLSection section) {
			subSections.add(section);

			add(section.getComponent());

			add(Box.createRigidArea(new Dimension(0, 2)));
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

			value = QLExpressionInterpreter.interpret(expr, context);

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
			setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
			setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.red), this.getBorder()));
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

	/**
	 * This class wraps a {@link QLWidget} and uses an expression to compute its
	 * value.
	 *
	 */
	private static class ComputedWidget implements QLWidget, ContextListener {

		private final QLWidget widget;
		private final Expr expr;

		public ComputedWidget(QLWidget widget, Expr expr) {
			this.widget = widget;

			widget.setEditable(false);

			this.expr = expr;
		}

		@Override
		public void setContext(Context context) {
			widget.setContext(context);

			context.addContextListener(this);
		}

		@Override
		public void contextChanged(Context context) {
			setValue(QLExpressionInterpreter.interpret(expr, context));
		}

		@Override
		public JComponent getComponent() {
			return widget.getComponent();
		}

		@Override
		public Object getValue() {
			return widget.getValue();
		}

		@Override
		public boolean setValue(Object value) {
			return widget.setValue(value);
		}

		@Override
		public void setEditable(boolean readOnly) {
			assert false : "ComputedWidgets should always be read-only, why was this method called?";
		}
	}

	private static class DefaultBooleanWidget extends JPanel implements QLWidget, ActionListener {

		private static final long serialVersionUID = 1L;
		private final String variableName;
		private final JRadioButton rbYes;
		private final JRadioButton rbNo;

		private Context context;

		public DefaultBooleanWidget(String variableName) {
			ButtonGroup bg;
			setLayout(new BorderLayout());

			this.variableName = variableName;

			rbYes = new JRadioButton("Yes");
			rbNo = new JRadioButton("No");

			bg = new ButtonGroup();
			bg.add(rbYes);
			bg.add(rbNo);

			add(rbYes, BorderLayout.WEST);
			add(rbNo, BorderLayout.EAST);

			setPreferredSize(new Dimension(90, 30));
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
		public Boolean getValue() {
			return rbYes.isSelected();
		}

		@Override
		public boolean setValue(Object value) {
			if (getValue().equals(value)) {
				return false;
			}

			// Calling doClick will trigger actionPerformed
			if ((Boolean) value) {
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

		@Override
		public void setEditable(boolean editable) {
			rbYes.setEnabled(editable);
			rbNo.setEnabled(editable);
		}
	}

	private static class DefaultIntegerWidget extends JPanel implements QLWidget {

		private static final long serialVersionUID = 1L;

		private final String variableName;
		private final JTextField textField;

		public DefaultIntegerWidget(String variableName) {
			this.variableName = variableName;

			textField = new JTextField();
			textField.setPreferredSize(new Dimension(100, 20));

			add(textField);
		}

		@Override
		public JComponent getComponent() {
			return this;
		}

		@Override
		public void setContext(Context context) {

			context.setValue(variableName, 0);

			textField.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					context.setValue(variableName, getValue());
				}
			});

			textField.addKeyListener(new KeyAdapter() {

				@Override
				public void keyReleased(KeyEvent e) {
					textField.postActionEvent();
				}
			});
		}

		@Override
		public Object getValue() {
			try {
				return Integer.parseInt(textField.getText());
			} catch (NumberFormatException ex) {
				return 0;
			}
		}

		@Override
		public boolean setValue(Object value) {
			if (getValue().equals(value)) {
				return false;
			}

			textField.setText(value == null ? "" : value.toString());
			textField.postActionEvent();
			return true;
		}

		@Override
		public void setEditable(boolean editable) {
			textField.setEditable(editable);
		}
	}

	private static class DefaultStringWidget extends JPanel implements QLWidget {

		private static final long serialVersionUID = 1L;

		private final String variableName;
		private final JTextField textField;

		private KeyListener keyListener;

		public DefaultStringWidget(String variableName) {
			this.variableName = variableName;

			textField = new JTextField();
			textField.setPreferredSize(new Dimension(100, 20));

			add(textField);
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
		}

		@Override
		public Object getValue() {
			return textField.getText();
		}

		@Override
		public boolean setValue(Object value) {
			if (getValue().equals(value)) {
				return false;
			}

			textField.setText(value == null ? "" : value.toString());

			return true;
		}

		@Override
		public void setEditable(boolean editable) {
			textField.setEditable(editable);
		}
	}
}