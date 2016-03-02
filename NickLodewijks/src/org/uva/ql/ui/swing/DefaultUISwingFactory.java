package org.uva.ql.ui.swing;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import org.uva.ql.QLInterpreter;
import org.uva.ql.QLInterpreterContext;
import org.uva.ql.QLInterpreterContext.ContextListener;
import org.uva.ql.ast.expr.Expr;
import org.uva.ql.ast.type.QLType;
import org.uva.ql.domain.Form;
import org.uva.ql.domain.Question;
import org.uva.ql.domain.Questionnaire;
import org.uva.ql.ui.UIComponent;
import org.uva.ql.ui.UIFactory;
import org.uva.ql.ui.UIForm;
import org.uva.ql.ui.UIQuestionnaire;
import org.uva.ql.ui.UIWidget;
import org.uva.ql.ui.UIWidgetFactory;

public class DefaultUISwingFactory implements UIFactory<JComponent> {

	private static final Map<QLType, UIWidgetFactory<UIWidget<JComponent>>> TYPE_TO_WIDGET_FACTORY;

	static {
		TYPE_TO_WIDGET_FACTORY = new HashMap<>();
		TYPE_TO_WIDGET_FACTORY.put(QLType.BOOLEAN, q -> new DefaultBooleanWidget(q.getId()));
		TYPE_TO_WIDGET_FACTORY.put(QLType.STRING, q -> new DefaultStringWidget(q.getId()));
		TYPE_TO_WIDGET_FACTORY.put(QLType.INTEGER, q -> new DefaultIntegerWidget(q.getId()));
	}

	@Override
	public UIQuestionnaire create(Questionnaire questionnaire) {
		DefaultQLQuestionnaire q;

		q = new DefaultQLQuestionnaire(questionnaire);
		for (Form form : questionnaire.getForms()) {
			q.addForm(create(form));
		}

		return q;
	}

	@Override
	public UIComponent<JComponent> create(Form form) {
		DefaultQLForm qlForm;

		qlForm = new DefaultQLForm(form);
		for (Question question : form.getQuestions()) {
			qlForm.add(create(question));
		}

		return qlForm;
	}

	@Override
	public UIComponent<JComponent> create(Question question) {
		UIWidget<JComponent> label;
		UIWidget<JComponent> value;

		label = createLabelWidget(question);
		value = createValueWidget(question);

		return new DefaultQLQuestion(question, label, value);
	}

	@Override
	public UIWidget<JComponent> createLabelWidget(Question q) {
		return new DefaultLabelWidget(q.getLabel());
	}

	@Override
	public UIWidget<JComponent> createValueWidget(Question q) {
		UIWidget<JComponent> widget;
		QLType type;

		type = q.getType();
		widget = TYPE_TO_WIDGET_FACTORY.get(type).create(q);
		if (q.isComputed()) {
			widget = new ComputedWidget(widget, q.getExpr());
		}

		return widget;
	}

	private static class DefaultQLQuestionnaire implements UIQuestionnaire {

		private final List<UIComponent<JComponent>> forms = new ArrayList<>();

		private final JFrame jframe;
		private final JScrollPane scrollPanel;

		public DefaultQLQuestionnaire(Questionnaire q) {
			JPanel panel;
			JPanel root;

			jframe = new JFrame();
			jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			scrollPanel = new JScrollPane();

			panel = new JPanel();
			panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
			panel.add(scrollPanel);

			root = new JPanel();
			root.setLayout(new BoxLayout(root, BoxLayout.X_AXIS));

			root.add(Box.createGlue());
			root.add(panel);
			root.add(Box.createGlue());

			jframe.setContentPane(root);
			jframe.setSize(400, 600);
			jframe.setLocationRelativeTo(null);
		}

		public void addForm(UIComponent<JComponent> form) {
			forms.add(form);
			scrollPanel.setViewportView(form.getComponent());
		}

		@Override
		public void show() {
			QLInterpreterContext context;

			context = new QLInterpreterContext();
			for (UIComponent<?> form : forms) {
				form.setContext(context);
			}

			jframe.setVisible(true);
		}
	}

	private static class DefaultQLForm implements UIForm<JComponent> {

		private final List<UIComponent<JComponent>> questions = new ArrayList<>();
		private final JPanel panel;

		public DefaultQLForm(Form form) {
			panel = new JPanel();
			panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		}

		public void add(UIComponent<JComponent> question) {
			questions.add(question);
			panel.add(question.getComponent());
			panel.add(Box.createRigidArea(new Dimension(0, 2)));
		}

		@Override
		public void setContext(QLInterpreterContext context) {
			questions.stream().forEach(q -> q.setContext(context));
		}

		@Override
		public JComponent getComponent() {
			return panel;
		}
	}

	private static class DefaultQLQuestion implements UIComponent<JComponent>, ContextListener {

		private final Question question;

		private final UIWidget<JComponent> labelWidget;
		private final UIWidget<JComponent> valueWidget;
		private final JPanel panel;

		public DefaultQLQuestion(Question q, UIWidget<JComponent> labelWidget, UIWidget<JComponent> valueWidget) {
			this.question = q;

			this.labelWidget = labelWidget;
			this.valueWidget = valueWidget;

			panel = new JPanel(new BorderLayout());
			panel.add(labelWidget.getComponent(), BorderLayout.CENTER);
			panel.add(valueWidget.getComponent(), BorderLayout.EAST);
			panel.setMaximumSize(new Dimension(400, 40));
			panel.setMinimumSize(new Dimension(200, 40));
		}

		@Override
		public JComponent getComponent() {
			return panel;
		}

		@Override
		public void setContext(QLInterpreterContext context) {
			if (question.isConditional()) {
				context.addContextListener(this);
			}

			labelWidget.setContext(context);
			valueWidget.setContext(context);
		}

		@Override
		public void contextChanged(QLInterpreterContext context) {
			boolean isEnabled;

			isEnabled = question.isEnabled(context);
			if (isEnabled != panel.isVisible()) {
				SwingUtilities.invokeLater(() -> {
					panel.setVisible(isEnabled);
					SwingUtilities.windowForComponent(panel).revalidate();
				});
			}
		}
	}

	private static class DefaultLabelWidget implements UIWidget<JComponent> {

		private final JLabel label;

		public DefaultLabelWidget(String text) {
			label = new JLabel(text.replaceAll("\"", ""));
			label.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		}

		@Override
		public void setContext(QLInterpreterContext context) {
			// DefaultLabelWidget does not use the context
		}

		@Override
		public JComponent getComponent() {
			return label;
		}

		@Override
		public Object getValue() {
			return label.getText();
		}

		@Override
		public boolean setValue(Object value) {
			if (getValue().equals(value)) {
				return false;
			}

			label.setText((String) value);

			return true;
		}

		@Override
		public void setEditable(boolean editable) {
			// NOOP
		}
	}

	/**
	 * This class wraps a {@link UIWidget<JComponent>} and uses an expression to
	 * compute its value.
	 *
	 */
	private static class ComputedWidget implements UIWidget<JComponent>, ContextListener {

		private final UIWidget<JComponent> widget;
		private final Expr expr;

		public ComputedWidget(UIWidget<JComponent> widget, Expr expr) {
			this.widget = widget;

			widget.setEditable(false);

			this.expr = expr;
		}

		@Override
		public void setContext(QLInterpreterContext context) {
			widget.setContext(context);

			context.addContextListener(this);
		}

		@Override
		public void contextChanged(QLInterpreterContext context) {
			setValue(QLInterpreter.interpret(expr, context));
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

	private static class DefaultBooleanWidget implements UIWidget<JComponent>, ActionListener {

		private final String variableName;
		private final JRadioButton rbYes;
		private final JRadioButton rbNo;
		private final JPanel panel;

		private QLInterpreterContext context;

		public DefaultBooleanWidget(String variableName) {
			ButtonGroup bg;

			this.variableName = variableName;

			panel = new JPanel(new BorderLayout());
			rbYes = new JRadioButton("Yes");
			rbNo = new JRadioButton("No");

			bg = new ButtonGroup();
			bg.add(rbYes);
			bg.add(rbNo);

			panel.add(rbYes, BorderLayout.WEST);
			panel.add(rbNo, BorderLayout.EAST);

			panel.setPreferredSize(new Dimension(90, 30));
		}

		@Override
		public JComponent getComponent() {
			return panel;
		}

		@Override
		public void setContext(QLInterpreterContext context) {
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

	private static class DefaultIntegerWidget implements UIWidget<JComponent> {

		private final String variableName;
		private final JTextField textField;
		private JPanel panel;

		private QLInterpreterContext context;

		public DefaultIntegerWidget(String variableName) {
			this.variableName = variableName;

			panel = new JPanel();

			textField = new JTextField();
			textField.setPreferredSize(new Dimension(100, 20));
			textField.addKeyListener(new KeyAdapter() {

				@Override
				public void keyReleased(KeyEvent e) {
					textField.postActionEvent();
				}
			});

			textField.addActionListener(e -> {
				context.setValue(variableName, getValue());
			});

			panel.add(textField);
		}

		@Override
		public JComponent getComponent() {
			return panel;
		}

		@Override
		public void setContext(QLInterpreterContext context) {
			this.context = context;
			context.setValue(variableName, 0);
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

	private static class DefaultStringWidget implements UIWidget<JComponent> {

		private final String variableName;
		private final JTextField textField;
		private final JPanel panel;

		private QLInterpreterContext context;

		public DefaultStringWidget(String variableName) {
			this.variableName = variableName;

			panel = new JPanel();

			textField = new JTextField();
			textField.setPreferredSize(new Dimension(100, 20));
			textField.addKeyListener(new KeyAdapter() {

				@Override
				public void keyReleased(KeyEvent e) {
					textField.postActionEvent();
				}
			});

			textField.addActionListener(e -> {
				context.setValue(variableName, getValue());
			});

			panel.add(textField);
		}

		@Override
		public JComponent getComponent() {
			return panel;
		}

		@Override
		public void setContext(QLInterpreterContext context) {
			this.context = context;
			context.setValue(variableName, "");
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
