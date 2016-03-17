package sc.qls.ui;

import java.util.ArrayList;
import java.util.List;

import sc.ql.ast.Statement.Question;
import sc.ql.ast.ValueType;
import sc.ql.ast.ValueType.BooleanType;
import sc.ql.ast.ValueType.IntegerType;
import sc.ql.ast.ValueType.StringType;
import sc.ql.ast.ValueTypeVisitor;
import sc.ql.eval.Environment;
import sc.ql.ui.UIFactory;
import sc.ql.ui.UIQuestion;
import sc.ql.ui.UIQuestionnaire;
import sc.ql.ui.widget.UIRadioButton;
import sc.ql.ui.widget.UITextField;
import sc.ql.ui.widget.UIWidget;
import sc.ql.ui.widget.UIWidgetChoice;
import sc.ql.ui.widget.UIWidgetChoices;
import sc.ql.ui.widget.UIWidgetStyle;
import sc.ql.value.BooleanValue;
import sc.ql.value.NumberValue;
import sc.ql.value.StringValue;
import sc.ql.value.Value;
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
import sc.qls.ast.StyleSheet;
import sc.qls.ast.Widget;
import sc.qls.ast.Widget.CheckBox;
import sc.qls.ast.Widget.DropDown;
import sc.qls.ast.Widget.ListWidget;
import sc.qls.ast.Widget.RadioButton;
import sc.qls.ast.Widget.Slider;
import sc.qls.ast.Widget.Spinbox;
import sc.qls.ast.Widget.TextField;
import sc.qls.ast.WidgetVisitor;
import sc.qls.ui.widget.UIDropDown;
import sc.qls.ui.widget.UISlider;
import sc.qls.ui.widget.UISpinner;

public class QLSUIFactory extends UIFactory {

	private StyleSheet styleSheet;

	public QLSUIFactory(StyleSheet styleSheet) {
		this.styleSheet = styleSheet;
	}

	@Override
	protected UIQuestionnaire createQuestionnaire(List<UIQuestion> questions) {
		return new StyledUIQuestionnaire(questions, styleSheet);
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
		ValueType type;

		widget = rule.widget();
		if (widget == null) {
			return null;
		}

		type = question.type();

		return widget.accept(new WidgetVisitor<UIWidget, Void>() {

			@Override
			public UIWidget visit(RadioButton widget, Void unused) {
				return new UIRadioButton(env, question, createChoices(type, widget));
			}

			@Override
			public UIWidget visit(DropDown widget, Void unused) {
				return new UIDropDown(env, question, createChoices(type, widget));
			}

			@Override
			public UIWidget visit(Slider widget, Void unused) {
				return new UISlider(env, question, createChoices(type, widget));
			}

			@Override
			public UIWidget visit(Spinbox widget, Void unused) {
				return new UISpinner(env, question, createChoices(type, widget));
			}

			@Override
			public UIWidget visit(TextField widget, Void unused) {
				return type.accept(new ValueTypeVisitor<UIWidget, Void>() {

					@Override
					public UIWidget visit(BooleanType type, Void unused) {
						return new UITextField(env, question, BooleanValue.FALSE);
					}

					@Override
					public UIWidget visit(StringType type, Void unused) {
						return new UITextField(env, question, new StringValue(""));
					}

					@Override
					public UIWidget visit(IntegerType type, Void unused) {
						return new UITextField(env, question, new NumberValue(0));
					}
				}, null);
			}

			@Override
			public UIWidget visit(CheckBox widget, Void unused) {
				return null;
			}

		}, null);
	}

	private UIWidgetChoices createChoices(ValueType type, ListWidget widget) {
		List<UIWidgetChoice> uiChoices;
		UIWidgetChoice defaultUiChoice;

		uiChoices = new ArrayList<>();
		for (String value : widget.values()) {
			uiChoices.add(new UIWidgetChoice(value, createValue(type, value)));
		}

		defaultUiChoice = null;
		if (widget.defaultValue() != null) {
			defaultUiChoice = new UIWidgetChoice(widget.defaultValue(), createValue(type, widget.defaultValue()));
		}

		return new UIWidgetChoices(uiChoices, defaultUiChoice);
	}

	private Value createValue(ValueType type, String text) {
		return type.accept(new ValueTypeVisitor<Value, Void>() {

			@Override
			public Value visit(BooleanType type, Void context) {
				return new BooleanValue(text);
			}

			@Override
			public Value visit(IntegerType type, Void context) {
				return new NumberValue(text);
			}

			@Override
			public Value visit(StringType type, Void context) {
				return new StringValue(text);
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
}
