package org.uva.sea.ql.ast.tree;

import org.antlr.v4.runtime.Token;

/**
 * Created by roy on 5-2-16.
 */
public abstract class Node{
    private Token token;

    public Node(){}
    public Node(Token token){this.token = token;}

    public Token getToken() {
        return token;
    }

    public abstract String toString();

}
