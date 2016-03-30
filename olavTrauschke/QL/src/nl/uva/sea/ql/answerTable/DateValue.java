package nl.uva.sea.ql.answerTable;

import java.util.Date;
import java.util.Objects;

/**
 * TODO document
 * 
 * @author Olav Trauschke
 * @version 29-mar-2016
 */
public class DateValue extends Value {
    
    public static final int HASH_ORIGIN = 497;
    
    private final Date value;
    
    /**
     * TODO document
     * 
     * @param theValue 
     */
    public DateValue(Date theValue) {
        value = theValue;
    }
    
    /**
     * TODO document
     * 
     * @return 
     */
    public Date getValue() {
        return value;
    }
    
    /**
     * TODO document
     * 
     * @param o
     * @return 
     */
    @Override
    public BooleanValue ternaryEquals(Value o) {
        if (o instanceof DateValue) {
            DateValue other = (DateValue) o;
            if (value == null || other.value == null) {
                return new BooleanValue(null);
            }
            else {
                boolean equalValues = value.equals(other.value);
                return new BooleanValue(equalValues);
            }
        }
        else {
            return new BooleanValue(false);
        }
    }
    
    /**
     * TODO document
     * 
     * @param o
     * @return 
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        
        DateValue other = (DateValue) o;
        return value == null ? other.value == null : value.equals(other.value);
    }
    
    /**
     * TODO document
     * 
     * @return 
     */
    @Override
    public int hashCode() {
        return HASH_ORIGIN + Objects.hashCode(this.value);
    }
    
}
