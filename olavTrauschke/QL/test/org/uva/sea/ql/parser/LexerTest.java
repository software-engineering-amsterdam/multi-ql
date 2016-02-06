package org.uva.sea.ql.parser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import static org.junit.Assert.*;
import org.junit.Test;

public class LexerTest {

    @Test
    public void testExpressionAnalysis() throws FileNotFoundException {
        FileReader reader = new FileReader("expressionTest.ql");
        Lexer lexer = new Lexer(reader);
        ArrayList<Integer> tokens = new ArrayList<>();
        int token;
        do {
            token = lexer.nextToken();
            tokens.add(token);
        } while (token != Tokens.ENDINPUT);
        ArrayList<Integer> expected = new ArrayList<>();
        expected.add(Tokens.INT);
        expected.add((int) '+');
        expected.add(Tokens.INT);
        expected.add(Tokens.ENDINPUT);
        assertEquals(expected, tokens);
    }
    
}
