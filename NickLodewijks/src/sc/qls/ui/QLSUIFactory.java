package sc.qls.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import sc.ql.Environment;
import sc.ql.ast.stat.Question;
import sc.ql.ui.UIFactory;
import sc.ql.ui.UIForm;
import sc.ql.ui.UIQuestion;
import sc.ql.ui.UIWidget;
import sc.ql.ui.widget.RadioButtonWidget;
import sc.qls.ast.page.QLSPage;
import sc.qls.ast.page.QLSSection;
import sc.qls.ast.page.QLSStyleSheet;
import sc.qls.ast.widget.QLSRadioButton;
import sc.qls.ast.widget.QLSWidget;
import sc.qls.ast.widget.QLSWidgetType;

public class QLSUIFactory extends UIFactory {

	private QLSStyleSheet styleSheet;

	public QLSUIFactory(QLSStyleSheet styleSheet) {
		this.styleSheet = styleSheet;
	}

	@Override
	protected UIForm createForm() {
		return new QLSUIForm(styleSheet);
	}

	@Override
	protected UIWidget createValueWidget(Question question, Environment context) {
		QLSWidgetType widgetType;
		QLSWidget widget;
		UIWidget uiWidget;

		widget = styleSheet.getQLSQuestion(question).getWidget();

		// Use default widget
		if (widget == null) {
			uiWidget = super.createValueWidget(question, context);
		} else {
			widgetType = widget.getType();

			if (widgetType instanceof QLSRadioButton) {
				uiWidget = new RadioButtonWidget(context, question.getId(), ((QLSRadioButton) widgetType).getChoices());
			} else {
				uiWidget = super.createValueWidget(question, context);
			}
		}

		return uiWidget;
	}

	private static class QLSUIForm implements UIForm {

		private final QLSStyleSheet styleSheet;
		private final List<UIPage> pages = new ArrayList<>();

		private JPanel panel;

		public QLSUIForm(QLSStyleSheet styleSheet) {
			this.styleSheet = styleSheet;

			panel = new JPanel();
			panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

			for (QLSPage page : styleSheet.getPages()) {
				UIPage uiPage;

				uiPage = new UIPage(page);
				pages.add(uiPage);
				panel.add(uiPage.getComponent());
				panel.add(Box.createRigidArea(new Dimension(0, 2)));
			}
		}

		@Override
		public void addQuestion(UIQuestion question) {
			for (UIPage page : pages) {
				if (page.shouldContain(question)) {
					page.addQuestion(question);
				}
			}
		}

		@Override
		public JComponent getComponent() {
			return panel;
		}
	}

	private static class UIPage implements UIForm {

		private final List<UISection> sections = new ArrayList<>();

		private final QLSPage page;
		private JPanel panel;

		public UIPage(QLSPage page) {
			this.page = page;

			panel = new JPanel();
			panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

			for (QLSSection section : page.getSections()) {
				UISection uiSection;

				uiSection = new UISection(section);
				sections.add(uiSection);

				panel.add(uiSection.getComponent());
				panel.add(Box.createRigidArea(new Dimension(0, 2)));
			}
		}

		public boolean shouldContain(UIQuestion question) {
			for (UISection section : sections) {
				if (section.shouldContain(question)) {
					return true;
				}
			}

			return false;
		}

		@Override
		public void addQuestion(UIQuestion question) {
			for (UISection section : sections) {
				if (section.shouldContain(question)) {
					section.addQuestion(question);
				}
			}
		}

		@Override
		public JComponent getComponent() {
			return panel;
		}
	}

	private static class UISection implements UIForm {

		private final ArrayList<UIQuestion> questions;
		private final QLSSection section;

		private JPanel panel;

		public UISection(QLSSection section) {
			TitledBorder title;

			this.section = section;
			this.questions = new ArrayList<>();

			panel = new JPanel();
			panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

			title = BorderFactory.createTitledBorder(section.getName());
			title.setTitleJustification(TitledBorder.LEFT);
			panel.setBorder(title);
		}

		public boolean shouldContain(UIQuestion question) {
			return section.contains(question.getId());
		}

		@Override
		public void addQuestion(UIQuestion question) {

			questions.add(question);

			questions.sort(new Comparator<UIQuestion>() {

				@Override
				public int compare(UIQuestion o1, UIQuestion o2) {
					return Integer.compare(section.indexOf(o1.getId()), section.indexOf(o2.getId()));
				}
			});

			panel.removeAll();

			for (UIQuestion uiQuestion : questions) {
				JPanel qPanel;

				qPanel = new JPanel(new BorderLayout());
				qPanel.add(uiQuestion.getLabelWidget().getComponent(), BorderLayout.CENTER);
				qPanel.add(uiQuestion.getValueWidget().getComponent(), BorderLayout.EAST);
				qPanel.setMaximumSize(new Dimension(400, 40));
				qPanel.setMinimumSize(new Dimension(200, 40));

				panel.add(qPanel);
				panel.add(Box.createRigidArea(new Dimension(0, 2)));
			}

			panel.revalidate();
		}

		@Override
		public JComponent getComponent() {
			return panel;
		}
	}
}
