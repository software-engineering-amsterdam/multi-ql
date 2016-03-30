package nl.uva.sea.ql.interpreter.questionComponent;

import java.util.EventListener;

/**
 * Interface to enable objects to listen to
 * {@link nl.uva.sea.ql.interpreter.questionComponent.QuestionComponentChangeEvent
 * QuestionComponentChangeEvent}s.
 * 
 * @author Olav Trauschke
 * @version 26-mar-2016
 */
public interface QuestionComponentListener extends EventListener {
    
    /**
     * Method that is called when a
     * <code>QuestionComponent this QuestionComponentListener</code> is
     * listening to changed.
     * 
     * @param event a <code>QuestionComponentChangeEvent</code> specifying
     *              which <code>QuestionComponent</code> changed (as its
     *              <code>source</code>) and whether or not whether that
     *              <code>QuestionComponent</code> should be displayed has
     *              changed
     */
    public void questionChanged(QuestionComponentChangeEvent event);
    
}
