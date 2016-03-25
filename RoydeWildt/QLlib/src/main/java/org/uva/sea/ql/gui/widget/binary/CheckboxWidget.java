package org.uva.sea.ql.gui.widget.binary;

import javafx.beans.value.ChangeListener;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Control;
import org.uva.sea.ql.evaluator.EvaluatedQuestion;
import org.uva.sea.ql.gui.widget.Widget;

/**
 * Created by roy on 28-2-16.
 */
public class CheckboxWidget extends BooleanWidget {

    public CheckboxWidget(EvaluatedQuestion parentQuestion){
        this.parentQuestion = parentQuestion;
        CheckBox checkBox = new CheckBox();
        checkBox.setDisable(parentQuestion.isComputed());
        this.uiElement = checkBox;
    }

    @Override
    public void setValue(Boolean bool) {
        ((CheckBox)uiElement).setSelected(bool);
    }

    @Override
    public Boolean isSelected() {
        return ((CheckBox)uiElement).isSelected();
    }

    @Override
    public void addListener(ChangeListener listener) {
        ((CheckBox)uiElement).selectedProperty().addListener(listener);
    }
}
