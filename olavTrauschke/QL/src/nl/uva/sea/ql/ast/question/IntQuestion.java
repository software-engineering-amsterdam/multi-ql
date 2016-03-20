package nl.uva.sea.ql.ast.question;

import nl.uva.sea.ql.ASTVisitor;
import nl.uva.sea.ql.ast.Label;
import nl.uva.sea.ql.ast.expr.Expr;
import nl.uva.sea.ql.ast.expr.Ident;

/**
 * Representation of <code>Question</code>s that return decimals in an AST.
 * 
 * @author Olav Trauschke
 * @version 19-mar-2016
 */
public class IntQuestion extends Question {
    
    /**
     * Constructor for <code>IntQuestion</code>s that should be answered by
     * the user.
     * 
     * @param identifier the <code>Ident</code> used to identify the constructed
     *                      <code>IntQuestion</code>
     * @param label the <code>Label</code> to display with the constructed
     *              <code>IntQuestion</code>
     */
    public IntQuestion(Ident identifier, Label label) {
        this (identifier, label, null);
    }
    
    /**
     * Constructor for <code>IntQuestion</code>s that should be computed based
     * on the answers to other <code>Question</code>s.
     * 
     * @param identifier the <code>Ident</code> used to identify the constructed
     *                      <code>DecimalQuestion</code>
     * @param label the <code>Label</code> to display with the constructed
     *              <code>DecimalQuestion</code>
     * @param calculation an <code>Expr</code> defining how to compute the value
     *                      of the constructed <code>IntQuestion</code>
     */
    public IntQuestion(Ident identifier, Label label, Expr calculation) {
        super(identifier, label, calculation);
    }
    
    /**
     * Returns whether <code>this IntQuestion</code> represents an integer value.
     * 
     * @return <code>true</code> because objects of this class represent integer
     *          values by definition
     */
    @Override
    public boolean isInt() {
        return true;
    }
    
    /**
     * Has the children of <code>this Question accept visitor</code> and then
     * has <code>visitor visit this Question</code>.
     * 
     * @param visitor an <code>ASTVisitor</code> that should
     *                  <code>visit this Question</code> and its children
     */
    @Override
    public void accept(ASTVisitor visitor) {
        childrenAccept(visitor);
        visitor.visit(this);
    }
    
}
