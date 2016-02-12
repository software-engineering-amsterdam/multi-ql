package org.uva.sea.ql.ast.checker.message;

import org.uva.sea.ql.ast.tree.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by roydewildt on 12/02/16.
 */
public abstract class Message {

    public enum Type{ERROR, WARNING}

    private List<String> msgList = new ArrayList<>();
    private List<Node> nodeList = new ArrayList<>();

    public Message(List<String> msgList, List<Node> nodeList){
        this.msgList = msgList;
        this.nodeList = nodeList;
    }

    public abstract Type getType();

    public List<String> getMsgList() {
        return msgList;
    }

    public List<Node> getNodeList() {
        return nodeList;
    }
}
