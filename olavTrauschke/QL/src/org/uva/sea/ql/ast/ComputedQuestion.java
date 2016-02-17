package org.uva.sea.ql.ast;

import org.uva.sea.ql.ast.expr.*;

public class ComputedQuestion extends Question {
    
    private final Expr calculation;
    
    public ComputedQuestion(Ident identifier, Str label, Int type, Expr theCalculation) {
        super (identifier, label, type);
        assert theCalculation != null;
        calculation = theCalculation;
    }
    
    @Override
    public boolean equals(Object o) {
        return super.equals(o) && calculation.equals(((ComputedQuestion) o).calculation);
    }

    @Override
    public int hashCode() {
        return 97 * 3 + calculation.hashCode();
    }
    
}
