

import antlr.FormulierParser;
import java.io.CharArrayReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.Test;
import static org.junit.Assert.*;
import org.uva.sea.ql.parser.antlr.FormulierLexer;
import ql.TreeWalker;

/**
 *
 * @author Dominique
 */
public class ASTTest {
    
 
    
    @Test
    public void buildAST() throws FileNotFoundException, IOException{
        Reader input = new FileReader("/Users/Dominique/NetBeansProjects/multi-ql/Mickeydus/QL/src/ql/Form.ql");
        ANTLRInputStream chars = new ANTLRInputStream(input);
        FormulierLexer lexer = new FormulierLexer(chars);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        FormulierParser parser = new FormulierParser(tokens);
        parser.setBuildParseTree(true);
        ParseTree tree = parser.form();
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(new TreeWalker(), tree );
        System.out.println("Test complete");
    }
}