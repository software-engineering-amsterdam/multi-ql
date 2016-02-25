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

/**
 * Created by roydewildt on 10/02/16.
 */
public abstract class BaseVisitor<F,S,E,T,V> implements IFormVisitor<F>,IStatVisitor<S>,IExprVisitor<E>,ITypeVisitor<T>,IValVisitor<V> {

    @Override
    public F visit(Form form) {

        if (form.getStms() != null){
            for (Stat s: form.getStms()) {
                s.accept(this);
            }
        }

        return null;
    }

    @Override
    public S visit(If stat) {

        stat.getCond().accept(this);
        for(Stat s : stat.getStms())
            s.accept(this);

        return null;
    }

    @Override
    public S visit(IfElse stat) {

        stat.getCond().accept(this);
        for(Stat s : stat.getIfStms())
            s.accept(this);
        for(Stat s : stat.getElseStms())
            s.accept(this);

        return null;
    }

    @Override
    public S visit(Question stat) {

        stat.getType().accept(this);
        stat.getVarname().accept(this);
        stat.getExpr().accept(this);

        return null;
    }

    @Override
    public E visit(Add expr) {

        expr.getLhs().accept(this);
        expr.getRhs().accept(this);

        return null;
    }

    @Override
    public E visit(And expr) {

        expr.getLhs().accept(this);
        expr.getRhs().accept(this);

        return null;
    }

    @Override
    public E visit(Div expr) {

        expr.getLhs().accept(this);
        expr.getRhs().accept(this);

        return null;
    }

    @Override
    public E visit(Eq expr) {

        expr.getLhs().accept(this);
        expr.getRhs().accept(this);

        return null;
    }

    @Override
    public E visit(GEq expr) {

        expr.getLhs().accept(this);
        expr.getRhs().accept(this);

        return null;
    }

    @Override
    public E visit(GT expr) {

        expr.getLhs().accept(this);
        expr.getRhs().accept(this);

        return null;
    }

    @Override
    public E visit(LEq expr) {

        expr.getLhs().accept(this);
        expr.getRhs().accept(this);

        return null;
    }

    @Override
    public E visit(LT expr) {

        expr.getLhs().accept(this);
        expr.getRhs().accept(this);

        return null;
    }

    @Override
    public E visit(Mul expr) {

        expr.getLhs().accept(this);
        expr.getRhs().accept(this);

        return null;
    }

    @Override
    public E visit(NEq expr) {

        expr.getLhs().accept(this);
        expr.getRhs().accept(this);

        return null;
    }

    @Override
    public E visit(Or expr) {

        expr.getLhs().accept(this);
        expr.getRhs().accept(this);

        return null;
    }

    @Override
    public E visit(Sub expr) {

        expr.getLhs().accept(this);
        expr.getRhs().accept(this);

        return null;
    }

    @Override
    public E visit(Neg expr) {

        expr.getValue().accept(this);

        return null;
    }

    @Override
    public E visit(Not expr) {

        expr.getValue().accept(this);

        return null;
    }

    @Override
    public E visit(Pos expr) {

        expr.getValue().accept(this);

        return null;
    }

    @Override
    public E visit(Primary expr) {

        expr.getValue().accept(this);

        return null;
    }

    @Override
    public T visit(Boolean type) {
        return null;
    }

    @Override
    public T visit(Money type) {
        return null;
    }

    @Override
    public V visit(Bool val) {
        return null;
    }

    @Override
    public V visit(Int val) {
        return null;
    }

    @Override
    public V visit(Var var) {
        return null;
    }

}
