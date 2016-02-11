package org.uva.sea.ql;

import org.uva.sea.ql.ast.Node;
import org.uva.sea.ql.ast.checker.*;
import org.uva.sea.ql.ast.form.Form;
import org.uva.sea.ql.ast.visitor.Visitor;
import org.uva.sea.ql.parser.*;

import java.io.IOException;
import java.util.List;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by roydewildt on 04/02/16.
 */
public class Test {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("");
        Form f1 = (QLParser.ParseForm(path.toAbsolutePath().toString() + "/resources/undefined1.ql")).get(0);
        Form f2 = (QLParser.ParseForm(path.toAbsolutePath().toString() + "/resources/duplicate1.ql")).get(0);
        Form f3 = (QLParser.ParseForm(path.toAbsolutePath().toString() + "/resources/operators1.ql")).get(0);

        Checker chk = new Checker();
        chk.undefinedChecker(f1);
        chk.duplicateChecker(f2);

        //System.out.println(res1.toString());
        //System.out.println(res2.toString());
    }
}
