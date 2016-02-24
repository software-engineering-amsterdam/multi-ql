package org.uva.sea.ql.checker;

import org.uva.sea.ql.ast.tree.Node;
import org.uva.sea.ql.ast.tree.form.Form;
import org.uva.sea.ql.ast.tree.stat.Question;
import org.uva.sea.ql.ast.tree.val.Var;
import org.uva.sea.ql.ast.visitor.BaseVisitor;
import org.uva.sea.ql.ast.visitor.Environment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by roydewildt on 18/02/16.
 */
public class DuplicateLabelsCheck extends BaseVisitor<Void,Environment>{
    private final List<Node> duplicatelabels = new ArrayList<>();

    public DuplicateLabelsCheck(Form f) {
        f.accept(this, new Environment());
    }

    @Override
    public Void visit(Question stat, Environment env) {
        if(labelExists(env,stat.getLabel()) && !varExists(env, stat.getVarname()))
            duplicatelabels.add(stat);
        env.add(stat);
        super.visit(stat,env);
        return null;
    }

    public List<Node> getDuplicatelabels() {
        return duplicatelabels;
    }

    private boolean labelExists(Environment env, String label){
        for (Node n : env.getEnv()){
            String nLabel = ((Question) n).getLabel();
            if (nLabel.equals(label))
                return true;
        }
        return false;
    }

    private boolean varExists(Environment env, Var var){
        for (Node n : env.getEnv()){
            if (((Question) n).getVarname() == var)
                return true;
        }
        return false;
    }

}
