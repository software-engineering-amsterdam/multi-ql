package nl.uva.sea.ql.interpreter;

import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import lombok.experimental.Delegate;
import nl.uva.sea.ql.answerTable.*;
import nl.uva.sea.ql.ast.expr.Expr;
import nl.uva.sea.ql.ast.question.Question;

/**
 * TODO document
 * 
 * @author Olav Trauschke
 * @version 28-mrt-2016
 */
public class DisplayableBooleanQuestion extends JCheckBox
        implements DisplayableQuestion{
    
    @Delegate(types=DisplayableQuestion.class)
    private final BasicDisplayableQuestion basicQuestion;
    
    /**
     * Constructor for objects of this class.
     * 
     * @param conditionForDisplay an <code>Expr</code> defining when the constructed
     *                              <code>DisplayableBooleanQuestion</code>
     *                              should be displayed
     * @param theQuestion a <code>Question</code> that should be displayed when
     *                      <code>conditionToDisplay</code> evaluates to
     *                      <code>true</code>
     * @param theAnswerTable an <code>AnswerTable</code> mapping all
     *                      <code>Ident</code>s <code>conditionToDisplay</code>
     *                      or <code>theCalculation</code> of <code>theQuestion</code>
     *                      could contain to their current <code>Value</code>s,
     *                      or <code>null</code> when these are unknown
     */
    public DisplayableBooleanQuestion(Expr conditionForDisplay,
            Question theQuestion, AnswerTable theAnswerTable) {
        super(theQuestion.obtainLabelString());
        addActionListener(this::setValue);
        basicQuestion = new BasicDisplayableQuestion(conditionForDisplay, theQuestion, theAnswerTable);
        setHorizontalTextPosition(SwingConstants.LEFT);
    }
    
    /**
     * TODO document
     * 
     * @param e 
     */
    public void setValue(ActionEvent e) {
        boolean selected = ((JCheckBox) e.getSource()).isSelected();
        setValue(new BooleanValue(selected));
    }
    
    /**
     * TODO document
     * 
     * @param newValue
     */
    private void setValue(BooleanValue newValue) {
        basicQuestion.setValue(newValue);
        setSelected(newValue == null ? false : newValue.getValue());
    }
    
}
