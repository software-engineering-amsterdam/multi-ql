package eu.bankersen.kevin.ql.gui.widgets;

import java.util.Map;

import eu.bankersen.kevin.ql.form.ast.statements.Question;
import eu.bankersen.kevin.ql.form.ast.values.EmptyValue;
import eu.bankersen.kevin.ql.form.ast.values.Value;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class RadioInput extends QuestionPane {

	private final ToggleGroup group;
	private final RadioButton yesButton;
	private final RadioButton noButton;

	public RadioInput(Question question) {
		super(question);
		HBox inputWidget = new HBox();
		inputWidget.setSpacing(70);

		group = new ToggleGroup();

		yesButton = createButton("Yes");
		noButton = createButton("No");

		group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

			@Override
			public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
				if (!isComputed() && newValue != null && newValue != oldValue) {
					sentData(newValue.getUserData().toString());
				}
			}
		});

		inputWidget.setHgrow(yesButton, Priority.ALWAYS);
		inputWidget.setHgrow(noButton, Priority.ALWAYS);
		inputWidget.getChildren().addAll(yesButton, noButton);

		super.setInput(inputWidget);
	}

	@Override
	public void dataUpdate(Map<String, Value> environment) {
		if (environment.containsKey(name())) {
			visible(true);
			changeValue(environment.get(name()));
		} else {
			visible(false);
			group.selectToggle(null);
		}
	}

	private void changeValue(Value value) {
		if (value.equals(new EmptyValue())) {
			group.selectToggle(null);
		} else if (isComputed()) {
			selectToggle((boolean) value.value());
		}
	}

	private void selectToggle(boolean value) {
		if (value) {
			group.selectToggle(yesButton);
		} else {
			group.selectToggle(noButton);
		}
	}

	private RadioButton createButton(String text) {
		RadioButton button = new RadioButton(text);
		button.setUserData(text);
		button.setDisable(isComputed());
		button.setToggleGroup(group);
		return button;
	}
}
