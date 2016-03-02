package nl.uva.sea.ql.ast.question;

import nl.uva.sea.ql.ast.Label;
import nl.uva.sea.ql.ast.expr.Expr;
import nl.uva.sea.ql.ast.expr.Ident;

/**
 * Representation of <code>Question</code>s that return strings in an AST.
 * 
 * @author Olav Trauschke
 * @version 1-mrt-2016
 */
public class StringQuestion extends Question {
    
    /**
     * Constructor for <code>StringQuestion</code>s that should be answered by
     * the user.
     * 
     * @param identifier the <code>Ident</code> used to identify the constructed
     *                      <code>StringQuestion</code>
     * @param label the <code>Label</code> to display with the constructed
     *              <code>StringQuestion</code>
     */
    public StringQuestion(Ident identifier, Label label) {
        this (identifier, label, null);
    }
    
    /**
     * Constructor for <code>StringQuestion</code>s that should be computed
     * based on the answers to other <code>Question</code>s.
     * 
     * @param identifier the <code>Ident</code> used to identify the constructed
     *                      <code>StringQuestion</code>
     * @param label the <code>Label</code> to display with the constructed
     *              <code>StringQuestion</code>
     * @param calculation an <code>Expr</code> defining how to compute the value
     *                      of the constructed <code>StrQuestion</code>
     */
    public StringQuestion(Ident identifier, Label label, Expr calculation) {
        super(identifier, label, calculation);
    }
    
}
