package org.uva.sea.ql.gui.widget;

import javafx.scene.control.TextField;
import org.uva.sea.ql.ast.tree.stat.Question;

/**
 * Created by roy on 3-3-16.
 */
public class MoneyFieldWidget extends TextField implements Widget {
    private Question parentQuestion;

    public MoneyFieldWidget(Question question, boolean readOnly) {
        this.parentQuestion = question;
        this.setDisable(readOnly);
    }

    public MoneyFieldWidget(String text, Question question, boolean readOnly) {
        super(text);
        this.parentQuestion = question;
        this.setDisable(readOnly);
    }

    public Question getParentQuestion() {
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
