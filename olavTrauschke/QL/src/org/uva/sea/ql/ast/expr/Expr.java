package org.uva.sea.ql.ast.expr;

import org.uva.sea.ql.ast.ASTNode;

public abstract class Expr extends ASTNode {
    
    @Override
    public abstract boolean equals(Object o);

    @Override
    public abstract int hashCode();
    
}
