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
 * @version 24-mar-2016
 */
public class Interpreter {
    
    private final AnswerTable symbolTable;
    
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
        symbolTable = new AnswerTable(identifiers);
        QuestionGeneratingVisitor generator = new QuestionGeneratingVisitor();
        form.accept(generator);
        List<Question> questions = generator.getResult();
        //TODO Make these objects observe the AnswerTable
    }
    
    /**
     * Display the GUI to the user.
     */
    public void run() {
        //TODO run GUI
    }
    
    /**
     * @return the <code>SymbolTable</code> used by <code>this Interpreter</code>
     */
    public AnswerTable getSymbolTable() {
        return symbolTable;
    }
    
}
