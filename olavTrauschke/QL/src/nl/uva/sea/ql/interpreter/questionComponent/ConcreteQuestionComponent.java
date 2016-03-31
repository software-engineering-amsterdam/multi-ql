package nl.uva.sea.ql.interpreter.questionComponent;

import nl.uva.sea.ql.answerTable.Value;

/**
 * Interface declaring the minimum functionality of a <code>QuestionComponent</code>
 * other than a <code>BasicQuestionComponent</code>, which should only be used
 * by <code>ConcreteQuestionComponents</code>.
 * 
 * @author Olav Trauschke
 * @version 30-mar-2016
 */
public interface ConcreteQuestionComponent extends QuestionComponent {
    
    /**
     * Make <code>this ConcreteQuestionComponent</code> display a specified
     * <code>Value</code>.
     * 
     * @param newValue a <code>Value this ConcreteQuestionComponent</code> should
     *                  display
     */
    void displayValue(Value newValue);
    
}
