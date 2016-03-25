package org.uva.sea.ql.gui.widget.number;

import javafx.beans.value.ChangeListener;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import org.uva.sea.ql.evaluator.EvaluatedQuestion;
import org.uva.sea.ql.gui.widget.Widget;

/**
 * Created by roy on 3-3-16.
 */
public class NumberFieldWidget extends NumberWidget {

    public NumberFieldWidget(EvaluatedQuestion parentQuestion) {
        this.parentQuestion = parentQuestion;
        TextField textField = new TextField();
        textField.setDisable(parentQuestion.isComputed());
        textField.setAlignment(Pos.BASELINE_RIGHT);
        this.uiElement = textField;
    }

    @Override
    public void setValue(Number value) {
        ((TextField)(this.uiElement)).setText(value.toString());
    }

    @Override
    public String getValue() {
        return ((TextField)(this.uiElement)).getText();
    }

    @Override
    public void addListener(ChangeListener listener) {
        ((TextField)(this.uiElement)).textProperty().addListener(listener);
    }
}
