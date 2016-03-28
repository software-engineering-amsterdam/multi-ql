package nl.uva.sea.ql.ast.question;

import java.util.Objects;
import nl.uva.sea.ql.answerTable.AnswerTable;
import nl.uva.sea.ql.answerTable.Value;
import nl.uva.sea.ql.ast.ASTNode;
import nl.uva.sea.ql.ast.Label;
import nl.uva.sea.ql.ast.expr.*;
import nl.uva.sea.ql.generalPurposeVisitors.Visitor;
import nl.uva.sea.ql.interpreter.DisplayableQuestion;
import nl.uva.sea.ql.interpreter.DisplayableQuestionGenerator;

/**
 * Representation of <code>Question</code>s in an AST.
 * 
 * @author Olav Trauschke
 * @version 28-mar-2016
 */
public abstract class Question extends ASTNode {
    
    /**
     * Start value used to calculate hashes for objects of this class.
     */
    public static final int HASH_ORIGIN = 7;
    
    /**
     * Factor partial hashes are multiplied by to generate a hash for objects of this class.
     */
    public static final int HASH_FACTOR = 67;
    
    private final Ident identifier;
    private final Label label;
    private final Expr calculation;
    
    /**
     * Constructor for <code>Questions</code>s.
     * 
     * @param theIdentifier an <code>Ident</code> used to identify the constructed
     *                      <code>Question</code>
     * @param theLabel a <code>Label</code> to display with the constructed
     *                  <code>Question</code>
     * @param theCalculation an <code>Expr</code> defining how to compute the value
     *                          of the constructed <code>Question</code> or
     *                          <code>null</code> if it should be answered by
     *                          the user
     */
    public Question(Ident theIdentifier, Label theLabel, Expr theCalculation) {
        assert theIdentifier != null && theLabel != null;
        identifier = theIdentifier;
        label = theLabel;
        calculation = theCalculation;
    }
    
    /**
     * Evaluate <code>theCalculation</code> of <code>this Question</code>.
     * 
     * @param answerTable an <code>AnswerTable</code> mapping each
     *                      <code>Ident theCalculation</code> of
     *                      <code>thisQuestion</code> may contain to its current
     *                      <code>Value</code> or to <code>null</code> if its
     *                      <code>Value</code> is currently unknonwn
     * @return the <code>Value theCalculation</code> currently has, or
     *          <code>null</code> if that is unknown
     */
    public Value evalCalculation(AnswerTable answerTable) {
        if (calculation == null) return null;
        
        return calculation.eval(answerTable);
    }
    
    /**
     * @return the <code>Ident</code> used to identify <code>this Question</code>
     */
    public Ident getIdentifier() {
        return identifier;
    }
    
    /**
     * @return the <code>Label</code> to be displayed with <code>this Question</code>
     */
    public Label getLabel() {
        return label;
    }
    
    /**
     * @return the <code>Expr</code> defining how to compute the value of
     *          <code>this Question</code>, or <code>null</code> if it should be
     *          answered by the user
     */
    public Expr getCalculation() {
        return calculation;
    }
    
    /**
     * @return a <code>String</code> representing <code>theLabel</code> to be
     *          displayed with <code>this Question</code>
     */
    public String obtainLabelString() {
        return label.toString();
    }
    
    /**
     * Has the children of <code>this Question accept visitor</code> and then has
     * <code>visitor visit this Question</code>. Not implemented here for more
     * specific dispatch.
     * 
     * @param visitor a <code>Visitor</code> that should
     *          <code>visit this Question</code> and its children
     */
    @Override
    public abstract void accept(Visitor visitor);
    
    /**
     * Has the <code>identifier</code>, the <code>label</code> and the
     * <code>calculation</code> of <code>this Question accept visitor</code>.
     * 
     * @param visitor a <code>Visitor</code> that should <code>visit</code> the
     *          children of <code>this Question</code>
     */
    protected void childrenAccept(Visitor visitor) {
        identifierAccept(visitor);
        labelAccept(visitor);
        calculationAccept(visitor);
    }
    
    /**
     * Has the <code>identifier</code> of <code>this Question accept visitor</code>.
     * 
     * @param visitor a <code>Visitor</code> that the <code>identifier</code> of
     *          <code>this Question</code> should <code>accept</code>
     */
    protected void identifierAccept(Visitor visitor) {
        identifier.accept(visitor);
    }
    
    /**
     * Has the <code>label</code> of <code>this Question accept visitor</code>.
     * 
     * @param visitor a <code>Visitor</code> that the <code>label</code> of
     *          <code>this Question</code> should <code>accept</code>
     */
    protected void labelAccept(Visitor visitor) {
        label.accept(visitor);
    }
    
    /**
     * Tells whether <code>this Question</code> is computed (or should be
     * anwered by the user).
     * 
     * @return <code>true</code> if and only if <code>calculation != null</code>
     */
    public boolean isComputed() {
        return calculation != null;
    }
    
    /**
     * Has the <code>calculation</code> of <code>this Question accept visitor</code>
     * iff. it is not <code>null</code>.
     * 
     * @param visitor a <code>Visitor</code> that the <code>calculation</code> of
     *          <code>this Question</code> should <code>accept</code>
     */
    public void calculationAccept(Visitor visitor) {
        if (calculation != null) {
            calculation.accept(visitor);
        }
    }
    
    /**
     * Determines whether <code>this Question</code> is of the same type as
     * another <code>Question</code>.
     * 
     * @param other a <code>Question</code> to compare
     *              <code>this Question</code> with
     * @return <code>true</code> if and only if <code>this Question</code> and
     *          <code>other</code> are objects of the same class
     */
    public boolean hasEqualType(Question other) {
        return getClass() == other.getClass();
    }
    
    /**
     * Returns whether <code>this Question</code> returns a boolean value.
     * 
     * @return <code>false</code> by default, should be overwritten apropriatly
     *          in subclasses returning booleans
     */
    public boolean isBoolean(){
        return false;
    }
    
    /**
     * Returns whether <code>this Question</code> returns a decimal value.
     * 
     * @return <code>false</code> by default, should be overwritten apropriatly
     *          in subclasses returning decimals
     */
    public boolean isDecimal() {
        return false;
    }
    
    /**
     * Returns whether <code>this Question</code> returns an integer value.
     * 
     * @return <code>false</code> by default, should be overwritten apropriatly
     *          in subclasses returning integers
     */
    public boolean isInt() {
        return false;
    }
    
    /**
     * Returns whether <code>this Question</code> returns a money value.
     * 
     * @return <code>false</code> by default, should be overwritten apropriatly
     *          in subclasses returning money
     */
    public boolean isMoney() {
        return false;
    }
    
    /**
     * Returns whether <code>this Question</code> returns a string value.
     * 
     * @return <code>false</code> by default, should be overwritten apropriatly
     *          in subclasses returning strings
     */
    public boolean isString() {
        return false;
    }
    
    /**
     * Make a specified <code>DisplayableQuestionGenerator</code> create a
     * <code>DisplayableQuestion</code> for <code>this Question</code>.
     * 
     * @param generator a <code>DisplayableQuestionGenerator</code> that should
     *                  create a <code>DisplayableQuestion</code> for
     *                  <code>this Question</code>
     * @return a <code>DisplayableQuestion</code> representing
     *          <code>this Question</code>, as created by a call to
     *          <code>generator</code>'s <code>createDisplayableQuestion</code>
     *          method for the type of <code>Question</code>
     *          <code>this Question</code> is of
     */
    public abstract DisplayableQuestion createDisplayableQuestion(
            DisplayableQuestionGenerator generator);
    
    /**
     * Compares <code>this Question</code> to another <code>Object</code>. A
     * <code>Question</code> is considered equal only to other objects of the
     * same class for which <code>theIdentifier</code>, <code>theLabel</code>
     * and <code>theCalculation</code> are equal to its own values for these
     * fields.
     * 
     * @param o the <code>Object</code> to compare to <code>this Question</code>
     * @return <code>true</code> if and only if o is equal to <code>this Question</code> 
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        
        Question other = (Question) o;
        return identifier.equals(other.identifier)
               && label.equals(other.label)
               && (calculation == null ? other.calculation == null : calculation.equals(other.calculation));
    }
    
    /**
     * @return an <code>int</code> containing a hash for <code>this Question</code>
     */
    @Override
    public int hashCode() {
        int hash = HASH_ORIGIN;
        hash = HASH_FACTOR * hash + identifier.hashCode();
        hash = HASH_FACTOR * hash + label.hashCode();
        hash = HASH_FACTOR * hash + Objects.hashCode(this.calculation);
        return hash;
    }
    
}
