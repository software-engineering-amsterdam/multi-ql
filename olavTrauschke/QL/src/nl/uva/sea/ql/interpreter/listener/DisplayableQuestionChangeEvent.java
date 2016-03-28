package nl.uva.sea.ql.interpreter.listener;

import java.util.EventObject;
import nl.uva.sea.ql.interpreter.BasicDisplayableQuestion;

/**
 * Objects of this class represent changes to
 * {@link nl.uva.sea.ql.interpreter.BasicDisplayableQuestion BasicDisplayableQuestion}s.
 * 
 * @author Olav Trauschke
 * @version 26-mrt-2016
 */
public class DisplayableQuestionChangeEvent extends EventObject {
    
    private final boolean displayChanged;
    
    /**
     * Constructor for objects of this class.
     * 
     * @param source a <code>BasicDisplayableQuestion</code> representing the source
     *                  of the <code>DisplayableQuestionChangedEvent</code> to
     *                  construct, i.e. the changed <code>BasicDisplayableQuestion</code>
     * @param toDisplayChanged a <code>boolean</code> telling whether or not
     *                          whether <code>source</code> should be displayed
     *                          has changed
     */
    public DisplayableQuestionChangeEvent(BasicDisplayableQuestion source,
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
