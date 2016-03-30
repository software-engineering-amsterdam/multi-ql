package nl.uva.sea.ql.interpreter.questionComponent;

import java.util.Observer;
import nl.uva.sea.ql.answerTable.Value;
import nl.uva.sea.ql.ast.expr.Ident;
import nl.uva.sea.ql.interpreter.questionComponent.QuestionComponentListener;

/**
 * <code>QuestionComponent</code>s are objects that can be used to display
 * <code>Question</code>s. N.B. <code>QuestionComponent</code>s must be
 * <code>JComponent</code>s.
 * 
 * @author Olav Trauschke
 * @version 29-mar-2016
 */
public interface QuestionComponent extends Observer {
    
    /**
     * @return a <code>boolean</code> telling whether or not
     *          <code>this QuestionComponent</code> should currently be displayed
     */
    boolean isToDisplay();
    
    /**
     * Make a specified <code>QuestionComponentListener</code> listen to
     * <code>this QuestionComponent</code>.
     * 
     * @param listener a <code>QuestionComponentListener</code> that should listen
     *                  to <code>this QuestionComponent</code>
     */
    void addListener(QuestionComponentListener listener);
    
    /**
     * @return an <code>Ident</code> that is <code>theIdentifier</code> of the
     * <code>Question this QuestionComponent</code> represents.
     */
    Ident getIdentifier();
    
    /**
     * Evaluate <code>theCalculation</code> of the
     * <code>Question this QuestionComponent</code> represents.
     * 
     * @return a <code>Value</code> representing the current value of the
     *          <code>Question this QuestionComponent</code> represents
     */
    Value evalCalculation();
    
}
