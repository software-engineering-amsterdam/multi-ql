package org.uva.sea.ql.ast.tree;

/**
 * Created by roy on 5-2-16.
 */
public abstract class Node{
    private int line;

    public Node(int line){this.line = line;}

    public int getLine() {
        return line;
    }

    public abstract String toString();

}
