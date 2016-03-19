package nl.uva.sea.ql.ast.expr;

import java.util.Map;
import nl.uva.sea.ql.ASTVisitor;
import nl.uva.sea.ql.answerTable.AnswerTable;
import nl.uva.sea.ql.answerTable.StringValue;
import nl.uva.sea.ql.ast.question.Question;

/**
 * Representation of (literals of) the type string in an AST.
 * 
 * @author Olav Trauschke
 * @version 16-mar-2016
 */
public class Str extends Expr {
    
    /**
     * Start value used to calculate hashes for objects of this class.
     */
    public static final int HASH_ORIGIN = 249;
    
    private final String value;
    
    /**
     * Constructor for objects of class <code>Str</code>.
     * 
     * @param theValue a <code>String</code> representing the value of the
     *                  constructed <code>Str</code>
     */
    public Str(String theValue) {
        assert theValue != null;
        value = theValue;
    }
    
    /**
     * Has <code>visitor visit this Str</code>.
     * 
     * @param visitor an <code>ASTVisitor</code> that should
     *                  <code>visit this Str</code>
     */
    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
    
    /**
     * Returns whether <code>this Str</code> represents a string value.
     * 
     * @param questionTypes a <code>Map</code> from <code>Ident</code>s found in
     *                      the ast <code>this Str</code> is part of to a
     *                      <code>Question</code> with that <code>Ident</code>
     * @return <code>true</code>, because a <code>Str</code> represents a string
     *          value by definition
     */
    @Override
    public boolean isString(Map<Ident,Question> questionTypes) {
        return true;
    }
    
    /**
     * Obtain <code>theValue</code> of <code>this Str</code>.
     * 
     * @param answerTable an <code>AnswerTable</code> that is not used, may also
     *                      be null
     * @return a <code>StringValue</code> representing <code>theValue</code> of
     *          <code>this Str</code>
     */
    @Override
    public StringValue eval(AnswerTable answerTable) {
        return new StringValue(value);
    }
    
    /**
     * Compares <code>this Str</code> to another <code>Object</code>. A
     * <code>Str</code> is considered equal only to other objects of this class,
     * for which <code>theValue</code> is equal to its own value for this field.
     * 
     * @param o the <code>Object</code> to compare to <code>this Int</code>
     * @return <code>true</code> if and only if o is equal to <code>this Str</code> 
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        
        return value.equals(((Str) o).value);
    }
    
    /**
     * @return an <code>int</code> containing a hash for <code>this Str</code>
     */
    @Override
    public int hashCode() {
        return HASH_ORIGIN + value.hashCode();
    }
    
    /**
     * @return <code>theValue</code> of <code>this Str</code> as a <code>String</code>
     */
    public String getValue() {
        return value;
    }
    
}
