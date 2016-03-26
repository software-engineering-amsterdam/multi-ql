package nl.uva.sea.ql.interpreter.listener;

import java.util.EventListener;

/**
 * Interface to enable objects to listen to
 * {@link nl.uva.sea.ql.interpreter.listener.DisplayableQuestionChangeEvent
 * DisplayableQuestionChangeEvent}s.
 * 
 * @author Olav Trauschke
 * @version 26-mrt-2016
 */
public interface DisplayableQuestionListener extends EventListener {
    
    /**
     * Method that is called when a
     * <code>DisplayableQuestion this DisplayableQuestionListener</code> is
     * listening to changed.
     * 
     * @param event a <code>DisplayableQuestionChangeEvent</code> specifying
     *              which <code>DisplayableQuestion</code> changed (as its
     *              <code>source</code>) and whether or not whether that
     *              <code>DisplayableQuestion</code> should be displayed has
     *              changed
     */
    public void questionChanged(DisplayableQuestionChangeEvent event);
    
}
