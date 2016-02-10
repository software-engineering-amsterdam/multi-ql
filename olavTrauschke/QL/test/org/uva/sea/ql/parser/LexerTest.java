package org.uva.sea.ql.parser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class LexerTest {
    
    private ArrayList<Integer> expectedResultComplexExpression;
    
    @Before
    public void setUp() {
        expectedResultComplexExpression = new ArrayList<>();
        expectedResultComplexExpression.add(Tokens.IDENT);
        expectedResultComplexExpression.add((int) '+');
        expectedResultComplexExpression.add(Tokens.INT);
        expectedResultComplexExpression.add((int) '*');
        expectedResultComplexExpression.add(Tokens.IDENT);
        expectedResultComplexExpression.add((int) '+');
        expectedResultComplexExpression.add(Tokens.IDENT);
        expectedResultComplexExpression.add(Tokens.ENDINPUT);
    }
    
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
    
    @Test
    public void testComplexExpressionAnalysis() throws FileNotFoundException {
        Lexer lexer = new Lexer("complexExpression.ql");
        ArrayList<Integer> tokens = obtainTokens(lexer);
        assertEquals(expectedResultComplexExpression, tokens);
    }
    
    @Test
    public void testCommentAnalysis() throws FileNotFoundException {
        Lexer lexer = new Lexer("complexExpressionWithComments.ql");
        ArrayList<Integer> tokens = obtainTokens(lexer);
        assertEquals(expectedResultComplexExpression, tokens);
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
