package org.uva.sea.ql.parser;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.uva.sea.ql.ast.tree.form.Form;
import org.uva.sea.ql.parser.listener.ThrowingErrorListener;

/**
 * Created by roy on 12-2-16.
 */
public class QLRunner {

    public static Form parseFromPath(String path) throws Exception {

        QLLexer lexer = new QLLexer(new ANTLRFileStream(path));
        lexer.removeErrorListeners();
        lexer.addErrorListener(ThrowingErrorListener.INSTANCE);

        CommonTokenStream tok = new CommonTokenStream(lexer);

        QLParser par = new QLParser(tok);

        return par.form().result;
    }

    public static Form parseString(String str) throws ParseCancellationException {
        QLLexer lexer = new QLLexer(new ANTLRInputStream(str));
        lexer.removeErrorListeners();
        lexer.addErrorListener(ThrowingErrorListener.INSTANCE);

        CommonTokenStream tok = new CommonTokenStream(lexer);

        QLParser parser = new QLParser(tok);
        parser.removeErrorListeners();
        parser.addErrorListener(ThrowingErrorListener.INSTANCE);

        return parser.form().result;
    }
}
