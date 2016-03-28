package nl.uva.sea.ql.interpreter;

import java.util.Observer;
import nl.uva.sea.ql.answerTable.Value;
import nl.uva.sea.ql.ast.expr.Ident;
import nl.uva.sea.ql.interpreter.listener.DisplayableQuestionListener;

/**
 * TODO document
 * N.B. <code>DisplayableQuestions</code> must be <code>JComponent</code>s.
 * 
 * @author Olav Trauschke
 * @version 28-mrt-2016
 */
public interface DisplayableQuestion extends Observer {
    
    /**
     * TODO document
     * 
     * @return 
     */
    boolean isToDisplay();
    
    /**
     * TODO document
     * 
     * @param listener 
     */
    void addListener(DisplayableQuestionListener listener);
    
    /**
     * TODO document
     * 
     * @return 
     */
    Ident getIdentifier();
    
    /**
     * TODO document
     * 
     * @return 
     */
    Value eval();
    
}
