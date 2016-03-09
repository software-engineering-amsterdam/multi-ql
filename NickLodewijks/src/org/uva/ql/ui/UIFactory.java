package org.uva.ql.ui;

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

import org.uva.ql.QLContext;
import org.uva.ql.QLContext.ContextListener;
import org.uva.ql.QLInterpreter;
import org.uva.ql.ast.ASTNodeVisitorAdapter;
import org.uva.ql.ast.BooleanValue;
import org.uva.ql.ast.NumberValue;
import org.uva.ql.ast.StringValue;
import org.uva.ql.ast.Value;
import org.uva.ql.ast.expr.And;
import org.uva.ql.ast.expr.BooleanLiteral;
import org.uva.ql.ast.expr.Expr;
import org.uva.ql.ast.form.QLForm;
import org.uva.ql.ast.stat.QLIFStatement;
import org.uva.ql.ast.stat.QLQuestion;
import org.uva.ql.ast.stat.QLQuestionComputed;
import org.uva.ql.ast.stat.QLQuestionInput;
import org.uva.ql.ast.type.QLBooleanType;
import org.uva.ql.ast.type.QLIntegerType;
import org.uva.ql.ast.type.QLStringType;
import org.uva.ql.ast.type.QLTypeVisitor;

public class UIFactory {

	public UIQuestionnaire create(QLForm form) {
		DefaultUIQuestionnaire uiQuestionnaire;
		UIForm uiForm;

		uiQuestionnaire = new DefaultUIQuestionnaire(form);

		uiForm = new DefaultUIForm(form);

		form.accept(new ASTNodeVisitorAdapter<Void, Expr>() {

			@Override
			public Void visit(QLIFStatement node, Expr condition) {
				Expr conjunction;

				conjunction = new And(null, condition, node.getCondition());

				node.getBody().accept(this, conjunction);

				return null;
			}

			@Override
			public Void visit(QLQuestionInput node, Expr condition) {
				UIQuestion uiQuestion;

				uiQuestion = create(node, condition);
				uiForm.addQuestion(uiQuestion);
				return null;
			}

			@Override
			public Void visit(QLQuestionComputed node, Expr condition) {
				UIQuestion uiQuestion;

				uiQuestion = create(node, condition, node.getComputation());
				uiForm.addQuestion(uiQuestion);
				return null;
			}

		}, BooleanLiteral.TRUE);

		uiQuestionnaire.addForm(uiForm);

		return uiQuestionnaire;
	}

	public UIQuestion create(QLQuestion question, Expr condition, Expr valueComputation) {
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

		return new DefaultUIQuestion(labelWidget, valueWidget, condition, valueComputation);
	}

	public UIQuestion create(QLQuestion question, Expr condition) {
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

		return new DefaultUIQuestion(labelWidget, valueWidget, condition, null);
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
			QLContext context;

			context = new QLContext();
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
		public void setContext(QLContext context) {
			questions.stream().forEach(q -> q.setContext(context));
		}

		@Override
		public JComponent getComponent() {
			return panel;
		}
	}

	private static class DefaultUIQuestion implements UIQuestion, ContextListener {

		private final Expr condition;
		private final Expr valueComputation;

		private final UIWidget<?> labelWidget;
		private final UIWidget<?> valueWidget;

		public DefaultUIQuestion(UIWidget<?> labelWidget, UIWidget<?> valueWidget, Expr condition,
				Expr valueComputation) {
			this.condition = condition;

			this.labelWidget = labelWidget;
			this.valueWidget = valueWidget;
			this.valueComputation = valueComputation;

			if (valueComputation != null) {
				this.valueWidget.setEditable(false);
			}
		}

		@Override
		public void setContext(QLContext context) {

			labelWidget.setContext(context);
			valueWidget.setContext(context);

			context.addContextListener(this);

			setVisible(isEnabled(context));
		}

		public boolean isEnabled(QLContext context) {
			return QLInterpreter.interpret(condition, context).equals(BooleanValue.TRUE);
		}

		@Override
		public void contextChanged(QLContext context) {
			setVisible(isEnabled(context));

			if (valueComputation != null) {
				valueWidget.setValue(QLInterpreter.interpret(valueComputation, context));
			}
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
		public void setContext(QLContext context) {
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

		@Override
		public void setEditable(boolean editable) {
			// NOOP
		}
	}

	private static abstract class AbstractBaseWidget<T extends Value> implements UIWidget<T> {

		private final String variableName;

		private QLContext context;

		public AbstractBaseWidget(String variableName) {
			this.variableName = variableName;
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
		public final void setContext(QLContext context) {
			this.context = context;

			context.setValue(variableName, getDefaultValue());
		}
	}

	private static class DefaultBooleanWidget extends AbstractBaseWidget<BooleanValue> implements ActionListener {

		private final JRadioButton rbYes;
		private final JRadioButton rbNo;
		private final JPanel panel;

		public DefaultBooleanWidget(QLQuestion q) {
			super(q.getId());
			ButtonGroup bg;

			panel = new JPanel(new BorderLayout());
			rbYes = new JRadioButton("Yes");
			rbNo = new JRadioButton("No");

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
		public void setEditable(boolean editable) {
			rbYes.setEnabled(editable);
			rbNo.setEnabled(editable);
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
			super(q.getId());

			panel = new JPanel();

			textField = new JTextField();
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

		@Override
		public void setEditable(boolean editable) {
			textField.setEditable(editable);
		}
	}

	private static class DefaultStringWidget extends AbstractBaseWidget<StringValue> {

		private final JTextField textField;
		private final JPanel panel;

		public DefaultStringWidget(QLQuestion q) {
			super(q.getId());

			panel = new JPanel();

			textField = new JTextField();

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

		@Override
		public void setEditable(boolean editable) {
			textField.setEditable(editable);
		}
	}
}
