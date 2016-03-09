package org.uva.sea.ql.ast.visitor;

import org.uva.sea.ql.ast.tree.expr.binary.*;
import org.uva.sea.ql.ast.tree.expr.unary.*;
import org.uva.sea.ql.ast.tree.form.Form;
import org.uva.sea.ql.ast.tree.stat.*;
import org.uva.sea.ql.ast.tree.type.Boolean;
import org.uva.sea.ql.ast.tree.type.Money;
import org.uva.sea.ql.ast.tree.val.*;
import org.uva.sea.ql.ast.tree.val.Var;
import org.uva.sea.ql.ast.visitor.interfaces.*;
import org.uva.sea.ql.ast.visitor.interfaces.TypeVisitor;

/**
 * Created by roydewildt on 10/02/16.
 */
public abstract class BaseVisitor<F,S,E,T,V,C> implements FormVisitor<F,C>,StatVisitor<S,C>,ExprVisitor<E,C>,TypeVisitor<T,C>,ValVisitor<V,C> {

    @Override
    public F visit(Form form, C context) {

        if (form.getStms() != null){
            for (Stat s: form.getStms()) {
                s.accept(this,context);
            }
        }

        return null;
    }

    @Override
    public S visit(If stat, C context) {

        stat.getCond().accept(this,context);
        for(Stat s : stat.getStms())
            s.accept(this,context);

        return null;
    }

    @Override
    public S visit(IfElse stat, C context) {

        stat.getCond().accept(this,context);
        for(Stat s : stat.getIfStms())
            s.accept(this,context);
        for(Stat s : stat.getElseStms())
            s.accept(this,context);

        return null;
    }

    @Override
    public S visit(Question stat, C context) {

        stat.getType().accept(this,context);
        stat.getVarname().accept(this,context);
        stat.getExpr().accept(this,context);

        return null;
    }

    @Override
    public E visit(Add expr, C context) {

        expr.getLhs().accept(this,context);
        expr.getRhs().accept(this,context);

        return null;
    }

    @Override
    public E visit(And expr, C context) {

        expr.getLhs().accept(this,context);
        expr.getRhs().accept(this,context);

        return null;
    }

    @Override
    public E visit(Div expr, C context) {

        expr.getLhs().accept(this,context);
        expr.getRhs().accept(this,context);

        return null;
    }

    @Override
    public E visit(Eq expr, C context) {

        expr.getLhs().accept(this,context);
        expr.getRhs().accept(this,context);

        return null;
    }

    @Override
    public E visit(GEq expr, C context) {

        expr.getLhs().accept(this,context);
        expr.getRhs().accept(this,context);

        return null;
    }

    @Override
    public E visit(GT expr, C context) {

        expr.getLhs().accept(this,context);
        expr.getRhs().accept(this,context);

        return null;
    }

    @Override
    public E visit(LEq expr, C context) {

        expr.getLhs().accept(this,context);
        expr.getRhs().accept(this,context);

        return null;
    }

    @Override
    public E visit(LT expr, C context) {

        expr.getLhs().accept(this,context);
        expr.getRhs().accept(this,context);

        return null;
    }

    @Override
    public E visit(Mul expr, C context) {

        expr.getLhs().accept(this,context);
        expr.getRhs().accept(this,context);

        return null;
    }

    @Override
    public E visit(NEq expr, C context) {

        expr.getLhs().accept(this,context);
        expr.getRhs().accept(this,context);

        return null;
    }

    @Override
    public E visit(Or expr, C context) {

        expr.getLhs().accept(this,context);
        expr.getRhs().accept(this,context);

        return null;
    }

    @Override
    public E visit(Sub expr, C context) {

        expr.getLhs().accept(this,context);
        expr.getRhs().accept(this,context);

        return null;
    }

    @Override
    public E visit(Neg expr, C context) {

        expr.getValue().accept(this,context);

        return null;
    }

    @Override
    public E visit(Not expr, C context) {

        expr.getValue().accept(this,context);

        return null;
    }

    @Override
    public E visit(Pos expr, C context) {

        expr.getValue().accept(this,context);

        return null;
    }

    @Override
    public E visit(Primary expr, C context) {

        expr.getValue().accept(this,context);

        return null;
    }

    @Override
    public T visit(Boolean type, C context) {
        return null;
    }

    @Override
    public T visit(Money type, C context) {
        return null;
    }

    @Override
    public V visit(Bool val, C context) {
        return null;
    }

    @Override
    public V visit(Int val, C context) {
        return null;
    }

    @Override
    public V visit(Str val, C context) {
        return null;
    }

    @Override
    public V visit(Var val, C context) {
        return null;
    }

}
