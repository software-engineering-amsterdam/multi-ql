package org.uva.sea.ql.gui.widget;

import javafx.scene.control.CheckBox;
import org.uva.sea.ql.ast.tree.Node;

/**
 * Created by roy on 28-2-16.
 */
public class CheckboxWidget extends CheckBox {
    private Node source;

    public CheckboxWidget(Node source) {
        this.source = source;
    }

    public CheckboxWidget(String text, Node source) {
        super(text);
        this.source = source;
    }

    public Node getSource() {
        return source;
    }
}
