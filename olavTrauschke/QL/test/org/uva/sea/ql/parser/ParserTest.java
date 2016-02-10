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
        ASTNode result = parser.getResult;
        ASTNode firstTerm = new Ident("a");
        ASTNode firstFactor = new Int(2);
        ASTNode secondFactor = new Ident("c");
        ASTNode secondTerm = new Mul(firstFactor, secondFactor);
        ASTNode firstTerms = new Add(firstTerm, secondTerm);
        ASTNode lastTerm = new Ident("d");
        ASTNode expected = new Add(firstTerms, lastTerm);
        assertEquals(expected, result);
    }
    
}
