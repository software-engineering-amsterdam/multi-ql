package nl.uva.sea.ql.answerTable;

/**
 * Objects of this class represent values of QL
 * {@link nl.uva.sea.ql.ast.question.IntQuestion IntQuestion}s and
 * {@link nl.uva.sea.ql.ast.expr.Expr Expr}s with an integer value.
 * 
 * @author Olav Trauschke
 * @version 10-mrt-2016
 */
public class IntValue {
    
    private final Integer value;
    
    /**
     * Constructor for objects of this class.
     * 
     * @param theValue an integer value or <code>null</code> to represent an
     *                  unknown value
     */
    public IntValue(Integer theValue) {
        value = theValue;
    }
    
}
