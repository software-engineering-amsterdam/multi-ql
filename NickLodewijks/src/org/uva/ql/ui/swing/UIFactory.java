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
import org.uva.ql.ast.BooleanValue;
import org.uva.ql.ast.NumberValue;
import org.uva.ql.ast.StringValue;
import org.uva.ql.ast.Value;
import org.uva.ql.ast.expr.Expr;
import org.uva.ql.ast.form.QLBlock;
import org.uva.ql.ast.form.QLForm;
import org.uva.ql.ast.stat.QLIFStatement;
import org.uva.ql.ast.stat.QLQuestion;
import org.uva.ql.ast.type.QLBooleanType;
import org.uva.ql.ast.type.QLIntegerType;
import org.uva.ql.ast.type.QLStringType;
import org.uva.ql.ast.type.QLTypeVisitor;
import org.uva.ql.ui.UIForm;
import org.uva.ql.ui.UIQuestion;
import org.uva.ql.ui.UIQuestionnaire;
import org.uva.ql.ui.UIWidget;

public class UIFactory {

	public UIQuestionnaire create(QLForm form) {
		DefaultUIQuestionnaire uiQuestionnaire;
		UIForm uiForm;

		form = normalize(form);

		uiQuestionnaire = new DefaultUIQuestionnaire(form);

		uiForm = new DefaultUIForm(form);
		for (QLQuestion question : form.getQuestions()) {
			UIQuestion uiQuestion;

			uiQuestion = create(question);
			uiForm.addQuestion(uiQuestion);
		}

		uiQuestionnaire.addForm(uiForm);

		return uiQuestionnaire;
	}

	private QLForm normalize(QLForm form) {
		QLForm normalizedForm;
		QLBlock normalizedBlock;

		normalizedBlock = new QLBlock(null, getAllQuestions(form.getBody()), null);
		normalizedForm = new QLForm(null, form.getName(), normalizedBlock);

		return normalizedForm;
	}

	private List<QLQuestion> getAllQuestions(QLBlock block) {
		List<QLQuestion> questions;

		questions = new ArrayList<>();

		block.getQuestions().forEach(q -> questions.add(q));

		for (QLIFStatement ifStat : block.getIfStatements()) {
			questions.addAll(getAllQuestions(ifStat.getBody()));
		}

		return questions;
	}

	public UIQuestion create(QLQuestion question) {
		UIWidget<?> labelWidget;
		UIWidget<?> valueWidget;

		labelWidget = new DefaultLabelWidget(question.getLabel());

		valueWidget = question.getType().accept(new QLTypeVisitor<UIWidget<?>, Void>() {

			@Override
			public UIWidget<?> visit(QLBooleanType type, Void context) {
				return new DefaultBooleanWidget(question);
			}

			@Override
			public UIWidget<?> visit(QLStringType type, Void context) {
				return new DefaultStringWidget(question);
			}

			@Override
			public UIWidget<?> visit(QLIntegerType type, Void context) {
				return new DefaultIntegerWidget(question);
			}
		}, null);

		return new DefaultUIQuestion(question, labelWidget, valueWidget);
	}

	public UIWidget<StringValue> createLabelWidget(QLQuestion q) {
		return new DefaultLabelWidget(q.getLabel());
	}

	private static class DefaultUIQuestionnaire implements UIQuestionnaire {

		private final List<UIForm> forms = new ArrayList<>();

		private final JFrame jframe;
		private final JScrollPane scrollPanel;

		public DefaultUIQuestionnaire(QLForm q) {
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
			for (UIForm form : forms) {
				form.setContext(context);
			}

			jframe.setVisible(true);
		}
	}

	private static class DefaultUIForm implements UIForm {

		private final List<UIQuestion> questions = new ArrayList<>();
		private final JPanel panel;

		public DefaultUIForm(QLForm form) {
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

	private static class DefaultUIQuestion implements UIQuestion, ContextListener {

		private final QLQuestion question;

		private final UIWidget<?> labelWidget;
		private final UIWidget<?> valueWidget;

		public DefaultUIQuestion(QLQuestion q, UIWidget<?> labelWidget, UIWidget<?> valueWidget) {
			this.question = q;

			this.labelWidget = labelWidget;
			this.valueWidget = valueWidget;
		}

		@Override
		public void setContext(QLInterpreterContext context) {

			labelWidget.setContext(context);
			valueWidget.setContext(context);

			context.addContextListener(this);

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
		public UIWidget<?> getLabelWidget() {
			return labelWidget;
		}

		@Override
		public UIWidget<?> getValueWidget() {
			return valueWidget;
		}
	}

	private static class DefaultLabelWidget implements UIWidget<StringValue> {

		private final JLabel label;

		public DefaultLabelWidget(String text) {
			label = new JLabel(text);
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
		public StringValue getValue() {
			return new StringValue(label.getText());
		}

		@Override
		public boolean setValue(StringValue value) {
			if (getValue().equals(value)) {
				return false;
			}

			label.setText(value.toString());

			return true;
		}

		@Override
		public void setVisible(boolean visible) {
			label.setVisible(visible);
		}
	}

	private static abstract class AbstractBaseWidget<T extends Value> implements UIWidget<T>, ContextListener {

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
		public final boolean setValue(T value) {
			if (!Objects.equals(context.getValue(variableName), value)) {
				context.setValue(variableName, value);
			}

			return setViewValue(value);
		}

		@Override
		public final T getValue() {
			return getViewValue();
		}

		protected abstract T getDefaultValue();

		protected abstract T getViewValue();

		protected abstract boolean setViewValue(T value);

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

	private static class DefaultBooleanWidget extends AbstractBaseWidget<BooleanValue> implements ActionListener {

		private final JRadioButton rbYes;
		private final JRadioButton rbNo;
		private final JPanel panel;

		public DefaultBooleanWidget(QLQuestion q) {
			super(q.getId(), q.expr());
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
			setValue(new BooleanValue(rbYes.isSelected()));
		}

		@Override
		protected BooleanValue getViewValue() {
			return new BooleanValue(rbYes.isSelected());
		}

		@Override
		protected boolean setViewValue(BooleanValue value) {
			if (getViewValue().equals(value)) {
				return false;
			}

			// Calling doClick will trigger actionPerformed
			if (value.equals(BooleanValue.TRUE)) {
				rbYes.doClick();
			} else {
				rbNo.doClick();
			}

			return true;
		}

		@Override
		protected BooleanValue getDefaultValue() {
			return BooleanValue.FALSE;
		}

		@Override
		public void setVisible(boolean visible) {
			panel.setVisible(visible);
		}
	}

	private static class DefaultIntegerWidget extends AbstractBaseWidget<NumberValue> {

		private final JTextField textField;
		private final JPanel panel;

		public DefaultIntegerWidget(QLQuestion q) {
			super(q.getId(), q.expr());

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
		protected NumberValue getViewValue() {
			try {
				return new NumberValue(Integer.parseInt(textField.getText()));
			} catch (NumberFormatException ex) {
				return new NumberValue(0);
			}
		}

		@Override
		protected boolean setViewValue(NumberValue value) {
			if (getViewValue().equals(value)) {
				return false;
			}

			textField.setText(value == null ? "" : value.toString());
			return true;
		}

		@Override
		protected NumberValue getDefaultValue() {
			return new NumberValue(0);
		}

		@Override
		public void setVisible(boolean visible) {
			textField.setVisible(visible);
		}
	}

	private static class DefaultStringWidget extends AbstractBaseWidget<StringValue> {

		private final JTextField textField;
		private final JPanel panel;

		public DefaultStringWidget(QLQuestion q) {
			super(q.getId(), q.expr());

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
		protected StringValue getViewValue() {
			return new StringValue(textField.getText());
		}

		@Override
		protected boolean setViewValue(StringValue value) {
			if (getViewValue().equals(value)) {
				return false;
			}

			textField.setText(value == null ? "" : value.toString());

			return true;
		}

		@Override
		protected StringValue getDefaultValue() {
			return new StringValue("");
		}

		@Override
		public void setVisible(boolean visible) {
			textField.setVisible(visible);
		}
	}
}
