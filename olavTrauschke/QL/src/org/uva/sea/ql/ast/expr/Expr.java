package org.uva.sea.ql.ast.expr;

import org.uva.sea.ql.ast.ASTNode;

public abstract class Expr extends ASTNode {
    
    private boolean canBeBoolean;
    private boolean canBeNumeric;
    private boolean canBeString;
    
    public Expr(boolean canBeBooleanExpr, boolean canBeNumericExpr, boolean canBeStringExpr) {
        canBeBoolean = canBeBooleanExpr;
        canBeNumeric = canBeNumericExpr;
        canBeString = canBeStringExpr;
    }
    
    public boolean canBeBoolean() {
        return canBeBoolean;
    }
    
    public boolean canBeNumeric() {
        return canBeNumeric;
    }
    
    
    public boolean canBeString() {
        return canBeString;
    }
}
