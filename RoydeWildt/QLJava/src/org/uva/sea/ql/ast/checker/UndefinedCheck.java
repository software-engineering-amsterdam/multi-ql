package org.uva.sea.ql.ast.checker;

import org.uva.sea.ql.ast.Node;
import org.uva.sea.ql.ast.form.Form;
import org.uva.sea.ql.ast.stat.AssQuestion;
import org.uva.sea.ql.ast.stat.Question;
import org.uva.sea.ql.ast.var.Var;
import org.uva.sea.ql.ast.visitor.BaseVisitor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by roy on 5-2-16.
 */
public class UndefinedCheck extends BaseVisitor {

    private final List<Node> vars;
    private final List<Node> decls;
    private final List<Node> undefined;


    public UndefinedCheck(Form f) {
        this.vars = new ArrayList<>();
        this.decls = new ArrayList<>();
        this.undefined = new ArrayList<>();

        f.accept(this);
    }

    @Override
    public <T> T visit(Var var) {

        vars.add(var);
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
