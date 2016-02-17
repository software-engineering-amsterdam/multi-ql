package org.uva.sea.ql.ast.visitor;

import org.uva.sea.ql.ast.tree.stat.Question;
import org.uva.sea.ql.ast.tree.stat.Stat;
import org.uva.sea.ql.ast.tree.val.Var;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by roydewildt on 17/02/16.
 */
public class Environment {
    private List<Var> env;

    public Environment() {
        this.env = new ArrayList<>();
    }

    public void addDecl(Question q){
        if(!env.contains(q.getVarname()))
            env.add(q.getVarname());
    }

    public void removeDecl(Question q){
        if(env.contains(q.getVarname()))
            env.remove(q.getVarname());
    }

    public boolean isDeclared(Var v){
        if(env.contains(v))
            return true;
        return false;

    }

}
