package eu.bankersen.kevin.ql.gui.widgets;

import java.util.Map;

import eu.bankersen.kevin.ql.form.ast.statements.Question;
import eu.bankersen.kevin.ql.form.ast.values.EmptyValue;
import eu.bankersen.kevin.ql.form.ast.values.Value;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class TextInput extends QuestionPane {
	private final TextField textField;

	public TextInput(Question question) {
		super(question);

		HBox widget = new HBox();
		textField = new TextField();
		textField.setDisable(isComputed());

		textField.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!isComputed()) {
					sentData(newValue);
				}
			}

		});

		widget.getChildren().add(textField);
		super.setInput(widget);
	}

	@Override
	public void dataUpdate(Map<String, Value> environment) {
		if (environment.containsKey(name())) {
			visible(true);
			changeValue(environment.get(name()));
		} else {
			visible(false);
		}
	}

	private void changeValue(Value value) {
		if (value.equals(new EmptyValue())) {
			textField.setText("");
		} else if (isComputed()) {
			textField.setText(value.toString());
		}
	}

}
