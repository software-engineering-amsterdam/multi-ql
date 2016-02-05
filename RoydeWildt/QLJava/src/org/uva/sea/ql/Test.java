package org.uva.sea.ql;

import org.uva.sea.ql.ast.form.Form;
import org.uva.sea.ql.parser.antlr.*;

import java.io.IOException;
import java.util.List;

/**
 * Created by roydewildt on 04/02/16.
 */
public class Test {
    public static void main(String[] args) throws IOException {
        List<Form> x = QLParser.ParseForm("resources/dataset1.ql");
        System.out.println(x.toString());
    }
}
