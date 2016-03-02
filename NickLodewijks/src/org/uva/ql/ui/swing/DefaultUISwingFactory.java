package org.uva.ql.ui.swing;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
import org.uva.ql.ast.type.QLBooleanType;
import org.uva.ql.ast.type.QLIntegerType;
import org.uva.ql.ast.type.QLStringType;
import org.uva.ql.ast.type.QLType;
import org.uva.ql.ast.type.QLTypeVisitor;
import org.uva.ql.domain.Form;
import org.uva.ql.domain.Question;
import org.uva.ql.domain.Questionnaire;
import org.uva.ql.ui.UIComponent;
import org.uva.ql.ui.UIFactory;
import org.uva.ql.ui.UIForm;
import org.uva.ql.ui.UIQuestionnaire;
import org.uva.ql.ui.UIWidget;
import org.uva.ql.ui.UIWidgetFactory;

public class DefaultUISwingFactory implements UIFactory {

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
	public UIComponent create(Form form) {
		DefaultQLForm qlForm;

		qlForm = new DefaultQLForm(form);
		for (Question question : form.getQuestions()) {
			qlForm.add(create(question));
		}

		return qlForm;
	}

	@Override
	public UIComponent create(Question question) {
		UIWidget label;
		UIWidget value;

		label = createLabelWidget(question);
		value = createValueWidget(question);

		return new DefaultQLQuestion(question, label, value);
	}

	@Override
	public UIWidget createLabelWidget(Question q) {
		return new DefaultLabelWidget(q.getLabel());
	}

	@Override
	public UIWidget createValueWidget(Question q) {
		UIWidgetFactory factory;

		factory = QLWidgetFactory.create(q.getType());

		return factory.create(q.getId(), q.getExpr());
	}

	private static class DefaultQLQuestionnaire implements UIQuestionnaire {

		private final List<UIComponent> forms = new ArrayList<>();

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

		public void addForm(UIComponent form) {
			forms.add(form);
			scrollPanel.setViewportView(form.getComponent());
		}

		@Override
		public void show() {
			QLInterpreterContext context;

			context = new QLInterpreterContext();
			for (UIComponent form : forms) {
				form.setContext(context);
			}

			jframe.setVisible(true);
		}
	}

	private static class DefaultQLForm implements UIForm {

		private final List<UIComponent> questions = new ArrayList<>();
		private final JPanel panel;

		public DefaultQLForm(Form form) {
			panel = new JPanel();
			panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		}

		public void add(UIComponent question) {
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

	private static class DefaultQLQuestion implements UIComponent, ContextListener {

		private final Question question;

		private final UIWidget labelWidget;
		private final UIWidget valueWidget;
		private final JPanel panel;

		public DefaultQLQuestion(Question q, UIWidget labelWidget, UIWidget valueWidget) {
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

	private static class DefaultLabelWidget implements UIWidget {

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
	}

	private static abstract class AbstractBaseWidget implements UIWidget, ContextListener {

		private final String variableName;
		private final Expr expr;

		private QLInterpreterContext context;

		public AbstractBaseWidget(String variableName, Expr expr) {
			this.variableName = variableName;
			this.expr = expr;
		}

		public boolean isComputed() {
			return expr != null;
		}

		@Override
		public final boolean setValue(Object value) {
			if (!Objects.equals(context.getValue(variableName), value)) {
				context.setValue(variableName, value);
			}

			return setViewValue(value);
		}

		@Override
		public final Object getValue() {
			return getViewValue();
		}

		protected abstract Object getDefaultValue();

		protected abstract Object getViewValue();

		protected abstract boolean setViewValue(Object value);

		@Override
		public final void setContext(QLInterpreterContext context) {
			this.context = context;

			context.addContextListener(this);
			context.setValue(variableName, getDefaultValue());
		}

		@Override
		public void contextChanged(QLInterpreterContext context) {
			if (expr != null) {
				setValue(QLInterpreter.interpret(expr, context));
			}
		}
	}

	private static class DefaultBooleanWidget extends AbstractBaseWidget implements ActionListener {

		private final JRadioButton rbYes;
		private final JRadioButton rbNo;
		private final JPanel panel;

		public DefaultBooleanWidget(String variableName, Expr expr) {
			super(variableName, expr);
			ButtonGroup bg;

			panel = new JPanel(new BorderLayout());
			rbYes = new JRadioButton("Yes");
			rbNo = new JRadioButton("No");

			if (isComputed()) {
				rbYes.setEnabled(false);
				rbNo.setEnabled(false);
			}

			rbYes.addActionListener(this);
			rbNo.addActionListener(this);

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
		public void actionPerformed(ActionEvent e) {
			setValue(rbYes.isSelected());
		}

		@Override
		protected Object getDefaultValue() {
			return Boolean.FALSE;
		}

		@Override
		protected Object getViewValue() {
			return rbYes.isSelected();
		}

		@Override
		protected boolean setViewValue(Object value) {
			if (getViewValue().equals(value)) {
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
	}

	private static class DefaultIntegerWidget extends AbstractBaseWidget {

		private final JTextField textField;
		private final JPanel panel;

		public DefaultIntegerWidget(String variableName, Expr expr) {
			super(variableName, expr);

			panel = new JPanel();

			textField = new JTextField();
			textField.setEditable(!isComputed());
			textField.setPreferredSize(new Dimension(100, 20));
			textField.addKeyListener(new KeyAdapter() {

				@Override
				public void keyReleased(KeyEvent e) {
					setValue(getViewValue());
				}
			});

			panel.add(textField);
		}

		@Override
		public JComponent getComponent() {
			return panel;
		}

		@Override
		protected Object getViewValue() {
			try {
				return Integer.parseInt(textField.getText());
			} catch (NumberFormatException ex) {
				return 0;
			}
		}

		@Override
		protected boolean setViewValue(Object value) {
			if (getViewValue().equals(value)) {
				return false;
			}

			textField.setText(value == null ? "" : value.toString());
			return true;
		}

		@Override
		protected Object getDefaultValue() {
			return 0;
		}
	}

	private static class DefaultStringWidget extends AbstractBaseWidget {

		private final JTextField textField;
		private final JPanel panel;

		public DefaultStringWidget(String variableName, Expr expr) {
			super(variableName, expr);

			panel = new JPanel();

			textField = new JTextField();
			textField.setEditable(!isComputed());

			textField.setPreferredSize(new Dimension(100, 20));
			textField.addKeyListener(new KeyAdapter() {

				@Override
				public void keyReleased(KeyEvent e) {
					setValue(getViewValue());
				}
			});

			panel.add(textField);
		}

		@Override
		public JComponent getComponent() {
			return panel;
		}

		@Override
		protected Object getDefaultValue() {
			return "";
		}

		@Override
		public Object getViewValue() {
			return textField.getText();
		}

		@Override
		public boolean setViewValue(Object value) {
			if (getViewValue().equals(value)) {
				return false;
			}

			textField.setText(value == null ? "" : value.toString());

			return true;
		}
	}

	private static class QLWidgetFactory implements QLTypeVisitor<UIWidgetFactory> {

		public static UIWidgetFactory create(QLType type) {
			return type.accept(new QLWidgetFactory());
		}

		private QLWidgetFactory() {

		}

		@Override
		public UIWidgetFactory visit(QLBooleanType type) {
			return (id, expr) -> new DefaultBooleanWidget(id, expr);
		}

		@Override
		public UIWidgetFactory visit(QLStringType type) {
			return (id, expr) -> new DefaultStringWidget(id, expr);
		}

		@Override
		public UIWidgetFactory visit(QLIntegerType type) {
			return (id, expr) -> new DefaultIntegerWidget(id, expr);
		}
	}
}
