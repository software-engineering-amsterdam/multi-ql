package nl.uva.sea.ql.checker;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import nl.uva.sea.ql.ast.ASTNode;
import nl.uva.sea.ql.ast.expr.Ident;
import nl.uva.sea.ql.generalPurposeVisitors.QuestionIdentCollector;
import nl.uva.sea.ql.parser.ParserWrapper;
import static org.junit.Assert.*;
import org.junit.Test;

public class DependencyCheckerTest {
    
    @Test
    public void testFormWithoutDependencyErrors() throws FileNotFoundException {
        DependencyChecker checker = createDependencyChecker("formWithoutDependencyErrors.ql");
        checker.detectCyclicDependencies();
        List<String> errors = checker.getErrors();
        List<String> expectedErrors = new ArrayList<>();
        assertEquals(expectedErrors, errors);
    }
    
    @Test
    public void testFormWithReferenceToUndefinedQuestion() throws FileNotFoundException {
        DependencyChecker checker = createDependencyChecker("form.ql");
        checker.detectCyclicDependencies();
        List<String> errors = checker.getErrors();
        List<String> expectedErrors = new ArrayList<>();
        String error = DependencyChecker.REFERENCE_TO_UNDEFINED_QUESTION_ERROR + "privateDebt";
        expectedErrors.add(error);
        assertEquals(expectedErrors, errors);
    }
    
    @Test
    public void testFormWithCyclicDependency() throws FileNotFoundException {
        DependencyChecker checker = createDependencyChecker("formWithCyclicDependency.ql");
        List<String> errors = checker.getErrors();
        assertTrue(errors.isEmpty());
        checker.detectCyclicDependencies();
        assertEquals(1, errors.size());
    }
    
    @Test
    public void testFormWithCyclicDependencyInvolvingConditionalStatement()
            throws FileNotFoundException {
        DependencyChecker checker
                = createDependencyChecker("formWithCyclicDependencyInvolvingConditionalStatement.ql");
        List<String> errors = checker.getErrors();
        assertTrue(errors.isEmpty());
        checker.detectCyclicDependencies();
        assertEquals(1, errors.size());
    }
    
    private DependencyChecker createDependencyChecker(String filename) throws FileNotFoundException {
        ParserWrapper parser = new ParserWrapper(filename);
        boolean parsed = parser.parse();
        assertTrue(parsed);
        ASTNode ast = parser.getResult();
        QuestionIdentCollector collector = new QuestionIdentCollector();
        ast.accept(collector);
        Iterable<Ident> identifiers = collector.obtainIdentifiers();
        DependencyChecker checker = new DependencyChecker(identifiers);
        ast.accept(checker);
        return checker;
    }
    
}
