package nl.uva.sc.ql.parser;

import java.io.IOException;

import org.antlr.v4.runtime.*;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ParserTest extends TestCase {

    public ParserTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite( ParserTest.class );
    }
    
    public void testExampleField() throws IOException {
        QLLexer lexer = new QLLexer(new ANTLRInputStream(getClass().getResourceAsStream("/test/parser/success.ql")));
        QLParser parser = new QLParser(new CommonTokenStream(lexer));
        parser.addErrorListener(new BaseErrorListener() {
            @Override
            public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
                throw new IllegalStateException("failed to parse at line " + line + " due to " + msg, e);
            }
        });
        parser.ql();
    }

    public void testSuccess() throws IOException {
        QLLexer lexer = new QLLexer(new ANTLRInputStream(getClass().getResourceAsStream("/test/parser/success.ql")));
        QLParser parser = new QLParser(new CommonTokenStream(lexer));
        parser.ql();

        assertEquals(0, parser.getNumberOfSyntaxErrors());
    }
    
    public void testUnsuccess() throws IOException {
        QLLexer lexer = new QLLexer(new ANTLRInputStream(getClass().getResourceAsStream("/test/parser/unsuccess.ql")));
        QLParser parser = new QLParser(new CommonTokenStream(lexer));
        parser.ql();

        assertEquals(5, parser.getNumberOfSyntaxErrors());
    }
}
