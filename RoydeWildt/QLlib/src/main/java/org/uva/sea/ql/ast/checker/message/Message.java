package org.uva.sea.ql.ast.checker.message;

import org.uva.sea.ql.ast.tree.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by roydewildt on 12/02/16.
 */
public abstract class Message {

    public enum Type{ERROR, WARNING}

    private String msgStr;
    private Node msgNode;

    public Message(String msgStr, Node msgNode){
        this.msgStr = msgStr;
        this.msgNode = msgNode;
    }

    public abstract Type getType();

    public String getMsg() {
        return msgStr;
    }

    public Node getNode() {
        return msgNode;
    }
}