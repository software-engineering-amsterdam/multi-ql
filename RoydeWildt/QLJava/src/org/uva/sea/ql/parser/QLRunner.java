package org.uva.sea.ql.parser;

import org.antlr.v4.runtime.*;
import org.uva.sea.ql.ast.tree.form.Form;

import java.io.IOException;
import java.util.List;

/**
 * Created by roy on 12-2-16.
 */
public class QLRunner {

    public static List<Form> ParseFromPath(String path) throws Throwable {
        QLLexer lex = null;

        try {
            lex = new QLLexer(new ANTLRFileStream(path));
        } catch (IOException e) {
            e.printStackTrace();
            e.getCause();
        }

        CommonTokenStream tok = new CommonTokenStream(lex);
        QLParser par = new QLParser(tok);

        try{
            return par.forms().result;
        } catch (RecognitionException e) {
            e.printStackTrace();
            throw e.getCause();
        }
    }
}
