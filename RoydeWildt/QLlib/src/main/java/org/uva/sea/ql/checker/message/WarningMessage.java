package org.uva.sea.ql.checker.message;

import org.uva.sea.ql.ast.tree.Node;

/**
 * Created by roydewildt on 12/02/16.
 */
public class WarningMessage extends Message {

    public WarningMessage(String msgStr, Node msgNode) {
        super(msgStr, msgNode);
    }

    @Override
    public String toString() {
        String line = "(" + getNode().getToken().getLine() + ":"
                + getNode().getToken().getCharPositionInLine() + ")";
        return line + " Warning: " + this.getMessage();
    }
}
