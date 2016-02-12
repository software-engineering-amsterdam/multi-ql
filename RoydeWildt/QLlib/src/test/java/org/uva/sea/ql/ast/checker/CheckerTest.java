package org.uva.sea.ql.ast.checker;

import org.uva.sea.ql.ast.checker.Checker;
import static org.junit.Assert.*;
import org.junit.*;
import org.uva.sea.ql.ast.tree.form.Form;
import org.uva.sea.ql.parser.QLRunner;

/**
 * Created by roydewildt on 12/02/16.
 */
public class CheckerTest {

    @Test public void UndefinedVarsCheck(){

        Form f1 = null;
        try {
            f1 = QLRunner.ParseFromPath("src/main/resources/undefined1.ql");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        Checker chk = new Checker();
        chk.undefinedChecker(f1);
    }


}
