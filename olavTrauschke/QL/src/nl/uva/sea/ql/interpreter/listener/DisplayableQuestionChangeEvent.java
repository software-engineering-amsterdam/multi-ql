package nl.uva.sea.ql.interpreter.listener;

import java.util.EventObject;
import nl.uva.sea.ql.interpreter.DisplayableQuestion;

/**
 * Objects of this class represent changes to
 * {@link nl.uva.sea.ql.interpreter.DisplayableQuestion DisplayableQuestion}s.
 * 
 * @author Olav Trauschke
 * @version 26-mrt-2016
 */
public class DisplayableQuestionChangeEvent extends EventObject {
    
    private final boolean displayChanged;
    
    /**
     * Constructor for objects of this class.
     * 
     * @param source a <code>DisplayableQuestion</code> representing the source
     *                  of the <code>DisplayableQuestionChangedEvent</code> to
     *                  construct, i.e. the changed <code>DisplayableQuestion</code>
     * @param toDisplayChanged a <code>boolean</code> telling whether or not
     *                          whether <code>source</code> should be displayed
     *                          has changed
     */
    public DisplayableQuestionChangeEvent(DisplayableQuestion source,
            boolean toDisplayChanged) {
        super(source);
        displayChanged = toDisplayChanged;
    }
    
    /**
     * @return whether or not whether <code>source</code> should be displayed
     *          was changed by <code>this DisplayableQuestionChangedEvent</code>
     */
    public boolean toDisplayChanged() {
        return displayChanged;
    }
    
}
