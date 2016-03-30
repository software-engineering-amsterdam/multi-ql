package nl.uva.sea.ql.interpreter.questionComponent;

import java.awt.Component;
import java.beans.PropertyChangeEvent;
import java.text.Format;
import javax.swing.*;
import lombok.experimental.Delegate;
import nl.uva.sea.ql.answerTable.AnswerTable;
import nl.uva.sea.ql.answerTable.Value;
import nl.uva.sea.ql.ast.expr.Expr;
import nl.uva.sea.ql.ast.question.Question;

/**
 * TODO document
 * 
 * @author Olav Trauschke
 * @version 30-mar-2016
 */
public abstract class FormattedTextFieldComponent extends JPanel
        implements ConcreteQuestionComponent{
    
    /**
     * TODO document
     */
    public static final String FORMAT_DESCRIPTION_START = " (";
    
    /**
     * TODO document
     */
    public static final String FORMAT_DESCRIPTION_END = ")";
    
    @Delegate(types=QuestionComponent.class)
    private final BasicQuestionComponent basicQuestion;
    
    private final JLabel label;
    private final JFormattedTextField textField;
    
    /**
     * Constructor for objects of this class.
     * 
     * @param conditionForDisplay an <code>Expr</code> defining when the constructed
     *                              <code>FormattedTextFieldComponent</code>
     *                              should be displayed
     * @param theQuestion a <code>Question</code> that should be displayed
     *                      when <code>conditionToDisplay</code> evaluates to
     *                      <code>true</code>
     * @param theAnswerTable an <code>AnswerTable</code> mapping all
     *                      <code>Ident</code>s <code>conditionToDisplay</code>
     *                      or <code>theCalculation</code> of <code>theQuestion</code>
     *                      could contain to their current <code>Value</code>s,
     *                      or <code>null</code> when these are unknown
     * @param format    TODO document
     * @param numberOfColumns   TODO document
     * @param formatDescription TODO document
     */
    public FormattedTextFieldComponent(Expr conditionForDisplay,
            Question theQuestion, AnswerTable theAnswerTable, Format format,
            int numberOfColumns, String formatDescription) {
        assert conditionForDisplay != null;
        assert theQuestion != null;
        assert theAnswerTable != null;
        String labelString = theQuestion.obtainLabelString()
                + FORMAT_DESCRIPTION_START + formatDescription
                + FORMAT_DESCRIPTION_END;
        label = new JLabel(labelString);
        textField = new JFormattedTextField(format);
        textField.setColumns(numberOfColumns);
        add(label);
        add(textField);
        textField.addPropertyChangeListener("value", this::setValue);
        basicQuestion = new BasicQuestionComponent(conditionForDisplay,
                theQuestion, theAnswerTable, this);
        if (theQuestion.isComputed()) {
            textField.setEnabled(false);
        }
    }
    
    /**
     * TODO document
     * 
     * @param e 
     */
    public abstract void setValue(PropertyChangeEvent e);
    
    /**
     * TODO document
     * 
     * @param newValue
     */
    protected void setValue(Value newValue) {
        basicQuestion.setValue(newValue, false);
    }
    
    /**
     * TODO document
     * 
     * @return 
     */
    protected Object getTextFieldValue() {
        return textField.getValue();
    }
    
    /**
     * TODO document
     * 
     * @param newValue 
     */
    protected void setTextFieldValue(Object newValue) {
        textField.setValue(newValue);
    }
    
    /**
     * TODO document
     * 
     * @param comp
     * @return 
     */
    @Override
    public final Component add(Component comp) {
        return super.add(comp);
    }
    
}
