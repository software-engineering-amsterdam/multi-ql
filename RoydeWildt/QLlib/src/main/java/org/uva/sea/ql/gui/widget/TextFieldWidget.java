package org.uva.sea.ql.gui.widget;

import javafx.scene.control.TextField;
import org.uva.sea.ql.ast.tree.stat.Question;
import org.uva.sea.ql.evaluator.EvaluatedQuestion;

/**
 * Created by roy on 28-2-16.
 */
public class TextFieldWidget extends TextField implements Widget{
    private EvaluatedQuestion parentQuestion;

    public TextFieldWidget(EvaluatedQuestion parent, boolean readOnly) {
        this.parentQuestion = parent;
        this.setDisable(readOnly);

    }

    public TextFieldWidget(String text, EvaluatedQuestion parent, boolean readOnly) {
        super(text);
        this.parentQuestion = parent;
        this.setDisable(readOnly);
    }

    public EvaluatedQuestion getParentQuestion() {
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
