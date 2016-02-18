package org.uva.sea.ql.parser;

import java.io.FileNotFoundException;
import static org.junit.Assert.*;
import org.junit.Test;
import org.uva.sea.ql.ast.Form;

public class ParserFormTest {
    
    @Test
    public void testFormParsing() throws FileNotFoundException {
        Parser parser = new Parser("form.ql");
        boolean parsed = parser.parse();
        assertTrue(parsed);
        Form result = parser.getResult();
        
        Form expected = new Form(null, null);
        //To do check result
    }
}
