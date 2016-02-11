package org.uva.sea.ql.ast.form;

import org.uva.sea.ql.ast.Node;
import org.uva.sea.ql.ast.visitor.Visitor;
import org.uva.sea.ql.ast.stat.Stat;

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
