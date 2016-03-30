package nl.uva.sea.ql.interpreter.questionComponent;

import java.util.EventObject;

/**
 * Objects of this class represent changes to
 * {@link nl.uva.sea.ql.interpreter.questionComponent.BasicQuestionComponent BasicQuestionComponent}s.
 * 
 * @author Olav Trauschke
 * @version 26-mar-2016
 */
public class QuestionComponentChangeEvent extends EventObject {
    
    private final boolean displayChanged;
    
    /**
     * Constructor for objects of this class.
     * 
     * @param source a <code>BasicQuestionComponent</code> representing the source
     *                  of the <code>QuestionComponentChangeEvent</code> to
     *                  construct, i.e. the changed <code>QuestionComponent</code>
     * @param toDisplayChanged a <code>boolean</code> telling whether or not
     *                          whether <code>source</code> should be displayed
     *                          has changed
     */
    public QuestionComponentChangeEvent(QuestionComponent source,
            boolean toDisplayChanged) {
        super(source);
        displayChanged = toDisplayChanged;
    }
    
    /**
     * @return whether or not whether <code>source</code> should be displayed
     *          was changed by <code>this QuestionComponentChangeEvent</code>
     */
    public boolean toDisplayChanged() {
        return displayChanged;
    }
    
}
