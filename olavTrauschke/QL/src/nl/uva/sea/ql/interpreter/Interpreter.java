package nl.uva.sea.ql.interpreter;

import java.awt.AWTEvent;
import java.util.List;
import java.util.function.Consumer;
import nl.uva.sea.ql.answerTable.AnswerTable;
import nl.uva.sea.ql.ast.Form;
import nl.uva.sea.ql.ast.expr.Ident;
import nl.uva.sea.ql.generalPurposeVisitors.QuestionIdentCollector;

/**
 * Class for interpretating ast's.
 * 
 * @author Olav Trauschke
 * @version 26-mar-2016
 */
public class Interpreter {
    
    private final AnswerTable answerTable;
    private final GUI gui;
    
    private Consumer<AnswerTable> callback;
    
    /**
     * Constructor for objects of this class.
     * 
     * @param form the root of the ast the constructed <code>Interpreter</code>
     *              should interpret
     */
    public Interpreter(Form form) {
        QuestionIdentCollector identCollector = new QuestionIdentCollector();
        form.accept(identCollector);
        Iterable<Ident> identifiers = identCollector.obtainIdentifiers();
        answerTable = new AnswerTable(identifiers);
        DisplayableQuestionGenerator generator
                = new DisplayableQuestionGenerator(answerTable);
        form.accept(generator);
        List<BasicDisplayableQuestion> questions = generator.getResult();
        questions.forEach(answerTable::addObserver);
        gui = new GUI(form.obtainIdentifier(), questions);
    }
    
    /**
     * Display the GUI to the user.
     * 
     * @param theCallback a <code>Consumer</code> of <code>AnswerTable</code>s that
     *                      should be called with the resulting <code>AnswerTable</code>
     *                      as argument when the questionnaire has finished, should
     *                      at least properly shutdown the application
     */
    public void run(Consumer<AnswerTable> theCallback) {
        assert theCallback != null;
        callback = theCallback;
        gui.run(this::callback);
    }
    
    /**
     * Call <code>theCallback</code> that was provided to the last call to
     * {@link #run(java.util.function.Consumer) run(Consumer)} with the
     * <code>AnswerTable</code> used by <code>this Interpreter</code> as argument.
     * 
     * @param event the <code>AWTEvent</code> that was the reason for calling this
     *              method, unused
     */
    public void callback(AWTEvent event) {
        assert callback != null;
        callback.accept(answerTable);
    }
    
    /**
     * @return the <code>AnswerTable</code> used by <code>this Interpreter</code>
     */
    public AnswerTable getAnswerTable() {
        return answerTable;
    }
    
}
