package nl.uva.sea.ql.interpreter;

import nl.uva.sea.ql.interpreter.questionComponent.QuestionComponent;
import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 * Objects of this class are <code>JPanel</code>s that can be used to display
 * <code>QuestionComponent</code>s.
 * 
 * @author Olav Trauschke
 * @version 28-mar-2016
 */
public class QuestionPanel extends JPanel {
    
    /**
     * Constructor for objects of this class. Creates a new <code>JPanel</code>
     * that <code>isDoubleBuffered</code>, because it may be updated often.
     */
    public QuestionPanel() {
        super(true);
    }
    
    /**
     * Add a specified <code>QuestionComponent</code> to
     * <code>this QuestionPanel</code>.
     * 
     * @param toAdd a <code>QuestionComponent</code> to add to
     *              <code>this QuestionPanel</code>
     */
    public void add(QuestionComponent toAdd) {
        assert toAdd != null;
        add((JComponent) toAdd);
    }
    
}
