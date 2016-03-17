package org.uva.sea.ql.gui.widget;

import javafx.scene.control.TextField;
import org.uva.sea.ql.evaluator.EvaluatedQuestion;

/**
 * Created by roy on 3-3-16.
 */
public class NumberFieldWidget extends TextField implements Widget {
    private EvaluatedQuestion parentQuestion;

    public NumberFieldWidget(EvaluatedQuestion question, boolean readOnly) {
        this.parentQuestion = question;
        this.setDisable(readOnly);
    }

    public NumberFieldWidget(String text, EvaluatedQuestion question, boolean readOnly) {
        super(text);
        this.parentQuestion = question;
        this.setDisable(readOnly);
    }

    public EvaluatedQuestion getParentQuestion() {
        return parentQuestion;
    }

    @Override
    public void setInvalid() {
        getStyleClass().add("error");
    }

    @Override
    public void unSetInvalid() {
        getStyleClass().remove("error");
    }
}
