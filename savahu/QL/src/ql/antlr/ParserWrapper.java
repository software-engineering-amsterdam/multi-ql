package ql.antlr;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import ql.antlr.generatedcode.QLLexer;
import ql.antlr.generatedcode.QLParser;

/**
 *
 * @author sander
 */
public class ParserWrapper {

    public static QLParser initializeParser(String fileName) throws FileNotFoundException, IOException {
        Reader input = new FileReader("A:\\Users\\sander\\Documents\\NetBeansProjects\\multi-ql\\savahu\\QL\\src\\examples\\" + fileName);
        ANTLRInputStream chars = new ANTLRInputStream(input);
        QLLexer lexer = new QLLexer(chars);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        QLParser parser = new QLParser(tokens);
        parser.setBuildParseTree(true);
        return parser;
    }

}
