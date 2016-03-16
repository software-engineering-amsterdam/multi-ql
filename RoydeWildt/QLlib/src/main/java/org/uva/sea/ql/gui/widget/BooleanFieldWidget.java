package org.uva.sea.ql.gui.widget;

import javafx.scene.control.CheckBox;
import org.uva.sea.ql.ast.tree.stat.Question;

/**
 * Created by roy on 28-2-16.
 */
public class BooleanFieldWidget extends CheckBox implements Widget{
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

    @Override
    public void setInvalid() {
        getStyleClass().add("error");
    }

    @Override
    public void unSetInvalid() {
        getStyleClass().remove("error");
    }
}
