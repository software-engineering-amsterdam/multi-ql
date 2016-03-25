package org.uva.sea.ql.gui.builder;

import javafx.scene.layout.GridPane;
import org.uva.sea.ql.ast.tree.atom.var.Var;

/**
 * Created by roy on 3/25/16.
 */
public class QuestionUIElement extends GridPane {
    private Var varName;

    public QuestionUIElement(Var varName) {
        this.varName = varName;
    }

    public Var getVarName() {
        return varName;
    }
}
