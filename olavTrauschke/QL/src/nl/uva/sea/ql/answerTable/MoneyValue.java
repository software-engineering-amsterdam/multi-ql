package nl.uva.sea.ql.answerTable;

import java.math.BigDecimal;

/**
 * Objects of this class represent values of QL
 * {@link nl.uva.sea.ql.ast.question.MoneyQuestion DecimalQuestion}s.
 * 
 * @author Olav Trauschke
 * @version 16-mrt-2016
 */
public class MoneyValue extends Value {
    
    private final BigDecimal value;
    
    /**
     * Constructor for objects of this class.
     * 
     * @param theValue a <code>String</code> representing the value, or
     *                  <code>null</code> to represent an unknown value
     */
    public MoneyValue(String theValue) {
        value = new BigDecimal(theValue);
    }
}
