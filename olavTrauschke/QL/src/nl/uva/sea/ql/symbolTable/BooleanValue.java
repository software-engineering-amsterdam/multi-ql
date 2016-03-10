package nl.uva.sea.ql.symbolTable;

/**
 * Objects of this class represent values of QL
 * {@link nl.uva.sea.ql.ast.question.BooleanQuestion BooleanQuestion}s and
 * {@link nl.uva.sea.ql.ast.expr.Expr Expr}s with a boolean value.
 * 
 * @author Olav Trauschke
 * @version 10-mrt-2016
 */
public class BooleanValue {
    
    private final Boolean value;
    
    /**
     * Constructor for objects of this class.
     * 
     * @param theValue <code>true</code>, <code>false</code> or <code>null</code>
     *                  (for unknown)
     */
    public BooleanValue(Boolean theValue) {
        value = theValue;
    }
    
}