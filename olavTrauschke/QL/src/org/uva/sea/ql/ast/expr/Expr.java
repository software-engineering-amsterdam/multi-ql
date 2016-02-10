package org.uva.sea.ql.ast.expr;

import org.uva.sea.ql.ast.ASTNode;

public abstract class Expr extends ASTNode {
    
    private boolean canBeBoolean;
    private boolean canBeNumeric;
    
    public Expr(boolean canBeBooleanExpr, boolean canBeNumericExpr) {
        canBeBoolean = canBeBooleanExpr;
        canBeNumeric = canBeNumericExpr;
    }
    
    public boolean canBeBoolean() {
        return canBeBoolean;
    }
    
    public boolean canBeNumeric() {
        return canBeNumeric;
    }
    
}
