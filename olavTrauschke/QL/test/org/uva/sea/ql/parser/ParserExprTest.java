package org.uva.sea.ql.parser;

import java.io.FileNotFoundException;
import org.junit.Test;
import static org.junit.Assert.*;
import org.uva.sea.ql.ast.ASTNode;
import org.uva.sea.ql.ast.expr.*;

public class ParserExprTest {

    @Test
    public void testSimpleExpressionParsing() throws FileNotFoundException {
        Parser parser = new Parser("simpleExpression.ql");
        boolean parsed = parser.parse();
        assertTrue(parsed);
        ASTNode result = parser.getResult();
        ASTNode expected = new Add(new Int(1), new Int(1));
        assertEquals(expected, result);
    }
    
    @Test
    public void testComplexExpressionParsing() throws FileNotFoundException {
        Parser parser = new Parser("complexExpression.ql");
        boolean parsed = parser.parse();
        assertTrue(parsed);
        ASTNode result = parser.getResult();
        
        Expr firstFactor = new Int(2);
        Expr secondFactor = new Ident("c");
        Expr multiplication = new Mul(firstFactor, secondFactor);
        Expr firstTerm = new Ident("a");
        Expr firstAddition = new Add(firstTerm, multiplication);
        Expr lastTerm = new Ident("d");
        Expr totalAddition = new Add(firstAddition, lastTerm);
        Expr firstToCompare = new Ident("e");
        Expr firstComparison = new LT(totalAddition, firstToCompare);
        Expr secondToCompare = new Bool(true);
        ASTNode expected = new Eq(firstComparison, secondToCompare);
        
        assertEquals(expected, result);
    }
    
    @Test
    public void testStringExpressionParsing() throws FileNotFoundException {
        Parser parser = new Parser("stringExpression.ql");
        boolean parsed = parser.parse();
        assertTrue(parsed);
        ASTNode result = parser.getResult();
        Expr firstStr = new Str("Hello");
        Expr secondStr = new Str(" world!");
        ASTNode expected = new Add(firstStr, secondStr);
        assertEquals(expected, result);
    }
    
}
