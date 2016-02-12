package org.uva.sea.ql.ast.checker;

import org.uva.sea.ql.ast.tree.Node;
import org.uva.sea.ql.ast.tree.form.Form;
import org.uva.sea.ql.ast.tree.stat.AssQuestion;
import org.uva.sea.ql.ast.tree.stat.Question;
import org.uva.sea.ql.ast.tree.var.Var;
import org.uva.sea.ql.ast.visitor.BaseVisitor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by roy on 5-2-16.
 */
public class UndefinedVarsCheck extends BaseVisitor {

    private final List<Node> decls;
    private final List<Node> undefined;


    public UndefinedVarsCheck(Form f) {
        this.decls = new ArrayList<>();
        this.undefined = new ArrayList<>();

        f.accept(this);
    }

    @Override
    public <T> T visit(Var var) {

        if(!decls.contains(var))
            undefined.add(var);
        return null;
    }

    @Override
    public <T> T visit(Question stat) {
        decls.add(stat.getVarname());
        return null;
    }

    @Override
    public <T> T visit(AssQuestion stat) {
        decls.add(stat.getVarname());
        return null;
    }

    public List<Node> getUndefined() {
        return undefined;
    }
}
