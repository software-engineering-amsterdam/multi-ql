package org.uva.sea.ql.ast.tree.form;

import javafx.beans.Observable;
import org.uva.sea.ql.ast.tree.Node;
import org.uva.sea.ql.ast.tree.stat.Stat;
import org.uva.sea.ql.ast.visitor.interfaces.IFormVisitable;
import org.uva.sea.ql.ast.visitor.interfaces.IFormVisitor;

import java.util.List;

/**
 * Created by roydewildt on 04/02/16.
 */
public class Form extends Node implements IFormVisitable {
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
    public <E, C> E accept(IFormVisitor<E, C> visitor, C context) {
        return visitor.visit(this,context);
    }
}
