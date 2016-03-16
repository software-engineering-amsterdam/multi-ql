package nl.uva.sea.ql.answerTable;

/**
 * Objects of this class represent values of QL
 * {@link nl.uva.sea.ql.ast.question.DecimalQuestion DecimalQuestion}s.
 * 
 * @author Olav Trauschke
 * @version 10-mrt-2016
 */
public class DecimalValue {
    
    private final Double value;
    
    /**
     * Constructor for objects of this class.
     * 
     * @param theValue a decimal value or <code>null</code> to represent an
     *                  unknown value
     */
    public DecimalValue(Double theValue) {
        value = theValue;
    }
    
}
