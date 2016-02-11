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
    
    //use only for instantiating subclasses when canBeNumeric was unknown when calling super
    protected void setCanBeNumeric(boolean newValue) {
        canBeNumeric = newValue;
    }
    
    //use only for instantiating subclasses when canBeString was unknown when calling super
    protected void setCanBeString(boolean newValue) {
        canBeString = newValue;
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
