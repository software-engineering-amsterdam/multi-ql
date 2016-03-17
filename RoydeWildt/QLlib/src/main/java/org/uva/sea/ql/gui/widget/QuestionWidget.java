package org.uva.sea.ql.gui.widget;

import javafx.scene.layout.GridPane;
import org.uva.sea.ql.evaluator.EvaluatedQuestion;

/**
 * Created by roy on 3-3-16.
 */
//TODO: Is this a widget?
public class QuestionWidget extends GridPane implements Widget {
    private EvaluatedQuestion parentQuestion;

    public QuestionWidget(EvaluatedQuestion question) {
        this.parentQuestion = question;
    }

    @Override
    public void setInvalid() {

    }

    @Override
    public void unSetInvalid() {

    }

    public EvaluatedQuestion getParentQuestion() {
        return parentQuestion;
    }
}
