package org.uva.sea.ql.ast.tree.form;

import org.uva.sea.ql.ast.tree.Node;
import org.uva.sea.ql.ast.tree.stat.Stat;
import org.uva.sea.ql.ast.visitor.interfaces.FormVisitable;
import org.uva.sea.ql.ast.visitor.interfaces.FormVisitor;

import java.util.List;

/**
 * Created by roydewildt on 04/02/16.
 */
public class Form extends Node implements FormVisitable {
    private final String id;
    private final List<Stat> stms;

    public Form(int line, String id, List<Stat> stms){
        super(line);
        this.id = id;
        this.stms = stms;
    }

    public List<Stat> getStms(){
        return stms;
    }

    public String getId(){
        return id;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(id);
        sb.append("(");
        sb.append(stms.toString());
        sb.append(")");
        return sb.toString();

    }

    @Override
    public <FORM, CONTEXT> FORM accept(FormVisitor<FORM, CONTEXT> visitor, CONTEXT context) {
        return visitor.visit(this,context);
    }
}
