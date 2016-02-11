package org.uva.sea.ql.ast.tree.form;

import org.uva.sea.ql.ast.tree.Node;
import org.uva.sea.ql.ast.visitor.Visitor;
import org.uva.sea.ql.ast.tree.stat.Stat;

import java.util.List;

/**
 * Created by roydewildt on 04/02/16.
 */
public class Form extends Node {
    private String id;
    private List<Stat> stms;

    public Form(int line, String id, List<Stat> stms){
        super(line);
        this.id = id;
        this.stms = stms;
    }

    public <T> T accept(Visitor visitor) {
        return visitor.visit(this);
    }

    public List<Stat> getStms(){
        return stms;
    }

    public String getId(){
        return id;
    }

}
