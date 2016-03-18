package org.uva.sea.ql.checker.message;

import org.uva.sea.ql.ast.tree.Node;

/**
 * Created by roydewildt on 12/02/16.
 */
public class ErrorMessage extends Message {

    public ErrorMessage(String message, Node node) {
        super(message, node);
    }

    @Override
    public String toString() {
        String line = "(" + getNode().getToken().getLine() + ":"
                    + getNode().getToken().getCharPositionInLine() + ")";
        return line + " Error: " + this.getMessage();
    }


}
