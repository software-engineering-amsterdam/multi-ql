package org.uva.sea.ql.ast.visitor;

import org.uva.sea.ql.ast.tree.expr.Expr;
import org.uva.sea.ql.ast.tree.expr.binary.*;
import org.uva.sea.ql.ast.tree.expr.unary.Neg;
import org.uva.sea.ql.ast.tree.expr.unary.Not;
import org.uva.sea.ql.ast.tree.expr.unary.Pos;
import org.uva.sea.ql.ast.tree.expr.unary.Primary;
import org.uva.sea.ql.ast.tree.form.Form;
import org.uva.sea.ql.ast.tree.stat.*;
import org.uva.sea.ql.ast.tree.val.Bool;
import org.uva.sea.ql.ast.tree.val.Int;
import org.uva.sea.ql.ast.tree.val.Var;
import org.uva.sea.ql.ast.visitor.BaseVisitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by roydewildt on 22/02/16.
 */
public class EvalVisitor<F,S,T> extends BaseVisitor<F,S,Object,T,Object,Void> {

    private final Map<Var,Expr>  decls;

    public EvalVisitor(Form f) {
        DeclVisitor dv = new DeclVisitor();
        f.accept(dv, null);
        this.decls = dv.getDecls();
    }

    @Override
    public S visit(If stat, Void context) {
        if((Boolean) stat.getCond().accept(this, context)){
            for(Stat s : stat.getStms())
                s.accept(this, context);
        }
        return null;
    }

    @Override
    public S visit(IfElse stat, Void context) {
        if((Boolean)stat.getCond().accept(this, context)){
            for(Stat s : stat.getIfStms())
                s.accept(this, context);
        }
        else {
            for(Stat s : stat.getElseStms())
                s.accept(this, context);
        }
        return null;
    }

    @Override
    public Object visit(Primary expr, Void context) {
        return expr.getValue().accept(this, context);
    }

    @Override
    public Object visit(Pos expr, Void context) {
        Integer value = (Integer) expr.getValue().accept(this, context);
        return value;
    }

    @Override
    public Object visit(Not expr, Void context) {
        Boolean value = (Boolean) expr.getValue().accept(this, context);
        return !value;
    }

    @Override
    public Object visit(Neg expr, Void context) {
        Integer value = (Integer) expr.getValue().accept(this, context);
        return -value;
    }

    @Override
    public Object visit(Sub expr, Void context) {
        Integer lhs = (Integer) expr.getLhs().accept(this, context);
        Integer rhs = (Integer) expr.getRhs().accept(this, context);
        return lhs - rhs;
    }

    @Override
    public Object visit(Or expr, Void context) {
        Boolean lhs = (Boolean) expr.getLhs().accept(this, context);
        Boolean rhs = (Boolean) expr.getRhs().accept(this, context);
        return lhs || rhs;
    }

    @Override
    public Object visit(NEq expr, Void context) {
        Object lhs = expr.getLhs().accept(this, context);
        Object rhs = expr.getRhs().accept(this, context);
        return lhs != rhs;
    }

    @Override
    public Object visit(Mul expr, Void context) {
        Integer lhs = (Integer) expr.getLhs().accept(this, context);
        Integer rhs = (Integer) expr.getRhs().accept(this, context);
        return lhs * rhs;
    }

    @Override
    public Object visit(LT expr, Void context) {
        Integer lhs = (Integer) expr.getLhs().accept(this, context);
        Integer rhs = (Integer) expr.getRhs().accept(this, context);
        return lhs < rhs;
    }

    @Override
    public Object visit(LEq expr, Void context) {
        Integer lhs = (Integer) expr.getLhs().accept(this, context);
        Integer rhs = (Integer) expr.getRhs().accept(this, context);
        return lhs <= rhs;
    }

    @Override
    public Object visit(GT expr, Void context) {
        Integer lhs = (Integer) expr.getLhs().accept(this, context);
        Integer rhs = (Integer) expr.getRhs().accept(this, context);
        return lhs > rhs;
    }

    @Override
    public Object visit(GEq expr, Void context) {
        Integer lhs = (Integer) expr.getLhs().accept(this, context);
        Integer rhs = (Integer) expr.getRhs().accept(this, context);
        return lhs >= rhs;
    }

    @Override
    public Object visit(Eq expr, Void context) {
        Object lhs = expr.getLhs().accept(this, context);
        Object rhs = expr.getRhs().accept(this, context);
        return lhs == rhs;
    }

    @Override
    public Object visit(Div expr, Void context) {
        Integer lhs = (Integer) expr.getLhs().accept(this, context);
        Integer rhs = (Integer) expr.getRhs().accept(this, context);
        return lhs / rhs;
    }

    @Override
    public Object visit(And expr, Void context) {
        Boolean lhs = (Boolean) expr.getLhs().accept(this, context);
        Boolean rhs = (Boolean) expr.getRhs().accept(this, context);
        return lhs && rhs;
    }

    @Override
    public Object visit(Add expr, Void context) {
        Integer lhs = (Integer) expr.getLhs().accept(this, context);
        Integer rhs = (Integer) expr.getRhs().accept(this, context);
        return lhs + rhs;
    }

    @Override
    public Object visit(Var val, Void context) {
        Expr expr = decls.get(val);
        return expr.accept(this, context);
    }

    @Override
    public Object visit(Bool val, Void context) {
        return val.getValue();
    }

    @Override
    public Object visit(Int val, Void context) {
        return val.getValue();
    }

}
