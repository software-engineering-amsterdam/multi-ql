package org.uva.sea.ql.gui.widget;

import javafx.beans.value.ChangeListener;
import javafx.scene.Parent;
import org.uva.sea.ql.ast.tree.atom.var.Var;
import org.uva.sea.ql.evaluator.EvaluatedQuestion;

/**
 * Created by roydewildt on 16/03/16.
 */
public abstract class Widget {
    protected Parent uiElement;
    protected EvaluatedQuestion parentQuestion;

    public abstract void addListener(ChangeListener listener);

    public void setInvalid() {
        uiElement.getStyleClass().add("error");
    }

    public void unSetInvalid() {
        uiElement.getStyleClass().remove("error");
    }

    public Parent getUiElement() {
        return uiElement;
    }

    public EvaluatedQuestion getParentQuestion() {
        return parentQuestion;
    }

    public String getQuestionName(){
        return parentQuestion.getVarname().toString();
    }
}
