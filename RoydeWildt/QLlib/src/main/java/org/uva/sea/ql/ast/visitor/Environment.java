package org.uva.sea.ql.ast.visitor;

import org.uva.sea.ql.ast.tree.Node;
import org.uva.sea.ql.ast.tree.stat.Question;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by roydewildt on 17/02/16.
 */
public class Environment {

    private List<Node> env;

    public Environment() {
        this.env = new ArrayList<>();
    }

    public void add(Node q){
        if(!env.contains(q))
            env.add(q);
    }

    public void remove(Node q){
        if(env.contains(q))
            env.remove(q);
    }

    public boolean contains(Node v){
        if(env.contains(v))
            return true;
        return false;

    }

    public List<Node> getEnv() {
        return env;
    }

}
