package nl.uva.sea.ql.interpreter.listener;

import java.util.EventListener;

/**
 * TODO document
 * 
 * @author Olav Trauschke
 * @version 26-mrt-2016
 */
public interface DisplayableQuestionListener extends EventListener {
    
    /**
     * TODO document
     * 
     * @param event
     */
    public void questionChanged(DisplayableQuestionChangeEvent event);
    
}
