package org.uva.sea.ql;

import org.uva.sea.ql.checker.*;
import org.uva.sea.ql.ast.tree.form.Form;
import org.uva.sea.ql.parser.*;

/**
 * Created by roydewildt on 04/02/16.
 */
public class Test {
    public static void main(String[] args) throws Throwable {
        Form f1 = QLRunner.ParseFromPath("src/test/resources/conditions1.ql");

        Checker chk = new Checker();
        chk.invalidConditionChecker(f1);

    }
}
