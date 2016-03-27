/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.Test;
import ql.antlr.ParserWrapper;
import ql.antlr.generatedcode.QLParser;
import ql.ast.ASTLogger;

/**
 *
 * @author sander
 */
public class ASTTest {

    public ASTTest() {
    }

    @Test
    public void BuildAST() throws IOException {
        QLParser parser = ParserWrapper.initializeParser("house.ql");
        ParseTree tree = parser.form();
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(new ASTLogger(), tree);
        System.out.println("Test complete");
    }
}
