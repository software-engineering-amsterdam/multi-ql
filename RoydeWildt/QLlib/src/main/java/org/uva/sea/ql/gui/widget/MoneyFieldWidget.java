package org.uva.sea.ql.gui.widget;

import javafx.scene.control.TextField;
import org.uva.sea.ql.ast.tree.stat.Question;

/**
 * Created by roy on 3-3-16.
 */
public class MoneyFieldWidget extends TextField {
    private Question parentQuestion;

    public MoneyFieldWidget(Question question) {
        this.parentQuestion = question;
    }

    public MoneyFieldWidget(String text, Question question) {
        super(text);
        this.parentQuestion = question;
    }

    public Question getParentQuestion() {
        return parentQuestion;
    }
}
