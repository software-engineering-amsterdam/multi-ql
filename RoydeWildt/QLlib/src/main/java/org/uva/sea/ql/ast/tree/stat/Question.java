package org.uva.sea.ql.ast.tree.stat;

import org.uva.sea.ql.ast.tree.expr.Expr;
import org.uva.sea.ql.ast.tree.expr.unary.Primary;
import org.uva.sea.ql.ast.tree.type.Type;
import org.uva.sea.ql.ast.tree.val.Var;
import org.uva.sea.ql.ast.visitor.interfaces.IStatVisitor;

/**
 * Created by roydewildt on 04/02/16.
 */
public class Question extends Stat {
    private final String label;
    private final Var varname;
    private final Type type;
    private final Expr expr;
    private final boolean computed;

    public Question(int line, String label, Var varname, Type type){
        super(line);
        this.label = label.replace("\"", "");
        this.varname = varname;
        this.type = type;
        this.expr = new Primary(type.getDefaultValue());
        this.computed = false;
    }

    public Question(int line, String label, Var varname, Type type, Expr expr){
        super(line);
        this.label = label.replace("\"", "");
        this.varname = varname;
        this.type = type;
        this.expr = expr;
        this.computed = true;
    }

    public Question(int line, String label, Var varname, Type type, Expr expr, boolean computed){
        super(line);
        this.label = label.replace("\"", "");
        this.varname = varname;
        this.type = type;
        this.expr = expr;
        this.computed = computed;
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

    public Expr getExpr() {
        return expr;
    }

    public boolean isComputed() {
        return computed;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Question(");
        sb.append(this.getLabel() + ",");
        sb.append(this.getVarname() + ",");
        sb.append(this.getType() + ",");
        sb.append(expr.toString());
        sb.append(")");
        return sb.toString();
    }

    @Override
    public <S, C> S accept(IStatVisitor<S, C> visitor, C context) {
        return visitor.visit(this, context);
    }
}
