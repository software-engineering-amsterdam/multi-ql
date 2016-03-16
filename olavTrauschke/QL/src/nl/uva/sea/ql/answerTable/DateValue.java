package nl.uva.sea.ql.answerTable;

/**
 * Objects of this class represent values of QL
 * {@link nl.uva.sea.ql.ast.question.DateQuestion DateQuestion}s.
 * 
 * @author Olav Trauschke
 * @version 16-mar-2016
 */
public class DateValue extends Value {
    
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
    
    /**
     * Test whether <code>this DateValue</code> equals another <code>Value</code>
     * according to ternary logic.
     * 
     * @param o a <code>Value</code> to compare to this one
     * @return a <code>BooleanValue</code> representing <code>false</code> if
     *          <code>other</code> is not a <code>DateValue</code>, an unknown
     *          value if this is not the case and <code>this DateValue</code>
     *          or <code>other</code> represents an unkonwn value and the result
     *          of comparing <code>theDay</code>, <code>theMonth</code> and
     *          <code>theYear</code> of <code>this DateValue</code> to those of
     *          <code>other</code> otherwise
     */
    @Override
    public BooleanValue ternaryEquals(Value o) {
        if (o instanceof DateValue) {
            DateValue other = (DateValue) o;
            boolean equalValues = day == other.day
                   && month == other.month
                   && year == other.year;
            return new BooleanValue(equalValues);
        }
        else {
            return new BooleanValue(false);
        }
    }
    
}
