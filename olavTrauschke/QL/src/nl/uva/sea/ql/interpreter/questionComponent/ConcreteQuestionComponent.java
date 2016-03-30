package nl.uva.sea.ql.interpreter.questionComponent;

import nl.uva.sea.ql.answerTable.Value;

/**
 * TODO document
 * 
 * @author Olav Trauschke
 * @version 29-mar-2016
 */
public interface ConcreteQuestionComponent extends QuestionComponent {
    
    /**
     * TODO document
     * 
     * @param newValue 
     */
    void displayValue(Value newValue);
    
}
