package org.uva.sea.ql.ast.form;

import org.uva.sea.ql.ast.Node;
import org.uva.sea.ql.ast.checker.Visitable;
import org.uva.sea.ql.ast.checker.Visitor;
import org.uva.sea.ql.ast.stat.Stat;

import java.util.List;

/**
 * Created by roydewildt on 04/02/16.
 */
public class Form implements Node, Visitable{
    private String id;
    private List<Stat> stms;

    public Form(String id, List<Stat> stms){
        this.id = id;
        this.stms = stms;
    }

    @Override
    public String toString() {
        String stmsStrs = "";
        for (Stat stat : stms) {
            stmsStrs += stat.toString() + ", ";
        }
        String stmsList= "[" + stmsStrs.substring(0,stmsStrs.length() - 2) + "]";
        return "Form(" + id + ", " + stmsList + ")";
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
