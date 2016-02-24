package org.uva.sea.ql.ast;

import org.uva.sea.ql.ast.expr.*;

/**
 * Representation of <code>Question</code>s which values are calculated automatically in an AST.
 * 
 * @author Olav Trauschke, 10329463
 * @version 24-feb-2016
 */
public class ComputedQuestion extends Question {
    
    /**
     * Factor partial hashes are multiplied by to generate a hash for objects of this class.
     */
    public static final int HASH_FACTOR = 41;
    
    private final Expr calculation;
    
    /**
     * Constructor for <code>ComputedQuestion</code>s.
     * 
     * @param identifier the <code>Ident</code> used to identify the constructed
     *                      <code>ComputedQuestion</code>
     * @param label the <code>Label</code> to display with the constructed <code>ComputedQuestion</code>
     * @param type an <code>ASTNode</code> representing the type of the answer
     *              to the constructed <code>ComputedQuestion</code>
     * @param theCalculation an <code>Expr</code> defining how to compute the value of the constructed <code>ComputedQuestion</code>
     */
    public ComputedQuestion(Ident identifier, Label label, ASTNode type, Expr theCalculation) {
        super (identifier, label, type);
        assert theCalculation != null;
        calculation = theCalculation;
    }
    
    /**
     * Compares <code>this ComputedQuestion</code> to another <code>Object</code>.
     * A <code>ComputedQuestion</code> is considered equal only to <code>Object</code>s
     * for which <code>Question.equals(o)</code> returns <code>true</code>
     * (which implies they are <code>ComputedQuestion</code>s) and which values
     * are computed by an <code>Expr</code> equal to the one used to compute its
     * own value.
     * 
     * @param o the <code>Object</code> to compare to <code>this ComputedQuestion</code>
     * @return <code>true</code> if and only if o is equal to
     *          <code>this ComputedQuestion</code>
     */
    @Override
    public boolean equals(Object o) {
        return super.equals(o) && calculation.equals(((ComputedQuestion) o).calculation);
    }
    
    /**
     * @return an <code>int</code> containing a hash for <code>this ComputedQuestion</code>
     */
    @Override
    public int hashCode() {
        return HASH_FACTOR * super.hashCode() + calculation.hashCode();
    }
    
}
