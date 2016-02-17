package org.uva.sea.ql.checker;

import org.uva.sea.ql.ast.tree.Node;
import org.uva.sea.ql.ast.tree.form.Form;
import org.uva.sea.ql.ast.tree.stat.AssQuestion;
import org.uva.sea.ql.ast.tree.stat.Question;
import org.uva.sea.ql.ast.tree.val.Var;
import org.uva.sea.ql.ast.visitor.BaseVisitor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by roy on 5-2-16.
 */
public class UndefinedVarCheck<T,U> extends BaseVisitor<T,U> {

    private final List<Node> decls = new ArrayList<>();
    private final List<Node> undefined = new ArrayList<>();


    public UndefinedVarCheck(Form f) {

        f.accept(this,null);
    }

    @Override
    public T visit(Var var, U context) {

        if(!decls.contains(var))
            undefined.add(var);
        return null;
    }

    @Override
    public T visit(Question stat, U context) {
        decls.add(stat.getVarname());
        return null;
    }

    @Override
    public T visit(AssQuestion stat, U context) {
        decls.add(stat.getVarname());
        return null;
    }

    public List<Node> getUndefined() {
        return undefined;
    }
}
