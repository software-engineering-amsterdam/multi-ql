package sc.ql.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import sc.ql.QLContext;
import sc.ql.QLContext.ContextListener;
import sc.ql.QLInterpreter;
import sc.ql.ast.QLTopDown;
import sc.ql.ast.expr.Expression;
import sc.ql.ast.expr.Expression.And;
import sc.ql.ast.expr.Expression.BooleanLiteral;
import sc.ql.ast.form.QLForm;
import sc.ql.ast.stat.QLIFStatement;
import sc.ql.ast.stat.QLQuestion;
import sc.ql.ast.stat.QLQuestionComputed;
import sc.ql.ast.stat.QLQuestionInput;
import sc.ql.ast.type.QLBooleanType;
import sc.ql.ast.type.QLIntegerType;
import sc.ql.ast.type.QLStringType;
import sc.ql.ast.type.QLTypeVisitor;
import sc.ql.ast.value.BooleanValue;
import sc.ql.ast.value.NumberValue;
import sc.ql.ast.value.StringValue;
import sc.ql.ui.widget.LabelWidget;
import sc.ql.ui.widget.RadioButtonWidget;
import sc.ql.ui.widget.TextFieldWidget;

public class UIFactory {

	private static UIFactory customFactory;

	public static synchronized void set(UIFactory factory) {
		customFactory = factory;
	}

	public static synchronized UIFactory get() {
		if (customFactory != null) {
			return customFactory;
		}

		return new UIFactory();
	}

	public UIQuestionnaire create(QLForm form) {
		QLContext context;
		UIForm uiForm;

		context = new QLContext();

		uiForm = createForm();

		form.accept(new QLTopDown<Void, Expression>() {

			@Override
			public Void visit(QLIFStatement node, Expression condition) {
				Expression conjunction;

				conjunction = new And(condition, node.getCondition());

				node.getBody().accept(this, conjunction);

				return null;
			}

			@Override
			public Void visit(QLQuestionInput node, Expression condition) {
				UIQuestion uiQuestion;

				uiQuestion = create(context, node, condition);
				uiForm.addQuestion(uiQuestion);
				return null;
			}

			@Override
			public Void visit(QLQuestionComputed node, Expression condition) {
				UIQuestion uiQuestion;

				uiQuestion = create(context, node, condition, node.getComputation());
				uiForm.addQuestion(uiQuestion);
				return null;
			}

		}, BooleanLiteral.TRUE);

		return new DefaultUIQuestionnaire(uiForm);
	}

	protected UIForm createForm() {
		return new DefaultUIForm();
	}

	private UIQuestion create(QLContext context, QLQuestion question, Expression condition) {
		return create(context, question, condition, null);
	}

	private UIQuestion create(QLContext context, QLQuestion question, Expression condition,
			Expression valueComputation) {
		UIWidget labelWidget;
		UIWidget valueWidget;

		labelWidget = createLabelWidget(question);
		valueWidget = createValueWidget(question, context);

		return new DefaultUIQuestion(context, question, labelWidget, valueWidget, condition, valueComputation);
	}

	protected UIWidget createLabelWidget(QLQuestion question) {
		return new LabelWidget(question.getLabel());
	}

	protected UIWidget createValueWidget(QLQuestion question, QLContext context) {
		return question.getType().accept(new QLTypeVisitor<UIWidget, Void>() {

			@Override
			public UIWidget visit(QLBooleanType type, Void unused) {
				final UIWidgetChoice YES;
				final UIWidgetChoice NO;
				UIWidgetChoices choices;

				YES = new UIWidgetChoice("Yes", BooleanValue.TRUE);
				NO = new UIWidgetChoice("No", BooleanValue.FALSE);

				choices = new UIWidgetChoices(Arrays.asList(YES, NO), NO);

				return new RadioButtonWidget(context, question.getId(), choices);
			}

			@Override
			public UIWidget visit(QLStringType type, Void unused) {
				return new TextFieldWidget(context, question, new StringValue(""));
			}

			@Override
			public UIWidget visit(QLIntegerType type, Void unused) {
				return new TextFieldWidget(context, question, new NumberValue(0));
			}
		}, null);
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
			scrollPanel.setBorder(null);

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

		private final QLQuestion question;
		private final Expression condition;
		private final Expression valueComputation;

		private final UIWidget labelWidget;
		private final UIWidget valueWidget;

		public DefaultUIQuestion(QLContext context, QLQuestion question, UIWidget labelWidget, UIWidget valueWidget,
				Expression condition, Expression valueComputation) {
			this.question = question;
			this.condition = condition;

			this.labelWidget = labelWidget;
			this.valueWidget = valueWidget;
			this.valueComputation = valueComputation;

			if (valueComputation != null) {
				this.valueWidget.setEditable(false);
				context.addComputedValue(question.getId(), valueComputation);
			}

			context.addContextListener(this);

			setVisible(isEnabled(context));
		}

		@Override
		public String getId() {
			return question.getId();
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
}
