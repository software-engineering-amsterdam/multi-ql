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
 * Objects of this class represent <code>Question</code>s that can be displayed
 * using <code>JFormattedTextField</code>'s and are <code>JPanel</code>s that
 * contain a <code>JLabel</code> and a <code>JFormattedTextField</code>.
 * 
 * @author Olav Trauschke
 * @version 1-apr-2016
 */
public abstract class FormattedTextFieldComponent extends JPanel
        implements ConcreteQuestionComponent {
    
    /**
     * A <code>String</code> that is appended to the <code>label</code> of a
     * <code>FormattedTextFieldComponent</code> to signal the start of the
     * format description.
     */
    public static final String FORMAT_DESCRIPTION_START = " (";
    
    /**
     * A <code>String</code> that is appended to the <code>label</code> of a
     * <code>FormattedTextField</code> to signal the end of the format description.
     */
    public static final String FORMAT_DESCRIPTION_END = ")";
    
    @Delegate(types=QuestionComponent.class)
    private final BasicQuestionComponent basicQuestion;
    
    private final JLabel label;
    private final JFormattedTextField textField;
    
    /**
     * Constructor for objects of this class.
     * 
     * @param conditionForDisplay an <code>Expr</code> defining when the
     *                              constructed
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
     * @param format    a <code>Format</code> describing the input the constructed
     *                  <code>FormattedTextFieldComponent</code> should accept
     *                  in its <code>textField</code>
     * @param numberOfColumns   the number of characters that should fit in the
     *                          <code>textField</code> of the constructed
     *                          <code>FormattedTextFieldComponent</code>
     * @param formatDescription a <code>String</code> describing
     *                          <code>format</code> to explain it to the user,
     *                          appended to the result of calling
     *                          <code>obtainLabelString</code> on
     *                          <code>theQuestion</code>
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
     * Set the value of <code>this FormattedTextFieldComponent</code>'s
     * <code>question</code> to the value it should currently represent, based
     * on the contents of its <code>textField</code>.
     * 
     * @param e an <code>ActionEvent</code> that changes the value of
     *          <code>this FormattedTextFieldComponent</code>
     */
    public abstract void setValue(PropertyChangeEvent e);
    
    /**
     * Set the value of <code>this FormattedTextFieldComponent</code>'s
     * <code>question</code> to a specified <code>Value</code>.
     * 
     * @param newValue a <code>Value</code> to set the value of 
     *                  <code>this FormattedTextFieldComponent</code>'s
     *                  <code>question</code> to
     */
    protected void setValue(Value newValue) {
        basicQuestion.setValue(newValue, false);
    }
    
    /**
     * Obtain the value <code>this FormattedTextFieldComponent</code>'s
     * <code>textField</code> currently represents.
     * 
     * @return an <code>Object</code> representing the last valid value that was
     *          entered in <code>this FormattedTextFieldComponent</code>'s
     *          <code>textField</code>, the (runtime) type of which is determined
     *          by the <code>format</code> that was passed to the constructor
     *          when <code>this FormattedTextFieldComponent</code> was constructed
     */
    protected Object obtainTextFieldValue() {
        return textField.getValue();
    }
    
    /**
     * @return a <code>String</code> containing the text currently in
     *          <code>this FormattedTextFieldComponent</code>'s
     *          <code>textField</code>
     */
    protected String getTextFieldContents() {
        return textField.getText();
    }
    
    /**
     * Make <code>this FormattedTextFieldComponent</code>'s <code>textField</code>
     * express a specified <code>newValue</code>.
     * 
     * @param newValue an <code>Object</code> expressing a value
     *                  <code>this FormattedTextFieldComponent</code> should
     *                  display in its <code>textField</code>, the (runtime)
     *                  type of which is determined by the <code>format</code>
     *                  that was passed to the constructor when
     *                  <code>this FormattedTextFieldComponent</code> was
     *                  constructed
     */
    protected void setTextFieldValue(Object newValue) {
        textField.setValue(newValue);
    }
    
    /**
     * Defined in {@link java.awt.Container#add(java.awt.Component)
     * Container.add(Component)} and overwritten only to declare final for use
     * in constructor.
     * 
     * @param comp a <code>Component</code> that is passed to
     *              {@link java.awt.Container#add(java.awt.Component)
     *              Container.add(Component)}
     * @return <code>comp</code>
     */
    @Override
    public final Component add(Component comp) {
        return super.add(comp);
    }
    
}
