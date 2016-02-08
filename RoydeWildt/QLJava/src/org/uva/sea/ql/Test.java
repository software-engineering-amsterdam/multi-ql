package org.uva.sea.ql;

import org.uva.sea.ql.ast.Node;
import org.uva.sea.ql.ast.checker.*;
import org.uva.sea.ql.ast.form.Form;
import org.uva.sea.ql.parser.*;

import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * Created by roydewildt on 04/02/16.
 */
public class Test {
    public static void main(String[] args) throws IOException {
        String projectpath = System.getProperty("user.dir");
        Form f = (QLParser.ParseForm(projectpath + "/QLJava/resources/undefined1.ql")).get(0);

        Visitor dv = new DeclVisitor();
        List<Node> res1 = (List<Node>) f.accept(dv);


        Visitor vv = new VarsVisitor();
        List<Node> res2 = (List<Node>) f.accept(vv);

        Checker chk = new Checker();
        //Set<String> res3 = chk.undefinedChecker(f);

        System.out.println(f.toString());
        System.out.println(res1.toString());
        System.out.println(res2.toString());
        //System.out.println(res3.toString());
    }
}
