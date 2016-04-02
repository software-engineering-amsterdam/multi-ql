package nl.uva.sea.ql.interpreter.questionComponent;

import java.awt.Component;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import lombok.experimental.Delegate;
import nl.uva.sea.ql.answerTable.AnswerTable;
import nl.uva.sea.ql.answerTable.StringValue;
import nl.uva.sea.ql.answerTable.Value;
import nl.uva.sea.ql.ast.expr.Expr;
import nl.uva.sea.ql.ast.question.StringQuestion;

/**
 * Objects of this class are <code>JPanel</code>s containing a <code>JLabel</code>
 * and a <code>JTextField</code> that are used to display <code>StringQuestion</code>s.
 * 
 * @author Olav Trauschke
 * @version 1-apr-2016
 */
public class StringQuestionComponent extends JPanel
        implements ConcreteQuestionComponent {
    
    /**
     * An <code>int</code> expressing the number of characters that fit in
     * a <code>StringQuestionComponent</code>.
     */
    public static final int NUMBER_OF_COLUMNS = 30;
    
    @Delegate(types=QuestionComponent.class)
    private final BasicQuestionComponent basicQuestion;
    
    private final JLabel label;
    private final JTextField textField;
    
    /**
     * Constructor for objects of this class.
     * 
     * @param conditionForDisplay an <code>Expr</code> defining when the constructed
     *                              <code>StringQuestionComponent</code>
     *                              should be displayed
     * @param theQuestion a <code>StringQuestion</code> that should be displayed
     *                      when <code>conditionToDisplay</code> evaluates to
     *                      <code>true</code>
     * @param theAnswerTable an <code>AnswerTable</code> mapping all
     *                      <code>Ident</code>s <code>conditionToDisplay</code>
     *                      or <code>theCalculation</code> of <code>theQuestion</code>
     *                      could contain to their current <code>Value</code>s,
     *                      or <code>null</code> when these are unknown
     */
    public StringQuestionComponent(Expr conditionForDisplay,
            StringQuestion theQuestion, AnswerTable theAnswerTable) {
        assert conditionForDisplay != null;
        assert theQuestion != null;
        assert theAnswerTable != null;
        label = new JLabel(theQuestion.obtainLabelString());
        textField = new JTextField(NUMBER_OF_COLUMNS);
        add(label);
        add(textField);
        textField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                //not usefull in this context
            }
            
            @Override
            public void insertUpdate(DocumentEvent e) {
                setValue(e);
            }
            
            @Override
            public void removeUpdate(DocumentEvent e) {
                setValue(e);
            }
        });
        basicQuestion = new BasicQuestionComponent(conditionForDisplay,
                theQuestion, theAnswerTable, this);
        if (theQuestion.isComputed()) {
            textField.setEnabled(false);
        }
    }
    
    /**
     * Set the value of <code>this StringQuestionComponent</code>'s
     * <code>question</code> to the value currently written in its
     * <code>textField</code>.
     * 
     * @param e an <code>ActionEvent</code> that changes the value of
     *          <code>this StringQuestionComponent</code>
     */
    public void setValue(DocumentEvent e) {
        assert e != null;
        String text = textField.getText();
        if (text.equals("")) {
            text = null;
        }
        StringValue newValue = new StringValue(text);
        basicQuestion.setValue(newValue, false);
    }
    
    /**
     * Make <code>this StringQuestionComponent</code> display a specified
     * <code>Value</code>.
     * 
     * @param newValue a <code>Value this StringQuestionComponent</code> should
     *                  display
     */
    @Override
    public void displayValue(Value newValue) {
        assert newValue instanceof StringValue;
        StringValue stringValue = (StringValue) newValue;
        String valueAsString = stringValue.getValue();
        if (valueAsString == null) {
            valueAsString = "";
        }
        textField.setText(valueAsString);
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
