package nl.uva.sea.ql.answerTable;

/**
 * Objects of this class represent values of QL
 * {@link nl.uva.sea.ql.ast.question.Question Question}s and
 * {@link nl.uva.sea.ql.ast.expr.Expr Expr}s.
 * 
 * @author Olav Trauschke
 * @version 16-mrt-2016
 */
public abstract class Value {
    
    /**
     * Test whether <code>this Value</code> equals another according to ternary
     * logic.
     * 
     * @param other a <code>Value</code> to compare to this one
     * @return a <code>BooleanValue</code> representing <code>false</code> if
     *          <code>this Value</code> and <code>other</code> don't both
     *          represent a boolean value, both represent a numeric value or
     *          both represent a string value, an unknown value if this is not
     *          the case and <code>this Value</code> or <code>other</code>
     *          represents an unkonwn value and the result of comparing
     *          <code>this Value</code> to <code>other</code> otherwise
     */
    public abstract BooleanValue ternaryEquals(Value other);
    
}
