package org.uva.sea.ql.ast.checker;

import org.uva.sea.ql.ast.form.Form;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by roy on 5-2-16.
 */
public class Checker {

    public Checker(){}

    public Set<String> undefinedChecker(Form f){
        Set<String> decls = new HashSet<>(f.accept(new DeclVisitor()));
        Set<String> vars  = new HashSet<>(f.accept(new VarsVisitor()));
        vars.removeAll(decls);

        return vars;
    }
}
