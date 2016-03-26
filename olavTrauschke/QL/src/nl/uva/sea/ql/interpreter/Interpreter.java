package nl.uva.sea.ql.interpreter;

import java.util.List;
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
        List<DisplayableQuestion> questions = generator.getResult();
        questions.forEach(answerTable::addObserver);
        gui = new GUI(form.obtainIdentifier(), questions);
    }
    
    /**
     * Display the GUI to the user.
     */
    public void run() {
        gui.run();
        //Do not return until GUI is close
    }
    
    /**
     * @return the <code>AnswerTable</code> used by <code>this Interpreter</code>
     */
    public AnswerTable getAnswerTable() {
        return answerTable;
    }
    
}
