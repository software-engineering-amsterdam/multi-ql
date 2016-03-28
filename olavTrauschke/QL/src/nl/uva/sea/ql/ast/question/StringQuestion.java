package nl.uva.sea.ql.ast.question;

import nl.uva.sea.ql.ast.Label;
import nl.uva.sea.ql.ast.expr.Expr;
import nl.uva.sea.ql.ast.expr.Ident;
import nl.uva.sea.ql.generalPurposeVisitors.Visitor;

/**
 * Representation of <code>Question</code>s that return strings in an AST.
 * 
 * @author Olav Trauschke
 * @version 14-mar-2016
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
    
    /**
     * Returns whether <code>this StringQuestion</code> represents a string value.
     * 
     * @return <code>true</code> because objects of this class represent string
     *          values by definition
     */
    @Override
    public boolean isString() {
        return true;
    }
    
    /**
     * Has the children of <code>this Question accept visitor</code> and then
     * has <code>visitor visit this Question</code>.
     * 
     * @param visitor a <code>Visitor</code> that should
     *                  <code>visit this Question</code> and its children
     */
    @Override
    public void accept(Visitor visitor) {
        childrenAccept(visitor);
        visitor.visit(this);
    }
}
