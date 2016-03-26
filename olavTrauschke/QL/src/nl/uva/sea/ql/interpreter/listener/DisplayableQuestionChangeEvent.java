package nl.uva.sea.ql.interpreter.listener;

import java.util.EventObject;
import nl.uva.sea.ql.interpreter.DisplayableQuestion;

/**
 * TODO document
 * 
 * @author Olav Trauschke
 * @version 26-mrt-2016
 */
public class DisplayableQuestionChangeEvent extends EventObject {
    
    private final boolean displayChanged;
    
    /**
     * TODO document
     * 
     * @param source
     * @param toDisplayChanged
     */
    public DisplayableQuestionChangeEvent(DisplayableQuestion source,
            boolean toDisplayChanged) {
        super(source);
        displayChanged = toDisplayChanged;
    }
    
    /**
     * TODO document
     * 
     * @return 
     */
    public boolean toDisplayChanged() {
        return displayChanged;
    }
    
}
