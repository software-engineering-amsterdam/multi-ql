package org.uva.sea.ql.ast;

import org.uva.sea.ql.ast.expr.*;

public class ComputedQuestion extends Question {
    
    private Expr calculation;
    
    public ComputedQuestion(Ident identifier, Str label, Int type, Expr theCalculation) {
        super (identifier, label, type);
        calculation = theCalculation;
    }
    
}