package nl.uva.sea.ql.ast.question;

import nl.uva.sea.ql.ast.expr.Expr;
import nl.uva.sea.ql.ast.expr.Ident;
import nl.uva.sea.ql.ast.Label;

/**
 * Representation of <code>Question</code>s that return booleans in an AST.
 * 
 * @author Olav Trauschke
 * @version 1-mrt-2016
 */
public class BooleanQuestion extends Question {
    
    /**
     * Constructor for <code>BooleanQuestion</code>s that should be answered by
     * the user.
     * 
     * @param identifier the <code>Ident</code> used to identify the constructed
     *                      <code>BooleanQuestion</code>
     * @param label the <code>Label</code> to display with the constructed
     *              <code>BooleanQuestion</code>
     */
    public BooleanQuestion(Ident identifier, Label label) {
        this (identifier, label, null);
    }
    
    /**
     * Constructor for <code>BooleanQuestion</code>s that should be calculated
     * based on the answers to other <code>Question</code>s.
     * 
     * @param identifier the <code>Ident</code> used to identify the constructed
     *                      <code>BooleanQuestion</code>
     * @param label the <code>Label</code> to display with the constructed
     *              <code>BooleanQuestion</code>
     * @param calculation an <code>Expr</code> defining how to compute the value
     *                      of the constructed <code>BooleanQuestion</code>
     */
    public BooleanQuestion(Ident identifier, Label label, Expr calculation) {
        super(identifier, label, calculation);
    }
    
}
