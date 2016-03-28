package nl.uva.sea.ql.interpreter;

import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 * TODO document
 * 
 * @author Olav Trauschke
 * @version 28-mrt-2016
 */
public class QuestionPanel extends JPanel {
    
    /**
     * TODO document
     */
    public QuestionPanel() {
        super(true);
    }
    
    /**
     * TODO document
     * 
     * @param toAdd
     */
    public void add(DisplayableQuestion toAdd) {
        add((JComponent) toAdd);
    }
    
}
