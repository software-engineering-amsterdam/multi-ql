package nl.uva.sea.ql.interpreter;

import java.util.*;
import nl.uva.sea.ql.answerTable.AnswerTable;
import nl.uva.sea.ql.ast.expr.*;
import nl.uva.sea.ql.ast.question.*;
import nl.uva.sea.ql.generalPurposeVisitors.GeneralizedASTVisitor;
import nl.uva.sea.ql.interpreter.questionComponent.*;

/**
 * Objects of this class keep track of conditions for each
 * <code>ConditionalStatement</code> and create <code>QuestionComponent</code>s
 * with these conditions for each <code>Question</code> they <code>visit</code>.
 * 
 * @author Olav Trauschke
 * @version 30-mar-2016
 */
public class QuestionComponentGenerator extends GeneralizedASTVisitor {
    
    private final AnswerTable answerTable;
    private final Stack<Expr> conditions;
    private final List<QuestionComponent> result;
    
    /**
     * Constructor for objects of this class.
     * 
     * @param theAnswerTable an <code>AnswerTable</code> mapping all
     *                      <code>Ident</code>s <code>Question</code>s or
     *                      <code>ConditionalStatement</code>s
     *                      <code>this QuestionComponentGenerator</code> may
     *                      visit may contain
     */
    protected QuestionComponentGenerator(AnswerTable theAnswerTable) {
        assert theAnswerTable != null;
        answerTable = theAnswerTable;
        conditions = new Stack<>();
        result = new ArrayList<>();
    }
    
    /**
     * Create a <code>QuestionComponent</code> for a specified
     * <code>BooleanQuestion</code>.
     * 
     * @param question a <code>BooleanQuestion</code> to create a
     * <code>QuestionComponent</code> for
     * @return a <code>new BooleanQuestionComponent</code> representing the
     *          <code>question</code> to be displayed only when all
     *          conditions in <code>this QuestionComponentGenerator</code> are
     *          met and using <code>theAnswerTable</code> that was given to the
     *          constructor when constructing
     *          <code>this QuestionComponentGenerator</code>
     */
    public BooleanQuestionComponent createQuestionComponent(BooleanQuestion question) {
        Expr condition = createConjunctionOfConditions();
        return new BooleanQuestionComponent(condition, question, answerTable);
    }
    
    /**
     * TODO document
     * 
     * @param question
     * @return 
     */
    public DateQuestionComponent createQuestionComponent(DateQuestion question) {
        Expr condition = createConjunctionOfConditions();
        return new DateQuestionComponent(condition, question, answerTable);
    }
    
    /**
     * TODO document
     * 
     * @param question
     * @return 
     */
    public DecimalQuestionComponent createQuestionComponent(DecimalQuestion question) {
        Expr condition = createConjunctionOfConditions();
        return new DecimalQuestionComponent(condition, question, answerTable);
    }
    
    /**
     * TODO document
     * 
     * @param question
     * @return 
     */
    public IntQuestionComponent createQuestionComponent(IntQuestion question) {
        Expr condition = createConjunctionOfConditions();
        return new IntQuestionComponent(condition, question, answerTable);
    }
    
    /**
     * Create a <code>QuestionComponent</code> for a specified
     * <code>StringQuestion</code>.
     * 
     * @param question a <code>StringQuestion</code> to create a
     *                  <code>QuestionComponent</code> for
     * @return a <code>new StringQuestionComponent</code> representing
     *          the <code>question</code> to be displayed only when all conditions
     *          in <code>this QuestionComponentGenerator</code> are met and using
     *          <code>theAnswerTable</code> that was given to the constructor
     *          when constructing <code>this QuestionComponentGenerator</code>
     */
    public StringQuestionComponent createQuestionComponent(StringQuestion question) {
        Expr condition = createConjunctionOfConditions();
        return new StringQuestionComponent(condition, question, answerTable);
    }
    
    /**
     * Create a <code>QuestionComponent</code> for a specified <code>Question</code>
     * with the conjuction of all available conditions as
     * <code>displayCondition</code>.
     * 
     * @param question a <code>Question</code> to create a
     *                  <code>BasicQuestionComponent</code> for
     */
    @Override
    public void visit(Question question) {
        QuestionComponent questionComponent
                = question.createQuestionComponent(this);
        result.add(questionComponent);
    }
    
    /**
     * Add a condition to <code>this QuestionComponentGenerator</code>.
     * 
     * @param condition an <code>Expr</code> representing the condition to add
     */
    public void addCondition(Expr condition) {
        assert condition != null;
        conditions.push(condition);
    }
    
    /**
     * Remove the last condition that was added to it from
     * <code>this QuestionComponentGenerator</code>.
     */
    public void removeLastCondition() {
        conditions.pop();
    }
    
    /**
     * Returns a <code>List</code> of <code>QuestionComponent</code>s representing
     * all <code>Question</code>s <code>this QuestionComponentGenerator</code>
     * has <code>visit</code>ed.
     * 
     * @return a <code>List</code> of all <code>QuestionComponents</code>
     *          generated by <code>this QuestionComponentGenerator</code>
     */
    public List<QuestionComponent> getResult() {
        return result;
    }
    
    /**
     * @return an <code>Expr</code> representing the conjunction of all
     *          conditions that were add to (and not removed from)
     *          <code>this QuestionComponentGenerator</code>
     */
    private Expr createConjunctionOfConditions() {
        Expr top = new Bool(true);
        return conditions.stream().reduce(top, And::new);
    }
    
}
