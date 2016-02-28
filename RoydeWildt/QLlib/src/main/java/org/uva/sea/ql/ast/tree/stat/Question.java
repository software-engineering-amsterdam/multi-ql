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
    private String label;
    private Var varname;
    private Type type;
    private Expr expr;

    public Question(int line, String label, Var varname, Type type){
        super(line);
        this.label = label.replace("\"", "");
        this.varname = varname;
        this.type = type;
        this.expr = new Primary(type.defaultValue());
    }

    public Question(int line, String label, Var varname, Type type, Expr expr){
        super(line);
        this.label = label.replace("\"", "");
        this.varname = varname;
        this.type = type;
        this.expr = expr;
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
