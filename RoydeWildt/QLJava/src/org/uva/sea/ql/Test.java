package org.uva.sea.ql;

import org.uva.sea.ql.ast.checker.*;
import org.uva.sea.ql.ast.tree.form.Form;
import org.uva.sea.ql.parser.*;

import java.io.IOException;
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
        Form f4 = (QLParser.ParseForm(path.toAbsolutePath().toString() + "/resources/conditions1.ql")).get(0);

        System.out.println(f4.toString());
        Checker chk = new Checker();
        //chk.undefinedChecker(f1);
        //chk.duplicateChecker(f2);
        //chk.invalidConditionChecker(f4);

    }
}
