package org.uva.sea.ql.ast;

import java.util.Objects;
import org.uva.sea.ql.ast.expr.Expr;
import org.uva.sea.ql.checker.ASTVisitor;

/**
 * Representation of <code>ConditionalStatement</code>s in an AST.
 * 
 * @author Olav Trauschke
 * @version 25-feb-2016
 */
public class ConditionalStatement extends ASTNode {
    
    /**
     * Start value used to calculate hashes for objects of this class.
     */
    public static final int HASH_ORIGIN = 3;
    
    /**
     * Factor partial hashes are multiplied by to generate a hash for objects of this class.
     */
    public static final int HASH_FACTOR = 79;
    
    private final Expr condition;
    private final ASTNode toDoIf;
    private final ASTNode toDoElse;
    
    /**
     * Constructor for <code>ConditionalStatement</code>s.
     * 
     * @param theCondition an <code>Expr</code> determing whether to execute
     *                      <code>toDoInCase</code> or <code>toDoInCaseNot</code>
     * @param toDoInCase value for the constructed <code>ConditionalStatement</code>
     *                      in case <code>theCondition</code> is <code>true</code>
     * @param toDoInCaseNot value for the constructed <code>ConditionalStatment</code>
     *                      in case <code>theCondition</code> is <code>false</code>
     */
    public ConditionalStatement(Expr theCondition, ASTNode toDoInCase, ASTNode toDoInCaseNot) {
        assert theCondition != null && toDoInCase != null;
        condition = theCondition;
        toDoIf = toDoInCase;
        toDoElse = toDoInCaseNot;
    }
    
    /**
     * @return the <code>condition</code> determing whether to execute
     *          <code>toDoInCase</code> or <code>toDoInCaseNot</code> for
     *          <code>this ConditionalStatement</code>
     */
    public Expr getCondition() {
        return condition;
    }
    
    /**
     * Has the <code>condition</code>, the <code>toDoIf</code> and the
     * <code>toDoElse</code> of <code>this ConditionalStatement accept v</code>
     * and then has <code>v visit this ConditionalStatement</code>.
     * 
     * @param v an <code>ASTVisitor</code> that should
     *          <code>visit this ConditionalStatement</code> and its children
     */
    @Override
    public void accept(ASTVisitor v) {
        condition.accept(v);
        toDoIf.accept(v);
        toDoElse.accept(v);
        
        v.visit(this);
    }
    
    /**
     * Compares <code>this ConditionalStatement</code> to another <code>Object</code>.
     * A <code>ConditionalStatement</code> is considered equal only to other
     * objects of this class for which <code>theCondition</code>,
     * <code>toDoInCase</code> and <code>toDoInCase</code> not are equal to its
     * own values for these fields.
     * 
     * @param o the <code>Object</code> to compare to <code>this ConditionalStatement</code>
     * @return <code>true</code> if and only if o is equal to
     *          <code>this ConditionalStatement</code>
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        
        ConditionalStatement other = (ConditionalStatement) o;
        if (condition.equals(other.condition) && toDoIf.equals(other.toDoIf)) {
            return toDoElse == null ? other.toDoElse == null : toDoElse.equals(other.toDoElse);
        }
        else return false;
    }
    
    /**
     * @return an <code>int</code> containing a hash for <code>this ConditionalStatment</code>
     */
    @Override
    public int hashCode() {
        int hash = HASH_ORIGIN;
        hash = HASH_FACTOR * hash + condition.hashCode();
        hash = HASH_FACTOR * hash + toDoIf.hashCode();
        hash = HASH_FACTOR * hash + Objects.hashCode(this.toDoElse);
        return hash;
    }
}
