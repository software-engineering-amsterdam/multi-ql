package org.uva.sea.ql.parser;

import java.io.FileNotFoundException;
import org.junit.Test;
import static org.junit.Assert.*;
import org.uva.sea.ql.ast.ASTNode;
import org.uva.sea.ql.ast.expr.*;

public class ParserTest {

    @Test
    public void testExpressionParsing() throws FileNotFoundException {
        Lexer lexer = new Lexer("expressionTest.ql");
        lexer.nextToken();
        Parser parser = new Parser(lexer);
        boolean parsed = parser.parse();
        assertTrue(parsed);
        ASTNode result = parser.getResult();
        ASTNode expected = new Add(new Int(1), new Int(1));
        assertEquals(expected, result);
    }
    
}
