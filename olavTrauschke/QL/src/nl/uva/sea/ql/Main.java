package nl.uva.sea.ql;

import java.io.*;
import java.util.*;
import nl.uva.sea.ql.ast.Form;
import nl.uva.sea.ql.ast.expr.Ident;
import nl.uva.sea.ql.ast.question.Question;
import nl.uva.sea.ql.checker.*;
import nl.uva.sea.ql.gui.Questionnaire;
import nl.uva.sea.ql.symbolTable.SymbolTable;
import nl.uva.sea.ql.parser.ParserWrapper;

/**
 * Main class to type check and run questionairs.
 * 
 * @author Olav Trauschke
 * @version 10-mrt-2016
 */
public class Main {
    
    /**
     * Error code representing a syntax error in a read file.
     */
    public static final int SYNTAX_ERROR = 2;
    
    /**
     * Error message presented to the user when a file was found to contain a
     * syntax error.
     */
    public static final String SYNTAX_ERROR_MESSAGE
            = "The selected file contains a syntax error and could not be interpreted";
    
    /**
     * Title of the dialog a <code>SYNTAX_ERROR_MESSAGE</code> is displayed in.
     */
    public static final String SYNTAX_ERROR_TITLE
            = "Syntax error";
    
    /**
     * Error code representing a semantic error in a read file the caused the
     * program to halt.
     */
    public static final int SEMANTICS_ERROR = 3;
    
    private IOManager ioManager;
    
    public Main() {
        ioManager = new IOManager();
        File file = ioManager.selectFileToOpen();
        if (file == null) {
            //user cancelled file opening
            System.exit(0);
        }
        else {
            ParserWrapper parser = ioManager.read(file);
            Form form = parse(parser);
            boolean run = check(form);
            if (run) {
                Questionnaire gui = new Questionnaire(form);
                gui.run();
                SymbolTable answers = gui.getSymbolTable();
                String destinationPath = ioManager.selectSaveLocation();
                ioManager.writeToXml(answers, destinationPath);
                System.exit(0);
            }
            else {
                System.exit(SEMANTICS_ERROR);
            }
        }
    }
    
    /**
     * Main method that asks the user to select a ql-file, type checks it and
     * reports any errors or warnings or creates and runs the questionnaire whe
     * there are no errors and there are no warnings or the users chooses to
     * continue despite the warnings.
     * 
     * @param args the command line arguments, which are ignored
     */
    public static void main(String[] args) {
        Main main = new Main();
    }
    
    /**
     * Call {@link nl.uva.sea.ql.parser.ParserWrapper#parse() parse()} on a given
     * <code>ParserWrapper</code> and shutdown with an error if the parsing failed.
     * 
     * @param parser the <code>ParserWrapper</code> wrapping the
     *                  <code>Parser</code> that should attempt to <code>parse</code>
     *                  its source
     * @return a <code>Form</code> representing the contents of the
     *          <code>parse</code>d file
     */
    private Form parse(ParserWrapper parser) {
        boolean parsed = parser.parse();
            if (!parsed) {
                ioManager.showErrorMessage(SYNTAX_ERROR_MESSAGE, SYNTAX_ERROR_TITLE);
                System.exit(SYNTAX_ERROR);
                return null; //never reached
            }
            else {
                return parser.getResult();
            }
    }
    
    /**
     * Check the semantic correctness of a <code>Form</code> and present any
     * errors or warnings that were generated in the process to the user.
     * 
     * @param form the <code>Form</code> to check
     * @return whether or not the <code>form</code> should be interpreted,
     *          <code>true</code> if and only if no errors were generated and
     *          no warnings were generated or the user chose to continue despite
     *          the warnings
     */
    private boolean check(Form form) {
        QuestionIdentCollector identCollector = new QuestionIdentCollector();
        form.accept(identCollector);
        List<String> errors = identCollector.getErrors();
        
        Map<Ident,Question> firstQuestionsForIdentifiers
                = identCollector.getFirstQuestionsForIdentifiers();
        TypeChecker typeChecker = new TypeChecker(firstQuestionsForIdentifiers);
        form.accept(typeChecker);
        errors.addAll(typeChecker.getErrors());
        
        Iterable<Ident> identifiers = identCollector.obtainIdentifiers();
        DependencyChecker dependencyChecker = new DependencyChecker(identifiers);
        form.accept(dependencyChecker);
        dependencyChecker.detectCyclicDependencies();
        errors.addAll(dependencyChecker.getErrors());
        
        DuplicateLabelDetector duplicateLabelDetector = new DuplicateLabelDetector();
        form.accept(duplicateLabelDetector);
        List<String> warnings = duplicateLabelDetector.getWarnings();
        
        return handleErrorsAndWarnings(errors, warnings);
    }
    
    /**
     * Display errors and warnings to the user and determine wether to execute
     * the <code>Form</code> they apply to.
     * 
     * @param errors any error messages to display to the user
     * @param warnings any warning messages to display to the user
     * @return wether to execute the <code>Form</code> the <code>error</code>
     *          and <code>warnings</code> apply to, <code>true</code> if and
     *          only if <code>errors</code> was empty and <code>warnings</code>
     *          was empty or the user chose to continue despite the
     *          <code>warnings</code>
     */
    private boolean handleErrorsAndWarnings(List<String> errors, List<String> warnings) {
        if (!errors.isEmpty()) {
            ioManager.showErrorsAndWarnings(errors, warnings);
            return false;
        }
        else if (!warnings.isEmpty()) {
            return ioManager.handleWarnings(warnings);
        }
        else {
            return true;
        }
    }
}
