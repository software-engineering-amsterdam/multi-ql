package org.uva.sea.ql.checker.message;

import org.uva.sea.ql.ast.tree.Node;

/**
 * Created by roydewildt on 12/02/16.
 */
public abstract class Message {

    private String message;
    private Node node;

    public Message(String message, Node node){
        this.message = message;
        this.node = node;
    }

    public String getMessage() {
        return message;
    }

    public Node getNode() {
        return node;
    }

    @Override
    public abstract String toString();
}
