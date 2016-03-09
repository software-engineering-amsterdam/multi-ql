package org.uva.sea.ql.gui.widget;

import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import org.uva.sea.ql.ast.tree.stat.Question;

/**
 * Created by roy on 28-2-16.
 */
public class TextFieldWidget extends TextField {
    private Question parentQuestion;

    public TextFieldWidget(Question parent, boolean readOnly) {
        this.parentQuestion = parent;
        this.setDisable(readOnly);

    }

    public TextFieldWidget(String text, Question parent, boolean readOnly) {
        super(text);
        this.parentQuestion = parent;
        this.setDisable(readOnly);
    }

    public Question getParentQuestion() {
        return parentQuestion;
    }

}
