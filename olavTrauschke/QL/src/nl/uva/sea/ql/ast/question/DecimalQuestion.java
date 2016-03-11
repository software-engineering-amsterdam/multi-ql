package nl.uva.sea.ql.ast.question;

import nl.uva.sea.ql.ast.Label;
import nl.uva.sea.ql.ast.expr.Expr;
import nl.uva.sea.ql.ast.expr.Ident;
import nl.uva.sea.ql.checker.ASTVisitor;

/**
 * Representation of <code>Question</code>s that return decimals in an AST.
 * 
 * @author Olav Trauschke
 * @version 9-mrt-2016
 */
public class DecimalQuestion extends Question {
    
    /**
     * Constructor for <code>DecimalQuestion</code>s that should be answered by
     * the user.
     * 
     * @param identifier the <code>Ident</code> used to identify the constructed
     *                      <code>DecimalQuestion</code>
     * @param label the <code>Label</code> to display with the constructed
     *              <code>DecimalQuestion</code>
     */
    public DecimalQuestion(Ident identifier, Label label) {
        this (identifier, label, null);
    }
    
    /**
     * Constructor for <code>DecimalQuestion</code>s that should be computed
     * based on the answers to other <code>Question</code>s.
     * 
     * @param identifier the <code>Ident</code> used to identify the constructed
     *                      <code>DecimalQuestion</code>
     * @param label the <code>Label</code> to display with the constructed
     *              <code>DecimalQuestion</code>
     * @param calculation an <code>Expr</code> defining how to compute the value
     *                      of the constructed <code>DecimalQuestion</code>
     */
    public DecimalQuestion(Ident identifier, Label label, Expr calculation) {
        super(identifier, label, calculation);
    }
    
    /**
     * Returns whether <code>this DecimalQuestion</code> represents a decimal value.
     * 
     * @return <code>true</code> because objects of this class represent decimal
     *          values by definition
     */
    @Override
    public boolean isDecimal() {
        return true;
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
