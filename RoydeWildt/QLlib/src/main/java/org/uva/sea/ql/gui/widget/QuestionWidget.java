package org.uva.sea.ql.gui.widget;

import javafx.scene.layout.GridPane;
import org.uva.sea.ql.ast.tree.stat.Question;

/**
 * Created by roy on 3-3-16.
 */
public class QuestionWidget extends GridPane {
    private Question question;

    public QuestionWidget(Question question) {
        this.question = question;
    }

    public Question getQuestion() {
        return question;
    }
}
