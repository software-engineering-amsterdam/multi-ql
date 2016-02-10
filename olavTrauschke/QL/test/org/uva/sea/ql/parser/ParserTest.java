package org.uva.sea.ql.parser;

import java.io.FileNotFoundException;
import org.junit.Test;
import static org.junit.Assert.*;
import org.uva.sea.ql.ast.ASTNode;
import org.uva.sea.ql.ast.expr.*;

public class ParserTest {

    @Test
    public void testSimpleExpressionParsing() throws FileNotFoundException {
        Lexer lexer = new Lexer("simpleExpression.ql");
        lexer.nextToken();
        Parser parser = new Parser(lexer);
        boolean parsed = parser.parse();
        assertTrue(parsed);
        ASTNode result = parser.getResult();
        ASTNode expected = new Add(new Int(1), new Int(1));
        assertEquals(expected, result);
    }
    
    public void testComplexExpressionParsing() throws FileNotFoundException {
        Lexer lexer = new Lexer("complexExpression.ql");
        lexer.nextToken();
        Parser parser = new Parser(lexer);
        boolean parsed = parser.parse();
        assertTrue(parsed);
        Expr result = parser.getResult();
        Expr firstTerm = new Ident("a");
        Expr firstFactor = new Int(2);
        Expr secondFactor = new Ident("c");
        Expr secondTerm = new Mul(firstFactor, secondFactor);
        Expr firstTerms = new Add(firstTerm, secondTerm);
        Expr lastTerm = new Ident("d");
        Expr expected = new Add(firstTerms, lastTerm);
        assertEquals(expected, result);
    }
    
}
