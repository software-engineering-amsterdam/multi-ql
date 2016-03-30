package nl.uva.sea.ql.interpreter.questionComponent;

import java.awt.Component;
import java.beans.PropertyChangeEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;
import lombok.experimental.Delegate;
import nl.uva.sea.ql.answerTable.*;
import nl.uva.sea.ql.ast.expr.Expr;
import nl.uva.sea.ql.ast.question.DateQuestion;

/**
 * TODO document
 * 
 * @author Olav Trauschke
 * @version 29-mrt-2016
 */
public class DateQuestionComponent extends JPanel
        implements ConcreteQuestionComponent {
    
    /**
     * TODO document
     */
    public static final int NUMBER_OF_COLUMNS = 10;
    
    /**
     * TODO document
     */
    public static final String DATE_FORMAT = "d-M-yyyy";
    
    @Delegate(types=QuestionComponent.class)
    private final BasicQuestionComponent basicQuestion;
    
    private final JLabel label;
    private final JFormattedTextField textField;
    
    /**
     * Constructor for objects of this class.
     * 
     * @param conditionForDisplay an <code>Expr</code> defining when the constructed
     *                              <code>DateQuestionComponent</code>
     *                              should be displayed
     * @param theQuestion a <code>DateQuestion</code> that should be displayed
     *                      when <code>conditionToDisplay</code> evaluates to
     *                      <code>true</code>
     * @param theAnswerTable an <code>AnswerTable</code> mapping all
     *                      <code>Ident</code>s <code>conditionToDisplay</code>
     *                      or <code>theCalculation</code> of <code>theQuestion</code>
     *                      could contain to their current <code>Value</code>s,
     *                      or <code>null</code> when these are unknown
     */
    public DateQuestionComponent(Expr conditionForDisplay,
            DateQuestion theQuestion, AnswerTable theAnswerTable) {
        assert conditionForDisplay != null;
        assert theQuestion != null;
        assert theAnswerTable != null;
        label = new JLabel(theQuestion.obtainLabelString());
        textField = new JFormattedTextField(new SimpleDateFormat(DATE_FORMAT));
        textField.setColumns(NUMBER_OF_COLUMNS);
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
    public void setValue(PropertyChangeEvent e) {
        assert e != null;
        Date date = (Date) textField.getValue();
        DateValue newValue = new DateValue(date);
        basicQuestion.setValue(newValue, false);
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
    
    /**
     * TODO document
     * 
     * @param newValue 
     */
    @Override
    public void displayValue(Value newValue) {
        assert newValue instanceof DateValue;
        DateValue dateValue = (DateValue) newValue;
        Date valueAsDate = dateValue.getValue();
        textField.setValue(valueAsDate);
    }
    
}
