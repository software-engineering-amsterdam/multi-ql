package org.uva.sea.ql.parser;

import org.antlr.v4.runtime.*;
import org.uva.sea.ql.ast.tree.form.Form;

import java.io.IOException;
import java.util.List;

/**
 * Created by roy on 12-2-16.
 */
public class QLRunner {

    public static Form parseFromPath(String path) throws Throwable {
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
            return par.form().result;
        } catch (RecognitionException e) {
            e.printStackTrace();
            throw e.getCause();
        }
    }

    public static Form parseString(String str) throws Throwable {
        QLLexer lex = null;

        lex = new QLLexer(new ANTLRInputStream(str));

        CommonTokenStream tok = new CommonTokenStream(lex);
        QLParser par = new QLParser(tok);

        try{
            return par.form().result;
        } catch (RecognitionException e) {
            e.printStackTrace();
            throw e.getCause();
        }
    }
}
