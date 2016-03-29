package org.uva.sea.ql.gui.view.preview;

import javafx.scene.layout.GridPane;

/**
 * Created by roy on 3/29/16.
 */
public class QuestionBox extends GridPane {
    private String questionName;

    public QuestionBox(String questionName) {
        this.questionName = questionName;
    }

    public String getQuestionName() {
        return questionName;
    }
}
