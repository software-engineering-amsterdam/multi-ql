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
import org.uva.ql.ui.UIQuestion;
import org.uva.ql.ui.UIQuestionnaire;
import org.uva.ql.ui.UIWidget;
import org.uva.ql.ui.UIWidgetFactory;

public class DefaultUISwingFactory implements UIFactory {

	@Override
	public UIQuestionnaire create(Questionnaire questionnaire) {
		DefaultUIQuestionnaire uiQuestionnaire;

		uiQuestionnaire = new DefaultUIQuestionnaire(questionnaire);

		for (Form form : questionnaire.getForms()) {
			UIForm uiForm;

			uiForm = create(form);
			uiQuestionnaire.addForm(uiForm);
		}

		return uiQuestionnaire;
	}

	@Override
	public UIForm create(Form form) {
		DefaultUIForm uiForm;

		uiForm = new DefaultUIForm(form);
		for (Question question : form.getQuestions()) {
			UIQuestion uiQuestion;

			uiQuestion = create(question);
			uiForm.addQuestion(uiQuestion);
		}

		return uiForm;
	}

	@Override
	public UIQuestion create(Question question) {
		UIWidget labelWidget;
		UIWidget valueWidget;

		labelWidget = createLabelWidget(question);
		valueWidget = createValueWidget(question);

		return new DefaultQLQuestion(question, labelWidget, valueWidget);
	}

	public UIWidget createLabelWidget(Question q) {
		return new DefaultLabelWidget(q.getLabel());
	}

	public UIWidget createValueWidget(Question q) {
		UIWidgetFactory factory;

		factory = DefaultUIWidgetFactory.get(q.getType());

		return factory.create(q);
	}

	private static class DefaultUIQuestionnaire implements UIQuestionnaire {

		private final List<UIForm> forms = new ArrayList<>();

		private final JFrame jframe;
		private final JScrollPane scrollPanel;

		public DefaultUIQuestionnaire(Questionnaire q) {
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

		@Override
		public void addForm(UIForm form) {
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

	private static class DefaultUIForm implements UIForm {

		private final List<UIQuestion> questions = new ArrayList<>();
		private final JPanel panel;

		public DefaultUIForm(Form form) {
			panel = new JPanel();
			panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		}

		@Override
		public void addQuestion(UIQuestion question) {
			JPanel qPanel;

			questions.add(question);

			qPanel = new JPanel(new BorderLayout());
			qPanel.add(question.getLabelWidget().getComponent(), BorderLayout.CENTER);
			qPanel.add(question.getValueWidget().getComponent(), BorderLayout.EAST);
			qPanel.setMaximumSize(new Dimension(400, 40));
			qPanel.setMinimumSize(new Dimension(200, 40));

			panel.add(qPanel);
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

	private static class DefaultQLQuestion implements UIQuestion, ContextListener {

		private final Question question;

		private final UIWidget labelWidget;
		private final UIWidget valueWidget;

		public DefaultQLQuestion(Question q, UIWidget labelWidget, UIWidget valueWidget) {
			this.question = q;

			this.labelWidget = labelWidget;
			this.valueWidget = valueWidget;
		}

		@Override
		public void setContext(QLInterpreterContext context) {

			labelWidget.setContext(context);
			valueWidget.setContext(context);

			if (question.isConditional()) {
				context.addContextListener(this);
			}

			setVisible(question.isEnabled(context));
		}

		@Override
		public void contextChanged(QLInterpreterContext context) {
			setVisible(question.isEnabled(context));
		}

		private void setVisible(boolean visible) {
			labelWidget.setVisible(visible);
			valueWidget.setVisible(visible);
		}

		@Override
		public UIWidget getLabelWidget() {
			return labelWidget;
		}

		@Override
		public UIWidget getValueWidget() {
			return valueWidget;
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

		@Override
		public void setVisible(boolean visible) {
			label.setVisible(visible);
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

			context.setValue(variableName, getDefaultValue());
			context.addContextListener(this);
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

		public DefaultBooleanWidget(Question q) {
			super(q.getId(), q.getExpr());
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

		@Override
		protected Object getDefaultValue() {
			return Boolean.FALSE;
		}

		@Override
		public void setVisible(boolean visible) {
			panel.setVisible(visible);
		}
	}

	private static class DefaultIntegerWidget extends AbstractBaseWidget {

		private final JTextField textField;
		private final JPanel panel;

		public DefaultIntegerWidget(Question q) {
			super(q.getId(), q.getExpr());

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

		@Override
		public void setVisible(boolean visible) {
			textField.setVisible(visible);
		}
	}

	private static class DefaultStringWidget extends AbstractBaseWidget {

		private final JTextField textField;
		private final JPanel panel;

		public DefaultStringWidget(Question q) {
			super(q.getId(), q.getExpr());

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
			return textField.getText();
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
			return "";
		}

		@Override
		public void setVisible(boolean visible) {
			textField.setVisible(visible);
		}
	}

	private static class DefaultUIWidgetFactory implements QLTypeVisitor<UIWidgetFactory> {

		/**
		 * Returns the {@code UIWidgetFactory} for widgets of type {@code type}.
		 * 
		 * @param type
		 *            the {@code QLType} of the widget.
		 * @return the {@link UIWidgetFactory}
		 * @throws NullPointerException
		 *             if {@code type} is {@code null}.
		 */
		public static UIWidgetFactory get(QLType type) {
			return type.accept(new DefaultUIWidgetFactory());
		}

		private DefaultUIWidgetFactory() {

		}

		@Override
		public UIWidgetFactory visit(QLBooleanType type) {
			return q -> new DefaultBooleanWidget(q);
		}

		@Override
		public UIWidgetFactory visit(QLStringType type) {
			return q -> new DefaultStringWidget(q);
		}

		@Override
		public UIWidgetFactory visit(QLIntegerType type) {
			return q -> new DefaultIntegerWidget(q);
		}
	}
}
