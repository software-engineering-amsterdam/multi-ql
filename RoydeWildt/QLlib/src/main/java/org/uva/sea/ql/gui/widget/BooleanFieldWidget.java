package org.uva.sea.ql.gui.widget;

import javafx.scene.control.CheckBox;
import org.uva.sea.ql.ast.tree.stat.Question;
import org.uva.sea.ql.ast.tree.val.Bool;

/**
 * Created by roy on 28-2-16.
 */
public class BooleanFieldWidget extends CheckBox {
    private Question parentQuestion;

    public BooleanFieldWidget(Question parent, boolean readOnly) {
        this.parentQuestion = parent;
        this.setDisable(readOnly);

    }

    public BooleanFieldWidget(String text, Question parent, boolean readOnly) {
        super(text);
        this.parentQuestion = parent;
        this.setDisable(readOnly);
    }

    public Question getParentQuestion() {
        return parentQuestion;
    }

}
