package org.uva.sea.ql.checker;

import org.uva.sea.ql.ast.tree.Node;
import org.uva.sea.ql.ast.tree.form.Form;
import org.uva.sea.ql.ast.visitor.Environment;
import org.uva.sea.ql.ast.tree.stat.AssQuestion;
import org.uva.sea.ql.ast.tree.val.Var;
import org.uva.sea.ql.ast.visitor.BaseVisitor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by roydewildt on 17/02/16.
 */
public class CyclicQuestionsCheck extends BaseVisitor<Void,Environment> {
    final private List<Node> cyclics = new ArrayList<>();

    public CyclicQuestionsCheck(Form f) {
        f.accept(this,new Environment());

    }

    @Override
    public Void visit(AssQuestion stat, Environment env) {
        env.add(stat.getVarname());
        stat.getExpr().accept(this.getV(),env);
        env.remove(stat.getVarname());
        return null;
    }

    @Override
    public Void visit(Var var, Environment env) {
        if(env.contains(var))
            cyclics.add(var);
        return null;
    }

    public List<Node> getCyclics() {
        return cyclics;
    }
}
