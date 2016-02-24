package org.uva.sea.ql.checker;

import org.uva.sea.ql.ast.tree.Node;
import org.uva.sea.ql.ast.tree.form.Form;
import org.uva.sea.ql.ast.tree.stat.*;
import org.uva.sea.ql.ast.tree.val.Var;
import org.uva.sea.ql.ast.visitor.BaseVisitor;
import org.uva.sea.ql.ast.visitor.Environment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by roy on 5-2-16.
 */
public class UndefinedVarsCheck extends BaseVisitor<Void,Environment> {

    private final List<Node> undefined = new ArrayList<>();


    public UndefinedVarsCheck(Form f) {

        f.accept(this, new Environment());
    }

    @Override
    public Void visit(Var var, Environment env) {
        if (!env.contains(var)) {
            undefined.add(var);
        }
        return null;
    }

    @Override
    public Void visit(Question stat, Environment env) {
        env.add(stat.getVarname());
        return null;
    }

    public List<Node> getUndefined() {
        return undefined;
    }
}
