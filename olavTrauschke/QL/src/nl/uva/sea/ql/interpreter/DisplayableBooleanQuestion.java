package nl.uva.sea.ql.interpreter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
 * @version 29-mrt-2016
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
        basicQuestion = new BasicDisplayableQuestion(conditionForDisplay,
                theQuestion, theAnswerTable, this);
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
     * Defined by {@link javax.swing.AbstractButton#addActionListener(java.awt.event.ActionListener)
     * AbstractButton.addActionListener(ActionListener)}, declared as final here
     * for use in constructor.
     * 
     * @param l an <code>ActionListener</code> that needs to listen to
     *          <code>this DisplayableBooleanQuestion</code>
     */
    @Override
    public final void addActionListener(ActionListener l) {
        super.addActionListener(l);
    }
    
    /**
     * Defined by {@link javax.swing.AbstractButton#setHorizontalTextPosition(int)
     * AbstractButton.setHorizontalTextPosition(int)}, declared as final here for
     * use in constructor.
     * 
     * @param newPosition an <code>int</code> representing the new position of
     *                      the text, one of the following:
     *                      <code>SwingConstants.RIGHT</code>,
     *                      <code>SwingConstants.LEFT</code>,
     *                      <code>SwingConstants.CENTER</code>,
     *                      <code>SwingConstants.LEADING</code> or
     *                      <code>SwingConstants.TRAILING</code> (default)
     */
    @Override
    public final void setHorizontalTextPosition(int newPosition) {
        super.setHorizontalTextPosition(newPosition);
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
