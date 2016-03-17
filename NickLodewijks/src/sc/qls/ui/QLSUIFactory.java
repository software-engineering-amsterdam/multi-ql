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

import sc.ql.ast.Statement.Question;
import sc.ql.eval.Environment;
import sc.ql.ui.UIFactory;
import sc.ql.ui.UIForm;
import sc.ql.ui.UIQuestion;
import sc.ql.ui.UIWidget;
import sc.ql.ui.UIWidgetStyle;
import sc.ql.ui.widget.RadioButtonWidget;
import sc.qls.ast.Page;
import sc.qls.ast.Property;
import sc.qls.ast.Property.ColorProperty;
import sc.qls.ast.Property.FontNameProperty;
import sc.qls.ast.Property.FontSizeProperty;
import sc.qls.ast.Property.FontStyleProperty;
import sc.qls.ast.Property.HeightProperty;
import sc.qls.ast.Property.WidthProperty;
import sc.qls.ast.PropertyVisitor;
import sc.qls.ast.Rule;
import sc.qls.ast.Rule.QuestionRule;
import sc.qls.ast.Section;
import sc.qls.ast.StyleSheet;
import sc.qls.ast.Widget;
import sc.qls.ast.Widget.CheckBox;
import sc.qls.ast.Widget.DropDown;
import sc.qls.ast.Widget.RadioButton;
import sc.qls.ast.Widget.Slider;
import sc.qls.ast.Widget.Spinbox;
import sc.qls.ast.Widget.TextField;
import sc.qls.ast.WidgetVisitor;

public class QLSUIFactory extends UIFactory {

	private StyleSheet styleSheet;

	public QLSUIFactory(StyleSheet styleSheet) {
		this.styleSheet = styleSheet;
	}

	@Override
	protected UIForm createForm() {
		return new QLSUIForm(styleSheet);
	}

	@Override
	protected UIWidget createValueWidget(Question question, Environment env) {
		QuestionRule rule;
		UIWidget uiWidget;
		UIWidgetStyle style;

		rule = styleSheet.getQLSQuestion(question);

		uiWidget = createWidget(rule, env, question);

		// Use default widget
		if (uiWidget == null) {
			uiWidget = super.createValueWidget(question, env);
		}

		style = createStyle(rule, uiWidget.getStyle());

		uiWidget.setStyle(style);

		return uiWidget;
	}

	private UIWidget createWidget(Rule rule, Environment env, Question question) {
		Widget widget;

		widget = rule.widget();
		if (widget == null) {
			return null;
		}

		return widget.accept(new WidgetVisitor<UIWidget, Void>() {

			@Override
			public UIWidget visit(RadioButton widget, Void unused) {
				return new RadioButtonWidget(env, question.name(), widget.getChoices());
			}

			@Override
			public UIWidget visit(DropDown widget, Void unused) {
				return null;
			}

			@Override
			public UIWidget visit(Slider widget, Void unused) {
				return null;
			}

			@Override
			public UIWidget visit(Spinbox widget, Void unused) {
				return null;
			}

			@Override
			public UIWidget visit(TextField widget, Void unused) {
				return null;
			}

			@Override
			public UIWidget visit(CheckBox widget, Void unused) {
				return null;
			}

		}, null);
	}

	private UIWidgetStyle createStyle(Rule rule, UIWidgetStyle defaultStyle) {
		UIWidgetStyle.Builder styleBuilder;

		styleBuilder = new UIWidgetStyle.Builder(defaultStyle);

		for (Property prop : rule.properties()) {
			prop.accept(new PropertyVisitor<Void, Void>() {

				@Override
				public Void visit(ColorProperty property, Void unused) {
					styleBuilder.setColor(property.color());
					return null;
				}

				@Override
				public Void visit(HeightProperty property, Void unused) {
					styleBuilder.setHeight(property.value().value().getValue());
					return null;
				}

				@Override
				public Void visit(WidthProperty property, Void unused) {
					styleBuilder.setWidth(property.value().value().getValue());
					return null;
				}

				@Override
				public Void visit(FontNameProperty property, Void context) {
					styleBuilder.setFontName(property.value().value().getValue());
					return null;
				}

				@Override
				public Void visit(FontSizeProperty property, Void context) {
					styleBuilder.setFontSize(property.value().value().getValue());
					return null;
				}

				@Override
				public Void visit(FontStyleProperty property, Void context) {
					styleBuilder.setFontStyle(property.getStyle());
					return null;
				}
			}, null);
		}

		return styleBuilder.build();
	}

	private static class QLSUIForm implements UIForm {

		private final StyleSheet styleSheet;
		private final List<UIPage> pages = new ArrayList<>();

		private JPanel panel;

		public QLSUIForm(StyleSheet styleSheet) {
			this.styleSheet = styleSheet;

			panel = new JPanel();
			panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

			for (Page page : styleSheet.getPages()) {
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

		private final Page page;
		private JPanel panel;

		public UIPage(Page page) {
			this.page = page;

			panel = new JPanel();
			panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

			for (Section section : page.getSections()) {
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
		private final Section section;

		private JPanel panel;

		public UISection(Section section) {
			TitledBorder title;

			this.section = section;
			this.questions = new ArrayList<>();

			panel = new JPanel();
			panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

			title = BorderFactory.createTitledBorder(section.name());
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
