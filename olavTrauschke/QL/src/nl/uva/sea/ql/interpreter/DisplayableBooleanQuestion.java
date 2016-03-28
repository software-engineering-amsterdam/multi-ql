package nl.uva.sea.ql.interpreter;

import javax.swing.JCheckBox;
import lombok.experimental.Delegate;
import nl.uva.sea.ql.answerTable.AnswerTable;
import nl.uva.sea.ql.ast.expr.Expr;
import nl.uva.sea.ql.ast.question.Question;

/**
 * TODO document
 * 
 * @author Olav Trauschke
 * @version 28-mrt-2016
 */
public class DisplayableBooleanQuestion extends DisplayableQuestion {
    
    @Delegate
    private final JCheckBox widget;
    
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
        super(conditionForDisplay, theQuestion, theAnswerTable);
        widget = new JCheckBox(theQuestion.obtainLabelString());
    }
    
}
