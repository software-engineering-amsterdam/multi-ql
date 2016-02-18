/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.Test;
import ql.antlr.QLLexer;
import ql.antlr.QLParser;

/**
 *
 * @author sander
 */
public class ASTTest {

    public ASTTest() {
    }

    @Test
    public void BuildAST() throws FileNotFoundException, IOException {
        Reader input = new FileReader("A:\\Users\\sander\\Documents\\NetBeansProjects\\multi-ql\\savahu\\QL\\src\\examples\\house.ql");
        ANTLRInputStream chars = new ANTLRInputStream(input);
        QLLexer lexer = new QLLexer(chars);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        QLParser parser = new QLParser(tokens);
        parser.setBuildParseTree(true);
        String tree = parser.form().toStringTree();
        System.out.println(tree);
                //QLParser.FormContext r = parser.form();
        //assertEquals(r.Ident().getText(), "Box1HouseOwning");
    }
}
