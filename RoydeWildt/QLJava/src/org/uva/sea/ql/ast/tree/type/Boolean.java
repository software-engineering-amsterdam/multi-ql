package org.uva.sea.ql.ast.tree.type;

import org.uva.sea.ql.ast.visitor.Visitor;

/**
 * Created by roydewildt on 11/02/16.
 */
public class Boolean extends Type {
    public Boolean(int line) {
        super(line);
    }

    @Override
    public <T> T accept(Visitor visitor) {
        return null;
    }
}
