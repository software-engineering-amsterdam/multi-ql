package org.uva.sea.ql.ast.visitor;

import org.uva.sea.ql.ast.tree.Node;
import org.uva.sea.ql.ast.tree.expr.binary.*;
import org.uva.sea.ql.ast.tree.expr.unary.*;
import org.uva.sea.ql.ast.tree.form.Form;
import org.uva.sea.ql.ast.tree.stat.*;
import org.uva.sea.ql.ast.tree.type.Boolean;
import org.uva.sea.ql.ast.tree.type.Money;
import org.uva.sea.ql.ast.tree.val.*;
import org.uva.sea.ql.ast.tree.val.Var;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by roydewildt on 10/02/16.
 */
public abstract class BaseVisitor<T,U> implements Visitor<T,U> {

    Visitor v;

    public BaseVisitor(){
        this.v = this;
    }

    @Override
    public T visit(Form form, U context) {

        if (form.getStms() != null){
            for (Stat s: form.getStms()) {
                s.accept(v,context);
            }
        }

        return null;
    }

    @Override
    public T visit(If stat, U context) {

        stat.getCond().accept(v,context);
        for(Stat s : stat.getStms())
            s.accept(v,context);

        return null;
    }

    @Override
    public T visit(IfElse stat, U context) {

        stat.getCond().accept(v,context);
        for(Stat s : stat.getIfStms())
            s.accept(v,context);
        for(Stat s : stat.getElseStms())
            s.accept(v,context);

        return null;
    }

    @Override
    public T visit(Question stat, U context) {

        stat.getType().accept(v,context);
        stat.getVarname().accept(v,context);

        return null;
    }

    @Override
    public T visit(AssQuestion stat, U context) {

        stat.getType().accept(v,context);
        stat.getVarname().accept(v,context);
        stat.getExpr().accept(v,context);

        return null;
    }

    @Override
    public T visit(Add expr, U context) {

        expr.getLhs().accept(v,context);
        expr.getRhs().accept(v,context);

        return null;
    }

    @Override
    public T visit(And expr, U context) {

        expr.getLhs().accept(v,context);
        expr.getRhs().accept(v,context);

        return null;
    }

    @Override
    public T visit(Div expr, U context) {

        expr.getLhs().accept(v,context);
        expr.getRhs().accept(v,context);

        return null;
    }

    @Override
    public T visit(Eq expr, U context) {

        expr.getLhs().accept(v,context);
        expr.getRhs().accept(v,context);

        return null;
    }

    @Override
    public T visit(GEq expr, U context) {

        expr.getLhs().accept(v,context);
        expr.getRhs().accept(v,context);

        return null;
    }

    @Override
    public T visit(GT expr, U context) {

        expr.getLhs().accept(v,context);
        expr.getRhs().accept(v,context);

        return null;
    }

    @Override
    public T visit(LEq expr, U context) {

        expr.getLhs().accept(v,context);
        expr.getRhs().accept(v,context);

        return null;
    }

    @Override
    public T visit(LT expr, U context) {

        expr.getLhs().accept(v,context);
        expr.getRhs().accept(v,context);

        return null;
    }

    @Override
    public T visit(Mul expr, U context) {

        expr.getLhs().accept(v,context);
        expr.getRhs().accept(v,context);

        return null;
    }

    @Override
    public T visit(NEq expr, U context) {

        expr.getLhs().accept(v,context);
        expr.getRhs().accept(v,context);

        return null;
    }

    @Override
    public T visit(Or expr, U context) {

        expr.getLhs().accept(v,context);
        expr.getRhs().accept(v,context);

        return null;
    }

    @Override
    public T visit(Sub expr, U context) {

        expr.getLhs().accept(v,context);
        expr.getRhs().accept(v,context);

        return null;
    }

    @Override
    public T visit(Neg expr, U context) {

        expr.getValue().accept(v,context);

        return null;
    }

    @Override
    public T visit(Not expr, U context) {

        expr.getValue().accept(v,context);

        return null;
    }

    @Override
    public T visit(Pos expr, U context) {

        expr.getValue().accept(v,context);

        return null;
    }

    @Override
    public T visit(Primary expr, U context) {

        expr.getValue().accept(v,context);

        return null;
    }

    @Override
    public T visit(Boolean val, U context) {
        return null;
    }

    @Override
    public T visit(Money val, U context) {
        return null;
    }

    @Override
    public T visit(Bool val, U context) {
        return null;
    }

    @Override
    public T visit(Int val, U context) {
        return null;
    }

    @Override
    public T visit(Var var, U context) {
        return null;
    }

    public Visitor getV() {
        return v;
    }
}
