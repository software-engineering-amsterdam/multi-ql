package org.uva.sea.ql.gui.widget.string;

import javafx.beans.value.ChangeListener;
import javafx.scene.control.TextField;
import org.uva.sea.ql.evaluator.EvaluatedQuestion;

/**
 * Created by roy on 28-2-16.
 */
public class TextFieldWidget extends StringWidget {

    public TextFieldWidget(EvaluatedQuestion parentQuestion) {
        this.parentQuestion = parentQuestion;
        TextField textField = new TextField();
        textField.setDisable(parentQuestion.isComputed());
        this.uiElement = textField;
    }

    @Override
    public void setValue(String str) {
        ((TextField) uiElement).setText(str);
    }

    @Override
    public void addListener(ChangeListener listener) {
        ((TextField) uiElement).textProperty().addListener(listener);
    }
}
