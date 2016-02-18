package org.uva.sea.ql.ast;

public class NestedStatement extends ASTNode {
    
    private final ASTNode statement;
    private final ASTNode nestedStatement;
    
    public NestedStatement(ASTNode theStatement, ASTNode theNestedStatement) {
        statement = theStatement;
        nestedStatement = theNestedStatement;
    }
    
}
