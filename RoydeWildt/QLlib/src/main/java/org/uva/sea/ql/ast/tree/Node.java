package org.uva.sea.ql.ast.tree;

import org.uva.sea.ql.ast.visitor.Visitable;
import org.uva.sea.ql.ast.visitor.Visitor;

/**
 * Created by roy on 5-2-16.
 */
public abstract class Node implements Visitable{
    private int line;

    public Node(int line){this.line = line;}

    public int getLine() {
        return line;
    }

    public abstract String toString();

}
