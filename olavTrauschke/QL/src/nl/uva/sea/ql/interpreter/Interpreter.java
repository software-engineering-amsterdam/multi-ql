package nl.uva.sea.ql.interpreter;

import java.util.List;
import nl.uva.sea.ql.QuestionIdentCollector;
import nl.uva.sea.ql.ast.Form;
import nl.uva.sea.ql.ast.expr.Ident;
import nl.uva.sea.ql.answerTable.AnswerTable;

/**
 * Class for interpretating ast's.
 * 
 * @author Olav Trauschke
 * @version 25-mar-2016
 */
public class Interpreter {
    
    private final AnswerTable answerTable;
    
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
        questions.forEach((DisplayableQuestion q) -> answerTable.addObserver(q));
        //TODO create GUI
    }
    
    /**
     * Display the GUI to the user.
     */
    public void run() {
        //TODO run GUI
    }
    
    /**
     * @return the <code>AnswerTable</code> used by <code>this Interpreter</code>
     */
    public AnswerTable getSymbolTable() {
        return answerTable;
    }
    
}
