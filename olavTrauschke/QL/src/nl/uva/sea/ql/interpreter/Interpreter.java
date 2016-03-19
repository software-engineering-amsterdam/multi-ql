package nl.uva.sea.ql.interpreter;

import nl.uva.sea.ql.QuestionIdentCollector;
import nl.uva.sea.ql.ast.Form;
import nl.uva.sea.ql.ast.expr.Ident;
import nl.uva.sea.ql.answerTable.AnswerTable;

/**
 * Class for interpretating ast's.
 * 
 * @author Olav Trauschke
 * @version 19-mar-2016
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
        /*TODO create GeneralizedASTVisitor that keeps track of dependencies in
        ConditionalStatements and have it visit form top-down to create objects
        for all Questions, then have it return these objects. Make these objects
        observe the AnswerTable*/
        //TODO create GUI
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
