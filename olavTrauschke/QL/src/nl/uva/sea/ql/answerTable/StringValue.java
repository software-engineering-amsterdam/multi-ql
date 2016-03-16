package nl.uva.sea.ql.answerTable;

/**
 * Objects of this class represent values of QL
 * {@link nl.uva.sea.ql.ast.question.StringQuestion StringQuestion}s and
 * {@link nl.uva.sea.ql.ast.expr.Expr Expr}s with a string value.
 * 
 * @author Olav Trauschke
 * @version 10-mrt-2016
 */
public class StringValue {
    
    private final String value;
    
    /**
     * Constructor for objects of this class.
     * 
     * @param theValue a string value or <code>null</code> to represent an
     *                  unknown value
     */
    public StringValue(String theValue) {
        value = theValue;
    }
}
