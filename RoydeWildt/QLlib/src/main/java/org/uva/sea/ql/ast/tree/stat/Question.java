package org.uva.sea.ql.ast.tree.stat;

import org.uva.sea.ql.ast.tree.type.Type;
import org.uva.sea.ql.ast.visitor.Visitor;
import org.uva.sea.ql.ast.tree.val.Var;

/**
 * Created by roydewildt on 04/02/16.
 */
public class Question extends Stat {
    private String label;
    private Var varname;
    private Type type;

    public Question(int line, String label, Var varname, Type type){
        super(line);
        this.label = label.replace("\"", "");
        this.varname = varname;
        this.type = type;
    }

    public <T,U> T accept(Visitor<T,U> visitor, U context) {
        return visitor.visit(this, context);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Question(");
        sb.append(label + ",");
        sb.append(varname + ",");
        sb.append(type);
        sb.append(")");
        return sb.toString();
    }

    public String getLabel() {
        return label;
    }

    public Var getVarname() {
        return varname;
    }

    public Type getType() {
        return type;
    }

}
