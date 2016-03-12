package nl.uva.sea.ql.interpreter;

import nl.uva.sea.ql.QuestionIdentCollector;
import nl.uva.sea.ql.ast.Form;
import nl.uva.sea.ql.ast.expr.Ident;
import nl.uva.sea.ql.symbolTable.SymbolTable;

/**
 * Class for interpretating ast's.
 * 
 * @author Olav Trauschke
 * @version 10-mrt-2016
 */
public class Interpreter {
    
    private final SymbolTable symbolTable;
    
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
        symbolTable = new SymbolTable(identifiers);
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
    public SymbolTable getSymbolTable() {
        return symbolTable;
    }
    
}
