package nl.uva.sea.ql.ast.question;

import nl.uva.sea.ql.ast.Label;
import nl.uva.sea.ql.ast.expr.Expr;
import nl.uva.sea.ql.ast.expr.Ident;
import nl.uva.sea.ql.checker.ASTVisitor;

/**
 * Representation of <code>Question</code>s that return dates in an AST.
 * 
 * @author Olav Trauschke
 * @version 9-mar-2016
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
    
    /**
     * Has the children of <code>this Question accept v</code> and then
     * has <code>v visit this Question</code>.
     * 
     * @param v an <code>ASTVisitor</code> that should
     *          <code>visit this Question</code> and its children
     */
    @Override
    public void accept(ASTVisitor v) {
        childrenAccept(v);
        v.visit(this);
    }
    
}
