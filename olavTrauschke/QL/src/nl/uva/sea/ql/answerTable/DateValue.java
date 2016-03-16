package nl.uva.sea.ql.answerTable;

/**
 * Objects of this class represent values of QL
 * {@link nl.uva.sea.ql.ast.question.DateQuestion DateQuestion}s.
 * 
 * @author Olav Trauschke
 * @version 10-mrt-2016
 */
public class DateValue {
    
    private final byte day;
    private final byte month;
    private final int year;
    
    /**
     * Constructor for objects of this class.
     * 
     * @param theDay a <code>byte</code> representing the day of the month with
     *                  a number between 1 and 31 (inclusive)
     * @param theMonth a <code>byte</code> representing the month with a number
     *                  between 1 and 12 (inclusive)
     * @param theYear an <code>int</code> representing the year
     */
    public DateValue(byte theDay, byte theMonth, int theYear) {
        assert theDay > 0 && theDay < 32;
        assert theMonth > 0 && theMonth < 13;
        day = theDay;
        month = theMonth;
        year = theYear;
    }
    
}
