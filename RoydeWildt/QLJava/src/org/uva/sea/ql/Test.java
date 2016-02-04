package org.uva.sea.ql;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.uva.sea.ql.parser.antlr.*;

import java.io.IOException;

/**
 * Created by roydewildt on 04/02/16.
 */
public class Test {
    public static void main(String[] args) throws IOException {
        QLLexer lex = new QLLexer(new ANTLRFileStream("resources/dataset1.ql"));
        CommonTokenStream tok = new CommonTokenStream(lex);
        QLParser par = new QLParser(tok);

        try{
            String xstr = par.stats().result.toString();
            System.out.println(xstr);
        } catch (RecognitionException e) {
            e.printStackTrace();
        }

    }
}
