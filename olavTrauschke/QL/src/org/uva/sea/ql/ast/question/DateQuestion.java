package org.uva.sea.ql.ast.question;

import org.uva.sea.ql.ast.Label;
import org.uva.sea.ql.ast.expr.Expr;
import org.uva.sea.ql.ast.expr.Ident;

/**
 * Representation of <code>Question</code>s that return dates in an AST.
 * 
 * @author Olav Trauschke
 * @version 1-mrt-2016
 */
public class DateQuestion extends Question {
    
    /**
     * Constructor for <code>DateQuestion</code>s that should be answered by
     * the user.
     * 
     * @param identifier the <code>Ident</code> used to identify the constructed
     *                      <code>DateQuestion</code>
     * @param label the <code>Label</code> to display with the constructed
     *              <code>DateQuestion</code>
     */
    public DateQuestion(Ident identifier, Label label) {
        this (identifier, label, null);
    }
    
    /**
     * Constructor for <code>DateQuestion</code>s that should be computed based
     * on the answers to other <code>Question</code>s.
     * 
     * @param identifier the <code>Ident</code> used to identify the constructed
     *                      <code>DateQuestion</code>
     * @param label the <code>Label</code> to display with the constructed
     *              <code>DateQuestion</code>
     *              <code>ComputedDateQuestion</code>
     * @param calculation an <code>Expr</code> defining how to compute the value
     *                      of the constructed <code>DateQuestion</code>
     */
    public DateQuestion(Ident identifier, Label label, Expr calculation) {
        super(identifier, label, calculation);
    }
    
}
