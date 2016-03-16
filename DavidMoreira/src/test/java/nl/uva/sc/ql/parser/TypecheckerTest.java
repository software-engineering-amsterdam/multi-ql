package nl.uva.sc.ql.parser;

import java.io.IOException;

import nl.uva.sc.ql.compiler.parser.CreateASTTree;
import nl.uva.sc.ql.compiler.parser.ast.FormNode;
import nl.uva.sc.ql.compiler.typechecker.SymbolTable;
import nl.uva.sc.ql.compiler.typechecker.TypeChecker;
import nl.uva.sc.ql.messages.MessagesHandler;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TypecheckerTest extends TestCase {

    public TypecheckerTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite( TypecheckerTest.class );
    }

    public void testSuccess() throws IOException {
        QLLexer lexer = new QLLexer(new ANTLRInputStream(getClass().getResourceAsStream("/test/typechecker/success.ql")));
        QLParser parser = new QLParser(new CommonTokenStream(lexer));
        ParseTree tree = parser.ql();

        CreateASTTree astTree = new CreateASTTree();
        FormNode ast = (FormNode) astTree.visit(tree);
        
        SymbolTable symbolTable = new SymbolTable();
        MessagesHandler messagesHandler = new MessagesHandler();
        
        TypeChecker typeChecker = new TypeChecker(symbolTable, messagesHandler);        
        ast.accept(typeChecker);

        assertFalse(messagesHandler.asError());
        assertFalse(messagesHandler.asWarning());
    }
    
    public void testSucess_WithWarnings() throws IOException {
        QLLexer lexer = new QLLexer(new ANTLRInputStream(getClass().getResourceAsStream("/test/typechecker/success_withWarnings.ql")));
        QLParser parser = new QLParser(new CommonTokenStream(lexer));
        ParseTree tree = parser.ql();

        CreateASTTree astTree = new CreateASTTree();
        FormNode ast = (FormNode) astTree.visit(tree);
        
        SymbolTable symbolTable = new SymbolTable();
        MessagesHandler messagesHandler = new MessagesHandler();

        TypeChecker typeChecker = new TypeChecker(symbolTable, messagesHandler);        
        ast.accept(typeChecker);

        assertFalse(messagesHandler.asError());
        assertTrue(messagesHandler.asWarning());
    }
    
    public void testUnsuccess_UndefinedQuestion() throws IOException {
    	auxUnsuccessTests("/test/typechecker/unsuccess_undefinedQuestion.ql");
    }
    
    public void testUnsuccess_ConditionNotBoolean() throws IOException {
    	auxUnsuccessTests("/test/typechecker/unsuccess_conditionNotBoolean.ql");
    }
    
    public void testUnsuccess_OperandsInvalidTypeOperation() throws IOException {
    	auxUnsuccessTests("/test/typechecker/unsuccess_operandsInvalidTypeOperation.ql");
    }
    
    public void testUnsuccess_CyclicDependency() throws IOException {
    	auxUnsuccessTests("/test/typechecker/unsuccess_cyclicDependency.ql");
    }
    
    private void auxUnsuccessTests(String file) throws IOException {
        QLLexer lexer = new QLLexer(new ANTLRInputStream(getClass().getResourceAsStream(file)));
        QLParser parser = new QLParser(new CommonTokenStream(lexer));
        ParseTree tree = parser.ql();

        CreateASTTree astTree = new CreateASTTree();
        FormNode ast = (FormNode) astTree.visit(tree);
        
        SymbolTable symbolTable = new SymbolTable();
        MessagesHandler messagesHandler = new MessagesHandler();

        TypeChecker typeChecker = new TypeChecker(symbolTable, messagesHandler);        
        ast.accept(typeChecker);

        assertTrue(messagesHandler.asError());
        assertFalse(messagesHandler.asWarning());
    }
}
