package nl.uva.sea.ql.ast.expr;

import java.util.Map;
import nl.uva.sea.ql.answerTable.AnswerTable;
import nl.uva.sea.ql.answerTable.IntValue;
import nl.uva.sea.ql.ast.question.Question;
import nl.uva.sea.ql.generalPurposeVisitors.Visitor;

/**
 * Representation of (literals of) the type int in an AST.
 * 
 * @author Olav Trauschke
 * @version 30-mar-2016
 */
public class Int extends NumericExpr {
    
    /**
     * Start value used to calculate hashes for objects of this class.
     */
    public static final int HASH_ORIGIN = 259;
    
    private final Long value;
    
    /**
     * Constructor for objects of class <code>Int</code>.
     * 
     * @param theValue a <code>Long</code> representing the value of the
     *                  constructed <code>Int</code>
     */
    public Int(Long theValue) {
        assert theValue != null;
        value = theValue;
    }
    
    /**
     * Has <code>visitor visit this Int</code>.
     * 
     * @param visitor a <code>Visitor</code> that should
     *                  <code>visit this Int</code>
     */
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
    
    /**
     * Returns whether <code>this Int</code> represents an integer value.
     * 
     * @param questionTypes a <code>Map</code> from <code>Ident</code>s found in
     *                      cthe ast <code>this Int</code> is part of to a
     *                      <code>Question</code> with that <code>Ident</code>
     * @return <code>true</code>, because an <code>Int</code> represents an int
     *          value by definition
     */
    @Override
    public boolean isInt(Map<Ident,Question> questionTypes) {
        return true;
    }
    
    /**
     * Obtain <code>theValue</code> of <code>this Int</code>.
     * 
     * @param answerTable an <code>AnswerTable</code> that is not used, may also
     *                      be null
     * @return an <code>IntValue</code> representing <code>theValue</code> of
     *          <code>this Int</code>
     */
    @Override
    public IntValue eval(AnswerTable answerTable) {
        return new IntValue(value);
    }
    
     /**
     * Compares <code>this Int</code> to another <code>Object</code>. An
     * <code>Int</code> is considered equal only to other objects of this class,
     * for which <code>theValue</code> is equal to its own value for this field.
     * 
     * @param o the <code>Object</code> to compare to <code>this Int</code>
     * @return <code>true</code> if and only if o is equal to <code>this Int</code> 
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        
        return value.equals(((Int) o).value);
    }
    
    /**
     * @return an <code>int</code> containing a hash for <code>this Int</code>
     */
    @Override
    public int hashCode() {
        return HASH_ORIGIN + value.hashCode();
    }
}
