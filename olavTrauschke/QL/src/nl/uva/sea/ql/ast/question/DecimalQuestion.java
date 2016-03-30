package nl.uva.sea.ql.ast.question;

import nl.uva.sea.ql.ast.Label;
import nl.uva.sea.ql.ast.expr.Expr;
import nl.uva.sea.ql.ast.expr.Ident;
import nl.uva.sea.ql.generalPurposeVisitors.Visitor;
import nl.uva.sea.ql.interpreter.QuestionComponentGenerator;
import nl.uva.sea.ql.interpreter.questionComponent.QuestionComponent;

/**
 * Representation of <code>Question</code>s that return decimals in an AST.
 * 
 * @author Olav Trauschke
 * @version 30-mar-2016
 */
public class DecimalQuestion extends Question {
    
    /**
     * Constructor for <code>DecimalQuestion</code>s that should be answered by
     * the user.
     * 
     * @param identifier the <code>Ident</code> used to identify the constructed
     *                      <code>DecimalQuestion</code>
     * @param label the <code>Label</code> to display with the constructed
     *              <code>DecimalQuestion</code>
     */
    public DecimalQuestion(Ident identifier, Label label) {
        this (identifier, label, null);
    }
    
    /**
     * Constructor for <code>DecimalQuestion</code>s that should be computed
     * based on the answers to other <code>Question</code>s.
     * 
     * @param identifier the <code>Ident</code> used to identify the constructed
     *                      <code>DecimalQuestion</code>
     * @param label the <code>Label</code> to display with the constructed
     *              <code>DecimalQuestion</code>
     * @param calculation an <code>Expr</code> defining how to compute the value
     *                      of the constructed <code>DecimalQuestion</code>
     */
    public DecimalQuestion(Ident identifier, Label label, Expr calculation) {
        super(identifier, label, calculation);
    }
    
    /**
     * Returns whether <code>this DecimalQuestion</code> represents a decimal value.
     * 
     * @return <code>true</code> because objects of this class represent decimal
     *          values by definition
     */
    @Override
    public boolean isDecimal() {
        return true;
    }
    
    /**
     * Has the children of <code>this Question accept visitor</code> and then
     * has <code>visitor visit this Question</code>.
     * 
     * @param visitor a <code>Visitor</code> that should
     *                  <code>visit this Question</code> and its children
     */
    @Override
    public void accept(Visitor visitor) {
        childrenAccept(visitor);
        visitor.visit(this);
    }
    
    /**
     * TODO document
     * 
     * @param generator
     * @return 
     */
    @Override
    public QuestionComponent createQuestionComponent(QuestionComponentGenerator generator) {
        assert generator != null;
        return generator.createQuestionComponent(this);
    }
    
}
