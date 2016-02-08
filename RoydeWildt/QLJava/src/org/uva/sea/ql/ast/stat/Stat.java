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

    public Stat(){}

    public Stat (Expr cond, List<Stat> stms){
        this.cond = cond;
        this.stms = stms;
    }
    public Stat (Expr cond, List<Stat> stms, List<Stat> altStms){
        this.cond = cond;
        this.stms = stms;
        this.altStms = altStms;
    }

    public List<? extends Node> accept(Visitor visitor) {
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
}
