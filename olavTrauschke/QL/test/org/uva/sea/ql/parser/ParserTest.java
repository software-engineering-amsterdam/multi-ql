package org.uva.sea.ql.parser;

import java.io.FileNotFoundException;
import org.junit.Test;
import static org.junit.Assert.*;
import org.uva.sea.ql.ast.ASTNode;
import org.uva.sea.ql.ast.expr.*;

public class ParserTest {

    @Test
    public void testExpressionParsing() throws FileNotFoundException {
        Parser parser = new Parser("expressionTest.ql");
        boolean parsed = parser.parse();
        assertTrue(parsed);
        ASTNode result = parser.getResult();
        ASTNode expected = new Add(new Int(1), new Int(1));
        assertEquals(expected, result);
    }
    
}
