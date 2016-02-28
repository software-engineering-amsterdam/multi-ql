package org.uva.sea.ql.ast.visitor.context;

import org.uva.sea.ql.ast.tree.Node;

/**
 * Created by roy on 28-2-16.
 */
public class NodeUpdate {
    private final Node oldNode;
    private final Node newNode;

    public NodeUpdate(Node oldNode, Node newNode) {
        this.oldNode = oldNode;
        this.newNode = newNode;
    }

    public Node getOldNode() {
        return oldNode;
    }

    public Node getNewNode() {
        return newNode;
    }
}
