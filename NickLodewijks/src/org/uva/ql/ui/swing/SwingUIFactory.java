package org.uva.ql.ui.swing;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JFrame;
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
import org.uva.ql.domain.QLForm;
import org.uva.ql.domain.QLQuestion;
import org.uva.ql.domain.QLQuestionaire;
import org.uva.ql.domain.QLSection;
import org.uva.ql.ui.UIFactory;
import org.uva.ql.ui.UIQuestionnaire;

public class SwingUIFactory implements UIFactory {

	@Override
	public UIQuestionnaire create(QLQuestionaire questionnaire) {
		return new DefaultQLQuestionaire(questionnaire);
	}

	private static QLSwingComponent createWidget(String variableName, VariableType type) {

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

	public static class DefaultQLQuestionaire implements UIQuestionnaire {

		private final QLQuestionaire questionnaire;
		private final List<DefaultQLForm> forms = new ArrayList<>();

		private final JFrame jframe;

		public DefaultQLQuestionaire(QLQuestionaire q) {
			JScrollPane scrollPanel;
			JPanel panel;
			JPanel root;

			this.questionnaire = q;

			jframe = new JFrame();

			jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			for (QLForm form : q.getForms()) {
				forms.add(new DefaultQLForm(form));
			}

			scrollPanel = new JScrollPane(forms.get(0).getComponent());

			root = new JPanel();
			root.setLayout(new BoxLayout(root, BoxLayout.X_AXIS));

			panel = new JPanel();
			panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
			panel.add(scrollPanel);

			root.add(Box.createGlue());
			root.add(panel);
			root.add(Box.createGlue());

			jframe.setContentPane(root);
			jframe.setSize(400, 600);
			jframe.setLocationRelativeTo(null);
		}

		@Override
		public void show() {
			Context context;

			context = new Context();
			for (DefaultQLForm form : forms) {
				form.setContext(context);
			}

			jframe.setVisible(true);
		}
	}

	private static class DefaultQLForm implements QLSwingComponent {

		private final QLForm form;
		private final List<DefaultQLQuestion> questions = new ArrayList<>();
		private final List<DefaultQLSection> sections = new ArrayList<>();

		private final JPanel panel;

		public DefaultQLForm(QLForm form) {
			this.form = form;

			panel = new JPanel();
			panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

			for (QLQuestion question : form.getQuestions()) {
				add(new DefaultQLQuestion(question));
			}

			for (QLSection section : form.getSections()) {
				add(new DefaultQLSection(section));
			}
		}

		private void add(DefaultQLQuestion question) {
			questions.add(question);
			panel.add(question.getComponent());
			panel.add(Box.createRigidArea(new Dimension(0, 2)));
		}

		private void add(DefaultQLSection section) {
			sections.add(section);
			panel.add(section.getComponent());
			panel.add(Box.createRigidArea(new Dimension(0, 2)));
		}

		@Override
		public void setContext(Context context) {
			questions.stream().forEach(q -> q.setContext(context));
			sections.stream().forEach(s -> s.setContext(context));
		}

		@Override
		public JComponent getComponent() {
			return panel;
		}
	}

	private static class DefaultQLSection implements QLSwingComponent, ContextListener {

		private final JPanel panel;
		private final QLSection section;
		private final List<DefaultQLQuestion> questions = new ArrayList<>();
		private final List<DefaultQLSection> sections = new ArrayList<>();

		public DefaultQLSection(QLSection section) {

			this.section = section;

			panel = new JPanel();
			panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

			for (QLQuestion question : section.getQuestions()) {
				add(new DefaultQLQuestion(question));
			}

			for (QLSection subSection : section.getSections()) {
				add(new DefaultQLSection(subSection));
			}

			panel.setVisible(false);
		}

		private void add(DefaultQLQuestion question) {
			questions.add(question);
			panel.add(question.getComponent());
			panel.add(Box.createRigidArea(new Dimension(0, 2)));
		}

		private void add(DefaultQLSection section) {
			sections.add(section);
			panel.add(section.getComponent());
			panel.add(Box.createRigidArea(new Dimension(0, 2)));
		}

		@Override
		public void setContext(Context context) {
			if (section.getExpr() != null) {
				context.addContextListener(this);
			}
			questions.stream().forEach(q -> q.setContext(context));
			sections.stream().forEach(s -> s.setContext(context));
		}

		@Override
		public JComponent getComponent() {
			return panel;
		}

		@Override
		public void contextChanged(Context context) {
			boolean value;

			value = QLExpressionInterpreter.interpret(section.getExpr(), context);

			SwingUtilities.invokeLater(() -> {
				panel.setVisible(value);
				SwingUtilities.windowForComponent(panel).revalidate();
			});
		}
	}

	private static class DefaultQLQuestion implements QLSwingComponent {

		private JPanel panel;
		private QLSwingComponent label;
		private QLSwingComponent input;

		public DefaultQLQuestion(QLQuestion q) {
			panel = new JPanel();

			panel.setLayout(new BorderLayout());

			label = new DefaultLabelWidget(q.getLabel());

			input = createWidget(q.getId(), q.getType());
			if (q.getExpr() != null) {
				input = new ComputedWidget((QLSwingWidget) input, q.getExpr());
			}

			panel.add(label.getComponent(), BorderLayout.CENTER);
			panel.add(input.getComponent(), BorderLayout.EAST);
			panel.setMaximumSize(new Dimension(400, 40));
			panel.setMinimumSize(new Dimension(200, 40));
		}

		@Override
		public JComponent getComponent() {
			return panel;
		}

		@Override
		public void setContext(Context context) {
			label.setContext(context);
			input.setContext(context);
		}
	}

	private static class DefaultLabelWidget extends JLabel implements QLSwingWidget {

		private static final long serialVersionUID = 1L;

		public DefaultLabelWidget(String label) {
			super(label.replaceAll("\"", ""));
			setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		}

		@Override
		public void setContext(Context context) {
			// DefaultLabelWidget does not use the context
		}

		@Override
		public JComponent getComponent() {
			return this;
		}

		@Override
		public Object getValue() {
			return getText();
		}

		@Override
		public boolean setValue(Object value) {
			if (getValue().equals(value)) {
				return false;
			}

			setText((String) value);

			return true;
		}

		@Override
		public void setEditable(boolean editable) {
			// NOOP
		}
	}

	/**
	 * This class wraps a {@link QLSwingWidget} and uses an expression to
	 * compute its value.
	 *
	 */
	private static class ComputedWidget implements QLSwingWidget, ContextListener {

		private final QLSwingWidget widget;
		private final Expr expr;

		public ComputedWidget(QLSwingWidget widget, Expr expr) {
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

	private static class DefaultBooleanWidget extends JPanel implements QLSwingWidget, ActionListener {

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

	private static class DefaultIntegerWidget extends JPanel implements QLSwingWidget {

		private static final long serialVersionUID = 1L;

		private final String variableName;
		private final JTextField textField;

		public DefaultIntegerWidget(String variableName) {
			this.variableName = variableName;

			textField = new JTextField();
			textField.setPreferredSize(new Dimension(100, 20));
			textField.addKeyListener(new KeyAdapter() {

				@Override
				public void keyReleased(KeyEvent e) {
					textField.postActionEvent();
				}
			});

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

	private static class DefaultStringWidget extends JPanel implements QLSwingWidget {

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
