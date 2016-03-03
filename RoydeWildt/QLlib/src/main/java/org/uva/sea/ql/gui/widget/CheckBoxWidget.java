package org.uva.sea.ql.gui.widget;

import javafx.scene.control.CheckBox;
import org.uva.sea.ql.ast.tree.stat.Question;
import org.uva.sea.ql.ast.tree.val.Bool;

/**
 * Created by roy on 28-2-16.
 */
public class CheckBoxWidget extends CheckBox {
    private Question parentQuestion;

    public CheckBoxWidget(Question parent, boolean readOnly) {
        this.parentQuestion = parent;
        this.setDisable(readOnly);

    }

    public CheckBoxWidget(String text, Question parent, boolean readOnly) {
        super(text);
        this.parentQuestion = parent;
        this.setDisable(readOnly);
    }

    public Question getParentQuestion() {
        return parentQuestion;
    }

}
