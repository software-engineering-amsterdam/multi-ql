package eu.bankersen.kevin.ql.gui.widgets;

import java.util.Map;

import eu.bankersen.kevin.ql.form.ast.statements.Question;
import eu.bankersen.kevin.ql.form.ast.values.EmptyValue;
import eu.bankersen.kevin.ql.form.ast.values.Value;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class TextInput extends Widget {
	private final TextField textField;

	public TextInput(Question question) {
		super(question);

		HBox input = new HBox();
		textField = new TextField();
		textField.setDisable(isComputed());

		textField.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!isComputed()) {
					sendData(newValue);
				}
			}

		});

		input.getChildren().add(textField);
		setRight(input);
	}

	@Override
	public void dataUpdate(Map<String, Value> environment) {
		if (environment.containsKey(name())) {
			setVisible(true);
			changeValue(environment.get(name()));
		} else {
			setVisible(false);
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
