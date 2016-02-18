package org.uva.sea.ql.ast;

import org.uva.sea.ql.ast.expr.Expr;

public class IfStatement extends ASTNode {
    
    private final Expr condition;
    private final ASTNode toDoIf;
    private final ASTNode toDoElse;
    
    public IfStatement(Expr theCondition, ASTNode toDoInCase, ASTNode toDoInCaseNot) {
        condition = theCondition;
        toDoIf = toDoInCase;
        toDoElse = toDoInCaseNot;
    }
    
}
