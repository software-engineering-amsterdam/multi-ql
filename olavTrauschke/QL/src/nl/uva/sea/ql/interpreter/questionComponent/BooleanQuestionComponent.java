package nl.uva.sea.ql.interpreter.questionComponent;

import nl.uva.sea.ql.interpreter.questionComponent.BasicQuestionComponent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import lombok.experimental.Delegate;
import nl.uva.sea.ql.answerTable.*;
import nl.uva.sea.ql.ast.expr.Expr;
import nl.uva.sea.ql.ast.question.Question;

/**
 * Objects of this class are <code>JCheckBox</code>es that are used to display
 * <code>BooleanQuestion</code>s.
 * 
 * @author Olav Trauschke
 * @version 29-mar-2016
 */
public class BooleanQuestionComponent extends JCheckBox
        implements QuestionComponent{
    
    @Delegate(types=QuestionComponent.class)
    private final BasicQuestionComponent basicQuestion;
    
    /**
     * Constructor for objects of this class.
     * 
     * @param conditionForDisplay an <code>Expr</code> defining when the constructed
     *                              <code>BooleanQuestionComponent</code>
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
    public BooleanQuestionComponent(Expr conditionForDisplay,
            Question theQuestion, AnswerTable theAnswerTable) {
        super(theQuestion.obtainLabelString());
        assert conditionForDisplay != null;
        assert theAnswerTable != null;
        addActionListener(this::setValue);
        basicQuestion = new BasicQuestionComponent(conditionForDisplay,
                theQuestion, theAnswerTable, this);
        setHorizontalTextPosition(SwingConstants.LEFT);
    }
    
    /**
     * Set the value of <code>this BooleanQuestionComponent</code>'s
     * <code>question</code> to the value represented by the value of the
     * <code>source</code> of an <code>ActionEvent</code>.
     * 
     * @param e an <code>ActionEvent</code> that changes the value of
     *          <code>this BooleanQuestionComponent</code>
     */
    public void setValue(ActionEvent e) {
        assert e != null;
        boolean selected = ((JCheckBox) e.getSource()).isSelected();
        setValue(new BooleanValue(selected));
    }
    
    /**
     * Defined by {@link javax.swing.AbstractButton#addActionListener(java.awt.event.ActionListener)
     * AbstractButton.addActionListener(ActionListener)}, declared as final here
     * for use in constructor.
     * 
     * @param l an <code>ActionListener</code> that needs to listen to
     *          <code>this BooleanQuestionComponent</code>
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
     * Set the value of <code>this BooleanQuestionComponent</code>.
     * 
     * @param newValue a <code>BooleanValue</code> repersenting a new
     *                  <code>Value</code> for
     *                  <code>this BooleanQuestionComponent</code>
     */
    private void setValue(BooleanValue newValue) {
        assert newValue != null;
        basicQuestion.setValue(newValue);
        setSelected(newValue == null ? false : newValue.getValue());
    }
    
}
