package org.uva.sea.ql.ast.stat;

import org.uva.sea.ql.ast.Node;
import org.uva.sea.ql.ast.checker.Visitable;
import org.uva.sea.ql.ast.checker.Visitor;
import org.uva.sea.ql.ast.expr.Expr;

import java.util.List;

/**
 * Created by roydewildt on 04/02/16.
 */
abstract public class Stat implements Node, Visitable {
    private Expr cond;
    private List<Stat> stms;
    private List<Stat> altStms;

    private String label;
    private String varname;
    private String type;
    private Expr   expr;

    public Stat (Expr cond, List<Stat> stms){
        this.cond = cond;
        this.stms = stms;
    }
    public Stat (Expr cond, List<Stat> stms, List<Stat> altStms){
        this.cond = cond;
        this.stms = stms;
        this.altStms = altStms;
    }

    public Stat (String label, String varname, String type){
        this.label = label;
        this.varname = varname;
        this.type = type;
    }

    public Stat (String label, String varname, String type, Expr expr){
        this.label = label;
        this.varname = varname;
        this.type = type;
        this.expr = expr;
    }

    public List<String> accept(Visitor visitor) {
        return visitor.visit(this);
    }

    public Expr getCond() {
        return cond;
    }

    public List<Stat> getStms() {
        return stms;
    }

    public List<Stat> getAltStms() {
        return altStms;
    }

    public String getLabel() {
        return label;
    }

    public String getVarname() {
        return varname;
    }

    public String getType() {
        return type;
    }

    public Expr getExpr() {
        return expr;
    }
}
