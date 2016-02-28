package org.uva.sea.ql.gui;

import javafx.scene.control.CheckBox;
import org.uva.sea.ql.ast.tree.Node;

/**
 * Created by roy on 28-2-16.
 */
public class ASTCheckBox extends CheckBox {
    private Node source;

    public ASTCheckBox(Node source) {
        this.source = source;
    }

    public ASTCheckBox(String text, Node source) {
        super(text);
        this.source = source;
    }

    public Node getSource() {
        return source;
    }
}
