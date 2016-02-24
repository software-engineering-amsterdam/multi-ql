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

    public BaseVisitor(){
    }

    @Override
    public T visit(Form form, U env) {

        if (form.getStms() != null){
            for (Stat s: form.getStms()) {
                s.accept(this,env);
            }
        }

        return null;
    }

    @Override
    public T visit(If stat, U env) {

        stat.getCond().accept(this,env);
        for(Stat s : stat.getStms())
            s.accept(this,env);

        return null;
    }

    @Override
    public T visit(IfElse stat, U env) {

        stat.getCond().accept(this,env);
        for(Stat s : stat.getIfStms())
            s.accept(this,env);
        for(Stat s : stat.getElseStms())
            s.accept(this,env);

        return null;
    }

    @Override
    public T visit(Question stat, U env) {

        stat.getType().accept(this,env);
        stat.getVarname().accept(this,env);
        stat.getExpr().accept(this,env);

        return null;
    }

    @Override
    public T visit(Add expr, U env) {

        expr.getLhs().accept(this,env);
        expr.getRhs().accept(this,env);

        return null;
    }

    @Override
    public T visit(And expr, U env) {

        expr.getLhs().accept(this,env);
        expr.getRhs().accept(this,env);

        return null;
    }

    @Override
    public T visit(Div expr, U env) {

        expr.getLhs().accept(this,env);
        expr.getRhs().accept(this,env);

        return null;
    }

    @Override
    public T visit(Eq expr, U env) {

        expr.getLhs().accept(this,env);
        expr.getRhs().accept(this,env);

        return null;
    }

    @Override
    public T visit(GEq expr, U env) {

        expr.getLhs().accept(this,env);
        expr.getRhs().accept(this,env);

        return null;
    }

    @Override
    public T visit(GT expr, U env) {

        expr.getLhs().accept(this,env);
        expr.getRhs().accept(this,env);

        return null;
    }

    @Override
    public T visit(LEq expr, U env) {

        expr.getLhs().accept(this,env);
        expr.getRhs().accept(this,env);

        return null;
    }

    @Override
    public T visit(LT expr, U env) {

        expr.getLhs().accept(this,env);
        expr.getRhs().accept(this,env);

        return null;
    }

    @Override
    public T visit(Mul expr, U env) {

        expr.getLhs().accept(this,env);
        expr.getRhs().accept(this,env);

        return null;
    }

    @Override
    public T visit(NEq expr, U env) {

        expr.getLhs().accept(this,env);
        expr.getRhs().accept(this,env);

        return null;
    }

    @Override
    public T visit(Or expr, U env) {

        expr.getLhs().accept(this,env);
        expr.getRhs().accept(this,env);

        return null;
    }

    @Override
    public T visit(Sub expr, U env) {

        expr.getLhs().accept(this,env);
        expr.getRhs().accept(this,env);

        return null;
    }

    @Override
    public T visit(Neg expr, U env) {

        expr.getValue().accept(this,env);

        return null;
    }

    @Override
    public T visit(Not expr, U env) {

        expr.getValue().accept(this,env);

        return null;
    }

    @Override
    public T visit(Pos expr, U env) {

        expr.getValue().accept(this,env);

        return null;
    }

    @Override
    public T visit(Primary expr, U env) {

        expr.getValue().accept(this,env);

        return null;
    }

    @Override
    public T visit(Boolean type, U env) {
        return null;
    }

    @Override
    public T visit(Money type, U env) {
        return null;
    }

    @Override
    public T visit(Bool val, U env) {
        return null;
    }

    @Override
    public T visit(Int val, U env) {
        return null;
    }

    @Override
    public T visit(Var var, U env) {
        return null;
    }

}
