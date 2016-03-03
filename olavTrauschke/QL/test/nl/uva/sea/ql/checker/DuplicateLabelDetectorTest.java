package nl.uva.sea.ql.checker;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import nl.uva.sea.ql.ast.Form;
import nl.uva.sea.ql.parser.ParserWrapper;
import static org.junit.Assert.*;
import org.junit.Test;

public class DuplicateLabelDetectorTest {
    
    @Test
    public void testFormWithoutErrors() throws FileNotFoundException {
        assertNoWarnings("form.ql");
    }
    
    @Test
    public void testFormWithCorrectDuplicateLabel() throws FileNotFoundException {
        assertNoWarnings("formWithCorrectDuplicateLabel.ql");
    }
    
    @Test
    public void testFormWithIncorrectDuplicateLabel() throws FileNotFoundException {
        ParserWrapper parser = new ParserWrapper("formWithIncorrectDuplicateLabel.ql");
        boolean parsed = parser.parse();
        assertTrue(parsed);
        Form ast = parser.getResult();
        
        DuplicateLabelDetector detector = new DuplicateLabelDetector();
        ast.accept(detector);
        
        List<String> warnings = detector.getWarnings();
        List<String> expectedWarnings = new ArrayList<>();
        String warning = DuplicateLabelDetector.DUPLICATE_LABEL_ERROR + "Is this label duplicate?";
        expectedWarnings.add(warning);
        
        assertEquals(expectedWarnings, warnings);
    }
    
    private void assertNoWarnings(String filename) throws FileNotFoundException {
        ParserWrapper parser = new ParserWrapper(filename);
        boolean parsed = parser.parse();
        assertTrue(parsed);
        Form ast = parser.getResult();
        
        DuplicateLabelDetector detector = new DuplicateLabelDetector();
        ast.accept(detector);
        
        List<String> errors = detector.getWarnings();
        assertTrue(errors.isEmpty());
    }
    
}
