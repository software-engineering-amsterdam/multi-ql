package org.uva.sea.ql.gui.widget;

import javafx.scene.control.TextField;
import org.uva.sea.ql.ast.tree.stat.Question;

/**
 * Created by roy on 3-3-16.
 */
public class NumberFieldWidget extends TextField {
    private Question parentQuestion;

    public NumberFieldWidget(Question question, boolean readOnly) {
        this.parentQuestion = question;
        this.setDisable(readOnly);
    }

    public NumberFieldWidget(String text, Question question, boolean readOnly) {
        super(text);
        this.parentQuestion = question;
        this.setDisable(readOnly);
    }

    public Question getParentQuestion() {
        return parentQuestion;
    }
}
