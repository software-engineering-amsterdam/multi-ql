import java.io.IOException;
import java.util.List;
import org.junit.Test;
import ql.antlr.ParserWrapper;
import ql.antlr.generatedcode.QLParser;
import ql.typechecker.ASTTypechecker;
import ql.ast.form.Form;

/**
 *
 * @author sander
 */
public class ASTTypecheckingTest {

    public ASTTypecheckingTest() {

    }

    @Test
    public void TypecheckTest() throws IOException {
        QLParser parser = ParserWrapper.initializeParser("house.ql");
        Form form = parser.form().result;
        ASTTypechecker typechecker = new ASTTypechecker();
        form.accept(typechecker);
        List<String> errorList = typechecker.GetErrors();
        errorList.stream().forEach(System.out::println);
        List<String> verboseLogList = typechecker.GetVerboseLogs();
        verboseLogList.stream().forEach(System.out::println);
    }

}
