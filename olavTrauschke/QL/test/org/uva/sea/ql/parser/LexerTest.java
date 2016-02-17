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
        expectedResultComplexExpression.add((int) '(');
        expectedResultComplexExpression.add(Tokens.IDENT);
        expectedResultComplexExpression.add((int) '+');
        expectedResultComplexExpression.add(Tokens.INT_LITERAL);
        expectedResultComplexExpression.add((int) '*');
        expectedResultComplexExpression.add(Tokens.IDENT);
        expectedResultComplexExpression.add((int) '+');
        expectedResultComplexExpression.add(Tokens.IDENT);
        expectedResultComplexExpression.add((int) '<');
        expectedResultComplexExpression.add(Tokens.IDENT);
        expectedResultComplexExpression.add((int) ')');
        expectedResultComplexExpression.add(Tokens.EQ);
        expectedResultComplexExpression.add(Tokens.BOOLEAN_LITERAL);
        expectedResultComplexExpression.add(Tokens.ENDINPUT);
    }
    
    @Test
    public void testSimpleExpressionAnalysis() throws FileNotFoundException {
        FileReader reader = new FileReader("simpleExpression.ql");
        Lexer lexer = new Lexer(reader);
        ArrayList<Integer> tokens = obtainTokens(lexer);
        ArrayList<Integer> expected = new ArrayList<>();
        expected.add(Tokens.INT_LITERAL);
        expected.add((int) '+');
        expected.add(Tokens.INT_LITERAL);
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
    
    @Test
    public void testStringAnalysis() throws FileNotFoundException {
        Lexer lexer = new Lexer("stringExpression.ql");
        ArrayList<Integer> tokens = obtainTokens(lexer);
        
        ArrayList<Integer> expectedTokens = new ArrayList<>();
        expectedTokens.add(Tokens.STRING_LITERAL);
        expectedTokens.add((int) '+');
        expectedTokens.add(Tokens.STRING_LITERAL);
        expectedTokens.add(Tokens.ENDINPUT);
        assertEquals(expectedTokens, tokens);
    }
    
    @Test
    public void testQuestionAnalysis() throws FileNotFoundException {
        Lexer lexer = new Lexer("question.ql");
        ArrayList<Integer> tokens = obtainTokens(lexer);
        
        ArrayList<Integer> expectedTokens = new ArrayList<>();
        expectedTokens.add(Tokens.IDENT);
        expectedTokens.add((int) ':');
        expectedTokens.add(Tokens.STRING_LITERAL);
        expectedTokens.add(Tokens.BOOLEAN);
        expectedTokens.add(Tokens.ENDINPUT);
        assertEquals(expectedTokens, tokens);
    }
    
    @Test
    public void testComputedQuestionAnalysis() throws FileNotFoundException {
        Lexer lexer = new Lexer("computedQuestion.ql");
        ArrayList<Integer> tokens = obtainTokens(lexer);
        
        ArrayList<Integer> expectedTokens = new ArrayList<>();
        expectedTokens.add(Tokens.IDENT);
        expectedTokens.add((int) ':');
        expectedTokens.add(Tokens.STRING_LITERAL);
        expectedTokens.add(Tokens.MONEY);
        expectedTokens.add((int) '(');
        expectedTokens.add(Tokens.IDENT);
        expectedTokens.add((int) '-');
        expectedTokens.add(Tokens.IDENT);
        expectedTokens.add((int) ')');
        expectedTokens.add(Tokens.ENDINPUT);
        assertEquals(expectedTokens, tokens);
    }
    
    @Test
    public void testFormAnalysis() throws FileNotFoundException {
        Lexer lexer = new Lexer("form.ql");
        ArrayList<Integer> tokens = obtainTokens(lexer);
        
        ArrayList<Integer> expectedTokens = new ArrayList<>();
        expectedTokens.add(Tokens.FORM);
        expectedTokens.add(Tokens.IDENT);
        expectedTokens.add((int) '{');
        
        expectedTokens.add(Tokens.IDENT);
        expectedTokens.add((int) ':');
        expectedTokens.add(Tokens.STRING_LITERAL);
        expectedTokens.add(Tokens.BOOLEAN);
        
        expectedTokens.add(Tokens.IDENT);
        expectedTokens.add((int) ':');
        expectedTokens.add(Tokens.STRING_LITERAL);
        expectedTokens.add(Tokens.BOOLEAN);
        
        expectedTokens.add(Tokens.IF);
        expectedTokens.add((int) '(');
        expectedTokens.add(Tokens.IDENT);
        expectedTokens.add((int) ')');
        expectedTokens.add((int) '{');
        
        expectedTokens.add(Tokens.IDENT);
        expectedTokens.add((int) ':');
        expectedTokens.add(Tokens.STRING_LITERAL);
        expectedTokens.add(Tokens.MONEY);
        
        expectedTokens.add(Tokens.IDENT);
        expectedTokens.add((int) ':');
        expectedTokens.add(Tokens.STRING_LITERAL);
        expectedTokens.add(Tokens.MONEY);
        expectedTokens.add((int) '(');
        expectedTokens.add(Tokens.IDENT);
        expectedTokens.add((int) '-');
        expectedTokens.add(Tokens.IDENT);
        expectedTokens.add((int) ')');
        
        expectedTokens.add((int) '}');
        expectedTokens.add(Tokens.ELSE);
        expectedTokens.add((int) '{');
        
        expectedTokens.add(Tokens.IDENT);
        expectedTokens.add((int) ':');
        expectedTokens.add(Tokens.STRING_LITERAL);
        expectedTokens.add(Tokens.BOOLEAN);
        
        expectedTokens.add((int) '}');
        expectedTokens.add((int) '}');
        expectedTokens.add(Tokens.ENDINPUT);
        
        assertEquals(expectedTokens, tokens);
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
