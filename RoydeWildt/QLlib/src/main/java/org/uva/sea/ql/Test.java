package org.uva.sea.ql;

import org.uva.sea.ql.ast.checker.*;
import org.uva.sea.ql.ast.tree.form.Form;
import org.uva.sea.ql.parser.*;

/**
 * Created by roydewildt on 04/02/16.
 */
public class Test {
    public static void main(String[] args) throws Throwable {
        Form f2 = QLRunner.ParseFromPath("src/main/resources/duplicate1.ql");
        Form f4 = QLRunner.ParseFromPath("src/main/resources/expressions2.ql");

        Checker chk = new Checker();
        chk.duplicateChecker(f2);
        chk.invalidConditionChecker(f4);

    }
}
