package nl.uva.sea.ql.ast.expr;

/**
 * Representation of <code>Expr</code>s which are sure to have a boolean value
 * in an AST.
 * 
 * @author Olav Trauschke
 * @version 24-feb-2016
 */
public abstract class BooleanExpr extends Expr {
    
    /**
     * Returns whether <code>this ASTNode</code> represents a boolean value.
     * 
     * @return <code>true</code>, because objects of this class represent booleans by definition
     */
    @Override
    public boolean isBoolean() {
        return true;
    }
    
}
