package org.uva.sea.ql.ast.visitor;

import org.uva.sea.ql.ast.tree.expr.binary.*;
import org.uva.sea.ql.ast.tree.expr.unary.*;
import org.uva.sea.ql.ast.tree.form.Form;
import org.uva.sea.ql.ast.tree.stat.*;
import org.uva.sea.ql.ast.tree.val.*;
import org.uva.sea.ql.ast.tree.val.Var;

/**
 * Created by roydewildt on 10/02/16.
 */
public class BaseVisitor implements Visitor {

    Visitor v;

    public BaseVisitor(){
        v =this;
    }

    @Override
    public <T> T visit(Form form) {

        if (form.getStms() != null){
            for (Stat s: form.getStms()) {
                s.accept(v);
            }
        }

        return null;
    }

    @Override
    public <T> T visit(If stat) {

        stat.getCond().accept(v);
        for(Stat s : stat.getStms())
            s.accept(v);

        return null;
    }

    @Override
    public <T> T visit(IfElse stat) {

        stat.getCond().accept(v);
        for(Stat s : stat.getIfStms())
            s.accept(v);
        for(Stat s : stat.getElseStms())
            s.accept(v);

        return null;
    }

    @Override
    public <T> T visit(Question stat) {

        stat.getType().accept(v);
        stat.getVarname().accept(v);

        return null;
    }

    @Override
    public <T> T visit(AssQuestion stat) {

        stat.getType().accept(v);
        stat.getVarname().accept(v);
        stat.getExpr().accept(v);

        return null;
    }

    @Override
    public <T> T visit(Add expr) {

        expr.getLhs().accept(v);
        expr.getRhs().accept(v);

        return null;
    }

    @Override
    public <T> T visit(And expr) {

        expr.getLhs().accept(v);
        expr.getRhs().accept(v);

        return null;
    }

    @Override
    public <T> T visit(Div expr) {

        expr.getLhs().accept(v);
        expr.getRhs().accept(v);

        return null;
    }

    @Override
    public <T> T visit(Eq expr) {

        expr.getLhs().accept(v);
        expr.getRhs().accept(v);

        return null;
    }

    @Override
    public <T> T visit(GEq expr) {

        expr.getLhs().accept(v);
        expr.getRhs().accept(v);

        return null;
    }

    @Override
    public <T> T visit(GT expr) {

        expr.getLhs().accept(v);
        expr.getRhs().accept(v);

        return null;
    }

    @Override
    public <T> T visit(LEq expr) {

        expr.getLhs().accept(v);
        expr.getRhs().accept(v);

        return null;
    }

    @Override
    public <T> T visit(LT expr) {

        expr.getLhs().accept(v);
        expr.getRhs().accept(v);

        return null;
    }

    @Override
    public <T> T visit(Mul expr) {

        expr.getLhs().accept(v);
        expr.getRhs().accept(v);

        return null;
    }

    @Override
    public <T> T visit(NEq expr) {

        expr.getLhs().accept(v);
        expr.getRhs().accept(v);

        return null;
    }

    @Override
    public <T> T visit(Or expr) {

        expr.getLhs().accept(v);
        expr.getRhs().accept(v);

        return null;
    }

    @Override
    public <T> T visit(Sub expr) {

        expr.getLhs().accept(v);
        expr.getRhs().accept(v);

        return null;
    }

    @Override
    public <T> T visit(Neg expr) {

        expr.getValue().accept(v);

        return null;
    }

    @Override
    public <T> T visit(Not expr) {

        expr.getValue().accept(v);

        return null;
    }

    @Override
    public <T> T visit(Pos expr) {

        expr.getValue().accept(v);

        return null;
    }

    @Override
    public <T> T visit(Primary expr) {

        expr.getValue().accept(v);

        return null;
    }

    @Override
    public <T> T visit(Bool val) {
        return null;
    }

    @Override
    public <T> T visit(Int val) {
        return null;
    }

    @Override
    public <T> T visit(Var var) {
        return null;
    }

    public Visitor getV() {
        return v;
    }
}
