package org.uva.sea.ql.ast.question;

import org.uva.sea.ql.ast.Label;
import org.uva.sea.ql.ast.expr.Expr;
import org.uva.sea.ql.ast.expr.Ident;

/**
 * Representation of <code>ComputedQuestion</code>s that return booleans in an AST.
 * 
 * @author Olav Trauschke
 * @version 25-feb-2016
 */
public class ComputedBooleanQuestion extends ComputedQuestion {
    
    /**
     * Constructor for <code>ComputedBooleanQuestion</code>s.
     * 
     * @param identifier the <code>Ident</code> used to identify the constructed
     *                      <code>ComputedBooleanQuestion</code>
     * @param label the <code>Label</code> to display with the constructed
     *              <code>ComputedBooleanQuestion</code>
     * @param calculation an <code>Expr</code> defining how to compute the value
     *                      of the constructed <code>ComputedBooleanQuestion</code>
     */
    public ComputedBooleanQuestion(Ident identifier, Label label, Expr calculation) {
        super (identifier, label, calculation);
    }
    
}
