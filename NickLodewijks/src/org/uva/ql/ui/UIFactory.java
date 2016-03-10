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

import org.uva.ql.QLContext;
import org.uva.ql.QLContext.ContextListener;
import org.uva.ql.QLInterpreter;
import org.uva.ql.ast.QLTopDown;
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
import org.uva.ql.ast.value.BooleanValue;
import org.uva.ql.ast.value.NumberValue;
import org.uva.ql.ast.value.StringValue;
import org.uva.ql.ast.value.Value;

public class UIFactory {

	public UIQuestionnaire create(QLForm form) {
		QLContext context;
		UIForm uiForm;

		context = new QLContext();

		uiForm = new DefaultUIForm();

		form.accept(new QLTopDown<Void, Expr>() {

			@Override
			public Void visit(QLIFStatement node, Expr condition) {
				Expr conjunction;

				conjunction = new And(condition, node.getCondition());

				node.getBody().accept(this, conjunction);

				return null;
			}

			@Override
			public Void visit(QLQuestionInput node, Expr condition) {
				UIQuestion uiQuestion;

				uiQuestion = create(context, node, condition);
				uiForm.addQuestion(uiQuestion);
				return null;
			}

			@Override
			public Void visit(QLQuestionComputed node, Expr condition) {
				UIQuestion uiQuestion;

				uiQuestion = create(context, node, condition, node.getComputation());
				uiForm.addQuestion(uiQuestion);
				return null;
			}

		}, BooleanLiteral.TRUE);

		return new DefaultUIQuestionnaire(uiForm);
	}

	private UIQuestion create(QLContext context, QLQuestion question, Expr condition) {
		return create(context, question, condition, null);
	}

	private UIQuestion create(QLContext context, QLQuestion question, Expr condition, Expr valueComputation) {
		UIWidget labelWidget;
		UIWidget valueWidget;

		labelWidget = new DefaultLabelWidget(question.getLabel());

		valueWidget = question.getType().accept(new QLTypeVisitor<UIWidget, Void>() {

			@Override
			public UIWidget visit(QLBooleanType type, Void unused) {
				return new DefaultBooleanWidget(context, question);
			}

			@Override
			public UIWidget visit(QLStringType type, Void unused) {
				return new DefaultStringWidget(context, question);
			}

			@Override
			public UIWidget visit(QLIntegerType type, Void unused) {
				return new DefaultIntegerWidget(context, question);
			}
		}, null);

		return new DefaultUIQuestion(context, question, labelWidget, valueWidget, condition, valueComputation);
	}

	private static class DefaultUIQuestionnaire implements UIQuestionnaire {

		private final JFrame jframe;
		private final JScrollPane scrollPanel;

		public DefaultUIQuestionnaire(UIForm form) {
			JPanel panel;
			JPanel root;

			jframe = new JFrame();
			jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			scrollPanel = new JScrollPane();
			scrollPanel.setViewportView(form.getComponent());

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
		public void show() {
			jframe.setVisible(true);
		}
	}

	private static class DefaultUIForm implements UIForm {

		private final List<UIQuestion> questions = new ArrayList<>();
		private final JPanel panel;

		public DefaultUIForm() {
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
		public JComponent getComponent() {
			return panel;
		}
	}

	private static class DefaultUIQuestion implements UIQuestion, ContextListener {

		private QLQuestion question;
		private final Expr condition;
		private final Expr valueComputation;

		private final UIWidget labelWidget;
		private final UIWidget valueWidget;

		public DefaultUIQuestion(QLContext context, QLQuestion question, UIWidget labelWidget, UIWidget valueWidget,
				Expr condition, Expr valueComputation) {
			this.question = question;
			this.condition = condition;

			this.labelWidget = labelWidget;
			this.valueWidget = valueWidget;
			this.valueComputation = valueComputation;

			if (valueComputation != null) {
				this.valueWidget.setEditable(false);
			}

			if (valueComputation != null) {
				context.addComputedValue(question.getId(), valueComputation);
			}

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
				valueWidget.setValue(context.getValue(question.getId()));
			}

		}

		private void setVisible(boolean visible) {
			SwingUtilities.invokeLater(() -> {
				labelWidget.setVisible(visible);
				valueWidget.setVisible(visible);
			});
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
			label = new JLabel(text);
			label.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
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
		public void setValue(Value value) {
			label.setText(value.toString());
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

	private static abstract class AbstractBaseWidget implements UIWidget {

		private final String variableName;
		private final QLContext context;

		public AbstractBaseWidget(QLContext context, String variableName) {
			this.variableName = variableName;
			this.context = context;

			context.setValue(variableName, getDefaultValue());
		}

		@Override
		public final void setValue(Value value) {
			context.setValue(variableName, value);

			SwingUtilities.invokeLater(() -> {
				if (getViewValue().equals(value)) {
					return;
				}

				setViewValue(value);
			});
		}

		@Override
		public final Value getValue() {
			return getViewValue();
		}

		protected abstract Value getDefaultValue();

		protected abstract Value getViewValue();

		protected abstract void setViewValue(Value value);
	}

	private static class DefaultBooleanWidget extends AbstractBaseWidget implements ActionListener {

		private final JRadioButton rbYes;
		private final JRadioButton rbNo;
		private final JPanel panel;

		public DefaultBooleanWidget(QLContext context, QLQuestion q) {
			super(context, q.getId());
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
		protected void setViewValue(Value value) {
			// Calling doClick will trigger actionPerformed
			if (value.equals(BooleanValue.TRUE)) {
				rbYes.doClick();
			} else {
				rbNo.doClick();
			}
		}

		@Override
		protected BooleanValue getDefaultValue() {
			return BooleanValue.FALSE;
		}

		@Override
		public void setVisible(boolean visible) {
			panel.setVisible(visible);
		}

		@Override
		public void setEditable(boolean editable) {
			rbYes.setEnabled(editable);
			rbNo.setEnabled(editable);
		}
	}

	private static class DefaultIntegerWidget extends AbstractBaseWidget {

		private final JTextField textField;
		private final JPanel panel;

		public DefaultIntegerWidget(QLContext context, QLQuestion q) {
			super(context, q.getId());

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
		protected void setViewValue(Value value) {
			textField.setText(value == null ? "" : value.toString());
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

	private static class DefaultStringWidget extends AbstractBaseWidget {

		private final JTextField textField;
		private final JPanel panel;

		public DefaultStringWidget(QLContext context, QLQuestion q) {
			super(context, q.getId());

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
		protected void setViewValue(Value value) {
			textField.setText(value == null ? "" : value.toString());
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
