package nl.uva.sea.ql.ast.expr;

import java.util.Map;
import nl.uva.sea.ql.answerTable.AnswerTable;
import nl.uva.sea.ql.answerTable.Value;
import nl.uva.sea.ql.ast.ASTNode;
import nl.uva.sea.ql.ast.question.Question;

/**
 * Representation of expression in an AST.
 * 
 * @author Olav Trauschke
 * @version 16-mrt-2016
 */
public abstract class Expr extends ASTNode {
    
    /**
     * Returns whether <code>this Expr</code> represents a boolean value.
     * 
     * @param questionTypes a <code>Map</code> from each <code>Ident</code>
     *                      <code>this Expr</code> might contain to a
     *                      <code>Question</code> with that <code>Ident</code>
     * @return <code>false</code> by default, should be overwritten apropriatly
     *          in subclasses representing booleans
     */
    public boolean isBoolean(Map<Ident,Question> questionTypes){
        return false;
    }
    
    /**
     * Returns whether <code>this Expr</code> represents a decimal value.
     * 
     * @param questionTypes a <code>Map</code> from each <code>Ident this Expr</code>
     *                      might contain to a <code>Question</code> with that
     *                      <code>Ident</code>
     * @return <code>false</code> by default, should be overwritten apropriatly
     *          in subclasses representing decimals
     */
    public boolean isDecimal(Map<Ident,Question> questionTypes) {
        return false;
    }
    
    /**
     * Returns whether <code>this Expr</code> represents an integer value.
     * 
     * @param questionTypes a <code>Map</code> from each <code>Ident this Expr</code>
     *                      might contain to a <code>Question</code> with that
     *                      <code>Ident</code>
     * @return <code>false</code> by default, should be overwritten apropriatly
     *          in subclasses representing integers
     */
    public boolean isInt(Map<Ident,Question> questionTypes) {
        return false;
    }
    
    /**
     * Returns whether <code>this Expr</code> represents a money value.
     * 
     * @param questionTypes a <code>Map</code> from each <code>Ident this Expr</code>
     *                      might contain to a <code>Question</code> with that
     *                      <code>Ident</code>
     * @return <code>false</code> by default, should be overwritten apropriatly
     *          in subclasses representing money
     */
    public boolean isMoney(Map<Ident,Question> questionTypes) {
        return false;
    }
    
    /**
     * Returns whether <code>this Expr</code> represents a string value.
     * 
     * @param questionTypes a <code>Map</code> from each <code>Ident this Expr</code>
     *                      might contain to a <code>Question</code> with that
     *                      <code>Ident</code>
     * @return <code>false</code> by default, should be overwritten apropriatly
     *          in subclasses representing strings
     */
    public boolean isString(Map<Ident,Question> questionTypes) {
        return false;
    }
    
    /**
     * Returns whether <code>this Expr</code> represents a numeric value. A
     * numeric value is a value that is either decimal (that includes integer
     * values) or money.
     * 
     * @param questionTypes a <code>Map</code> from each <code>Ident this Expr</code>
     *                      might contain to a <code>Question</code> with that
     *                      <code>Ident</code>
     * @return a <code>Map</code> from each <code>Ident this Expr</code>
     *          might contain to a <code>Question</code> with that
     *          <code>Ident</code>
     */
    public boolean isNumeric(Map<Ident,Question> questionTypes) {
        return isDecimal(questionTypes) || isMoney(questionTypes);
    }
    
    public abstract Value eval(AnswerTable answerTable);
    
    /**
     * Compares <code>this Expr</code> to another <code>Object</code>.
     * Implemented to force subclasses to overwrite
     * {@link java.lang.Object#equals(java.lang.Object) Object.equals(Object)},
     * which should be done in a semantic way.
     * 
     * @param o the <code>Object</code> to compare to <code>this Expr</code>
     * @return <code>true</code> if and only if o is equal to <code>this Expr</code> 
     */
    @Override
    public abstract boolean equals(Object o);
    
    /**
     * Overwrites {@link java.lang.Object#hashCode() Object.hashCode()} to force
     * subclasses to overwrite it, to keep it consistent with <code>equals()</code>.
     * 
     * @return an <code>int</code> containing a hash for <code>this Expr</code> 
     */
    @Override
    public abstract int hashCode();
    
}
