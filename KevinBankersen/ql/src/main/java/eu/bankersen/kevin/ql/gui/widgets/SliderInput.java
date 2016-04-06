package eu.bankersen.kevin.ql.gui.widgets;

import java.math.BigDecimal;
import java.util.Map;

import eu.bankersen.kevin.ql.form.ast.statements.Question;
import eu.bankersen.kevin.ql.form.ast.values.EmptyValue;
import eu.bankersen.kevin.ql.form.ast.values.Value;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;

public class SliderInput extends Widget {

	private final Slider slider;

	public SliderInput(Question question) {
		super(question);
		HBox input = new HBox();
		slider = new Slider();
		slider.setMin(0);
		slider.setMax(100);
		slider.setShowTickLabels(true);
		slider.setDisable(isComputed());

		slider.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
				if (!isComputed()) {
					sendData(String.valueOf(new_val.intValue()));
				}
			}
		});

		input.getChildren().add(slider);
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
			slider.setValue(0);
		} else if (isComputed()) {
			BigDecimal intVal = (BigDecimal) value.value();
			slider.setValue(intVal.doubleValue());
		}
	}

}
