package org.uva.sea.ql.ast.checker.message;

import org.uva.sea.ql.ast.tree.Node;

import java.util.List;

/**
 * Created by roydewildt on 12/02/16.
 */
public class WarningMessage extends Message {

    public WarningMessage(List<String> msgList, List<Node> nodeList) {
        super(msgList, nodeList);
    }

    @Override
    public Type getType() {
        return Type.WARNING;
    }


}
