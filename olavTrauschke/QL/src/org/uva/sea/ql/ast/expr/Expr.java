package org.uva.sea.ql.ast.expr;

import org.uva.sea.ql.ast.ASTNode;

public abstract class Expr extends ASTNode {
    
    //force subclasses to overwrite equals
    @Override
    public abstract boolean equals(Object o);
    
    //force subclasses to overwrite hashCode
    @Override
    public abstract int hashCode();
    
}
