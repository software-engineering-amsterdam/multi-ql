package org.uva.sea.ql.parser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import static org.junit.Assert.*;
import org.junit.Test;

public class LexerTest {

    @Test
    public void testSimpleExpressionAnalysis() throws FileNotFoundException {
        FileReader reader = new FileReader("simpleExpression.ql");
        Lexer lexer = new Lexer(reader);
        ArrayList<Integer> tokens = obtainTokens(lexer);
        ArrayList<Integer> expected = new ArrayList<>();
        expected.add(Tokens.INT);
        expected.add((int) '+');
        expected.add(Tokens.INT);
        expected.add(Tokens.ENDINPUT);
        assertEquals(expected, tokens);
    }
    
    
    public void testComplexExpressionAnalysis() throws FileNotFoundException {
        Lexer lexer = new Lexer("complexExpression.ql");
        ArrayList<Integer> tokens = obtainTokens(lexer);
        ArrayList<Integer> expected = new ArrayList<>();
        expected.add(Tokens.IDENT);
        expected.add((int) '+');
        expected.add(Tokens.INT);
        expected.add((int) '*');
        expected.add(Tokens.IDENT);
        expected.add((int) '+');
        expected.add(Tokens.IDENT);
        expected.add(Tokens.ENDINPUT);
        assertEquals(expected, tokens);
    }
    
    private ArrayList<Integer> obtainTokens(Lexer lexer) {
        ArrayList<Integer> tokens = new ArrayList<>();
        int token;
        do {
            token = lexer.nextToken();
            tokens.add(token);
        } while (token != Tokens.ENDINPUT);
        return tokens;
    }
}
