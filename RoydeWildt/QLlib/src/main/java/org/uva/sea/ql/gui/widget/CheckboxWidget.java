package org.uva.sea.ql.gui.widget;

import javafx.scene.control.CheckBox;
import org.uva.sea.ql.ast.tree.stat.Question;
import org.uva.sea.ql.ast.tree.val.Bool;

/**
 * Created by roy on 28-2-16.
 */
public class CheckboxWidget extends CheckBox {
    private Question parentQuestion;

    public CheckboxWidget(Question parent) {
        this.parentQuestion = parent;
    }

    public CheckboxWidget(String text, Question parent) {
        super(text);
        this.parentQuestion = parent;
    }

    public Question getParentQuestion() {
        return parentQuestion;
    }
}
