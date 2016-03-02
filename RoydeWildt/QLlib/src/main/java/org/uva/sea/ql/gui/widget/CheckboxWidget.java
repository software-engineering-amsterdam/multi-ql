package org.uva.sea.ql.gui.widget;

import javafx.scene.control.CheckBox;
import org.uva.sea.ql.ast.tree.val.Bool;

/**
 * Created by roy on 28-2-16.
 */
public class CheckboxWidget extends CheckBox {
    private Bool source;

    public CheckboxWidget(Bool source) {
        this.source = source;
    }

    public CheckboxWidget(String text, Bool source) {
        super(text);
        this.source = source;
    }

    public Bool getSource() {
        return source;
    }
}
