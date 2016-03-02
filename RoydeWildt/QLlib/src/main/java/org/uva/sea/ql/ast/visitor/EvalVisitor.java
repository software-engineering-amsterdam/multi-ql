package org.uva.sea.ql.ast.visitor;

import org.uva.sea.ql.ast.tree.Node;
import org.uva.sea.ql.ast.tree.expr.Expr;
import org.uva.sea.ql.ast.tree.expr.binary.*;
import org.uva.sea.ql.ast.tree.expr.unary.*;
import org.uva.sea.ql.ast.tree.form.Form;
import org.uva.sea.ql.ast.tree.stat.*;
import org.uva.sea.ql.ast.tree.val.Bool;
import org.uva.sea.ql.ast.tree.val.Int;
import org.uva.sea.ql.ast.tree.val.Val;
import org.uva.sea.ql.ast.tree.val.Var;
import org.uva.sea.ql.ast.visitor.BaseVisitor;
import org.uva.sea.ql.ast.visitor.interfaces.IExprVisitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by roydewildt on 22/02/16.
 */
public class EvalVisitor<F,S,T> extends BaseVisitor<F,S,UnaryExpr,T,Val,Void> {

    private final Map<Var,Expr>  decls;

    public EvalVisitor(Form f) {
        DeclVisitor dv = new DeclVisitor();
        f.accept(dv, null);
        this.decls = dv.getDecls();
    }

    @Override
    public S visit(If stat, Void context) {
        Bool value = stat.getCond().accept(this, context).getValue();
        if(value.getValue()){
            for(Stat s : stat.getStms())
                s.accept(this, context);
        }
        return null;
    }

    @Override
    public S visit(IfElse stat, Void context) {
        Bool value = stat.getCond().accept(this, context).getValue();
        if(value.getValue()){
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
    public Primary visit(Primary expr, Void context) {
        return new Primary(expr.getValue().accept(this, context));
    }

    @Override
    public Primary visit(Pos expr, Void context) {
        Int value = expr.getValue().accept(this, context).getValue();
        return new Primary(new Int(value.getValue()));
    }

    @Override
    public Primary visit(Not expr, Void context) {
        Bool value = expr.getValue().accept(this, context).getValue();
        return new Primary(new Bool(!value.getValue()));
    }

    @Override
    public Primary visit(Neg expr, Void context) {
        Int value = expr.getValue().accept(this, context).getValue();
        return new Primary(new Int(-value.getValue()));
    }

    @Override
    public Primary visit(Sub expr, Void context) {
        Int lhs = expr.getLhs().accept(this, context).getValue();
        Int rhs = expr.getRhs().accept(this, context).getValue();
        return new Primary(new Int(lhs.getValue() - rhs.getValue()));
    }

    @Override
    public Primary visit(Or expr, Void context) {
        Bool lhs = expr.getLhs().accept(this, context).getValue();
        Bool rhs = expr.getRhs().accept(this, context).getValue();
        return new Primary(new Bool(lhs.getValue() || rhs.getValue()));
    }

    @Override
    public Primary visit(NEq expr, Void context) {
        Val lhs = expr.getLhs().accept(this, context).getValue();
        Val rhs = expr.getRhs().accept(this, context).getValue();
        return new Primary(new Bool(lhs.getValue() != rhs.getValue()));
    }

    @Override
    public Primary visit(Mul expr, Void context) {
        Int lhs = expr.getLhs().accept(this, context).getValue();
        Int rhs = expr.getRhs().accept(this, context).getValue();
        return new Primary(new Int(lhs.getValue() * rhs.getValue()));
    }

    @Override
    public Primary visit(LT expr, Void context) {
        Int lhs = expr.getLhs().accept(this, context).getValue();
        Int rhs = expr.getRhs().accept(this, context).getValue();
        return new Primary(new Bool(lhs.getValue() < rhs.getValue()));
    }

    @Override
    public Primary visit(LEq expr, Void context) {
        Int lhs = expr.getLhs().accept(this, context).getValue();
        Int rhs = expr.getRhs().accept(this, context).getValue();
        return new Primary(new Bool(lhs.getValue() <= rhs.getValue()));
    }

    @Override
    public Primary visit(GT expr, Void context) {
        Int lhs = expr.getLhs().accept(this, context).getValue();
        Int rhs = expr.getRhs().accept(this, context).getValue();
        return new Primary(new Bool(lhs.getValue() > rhs.getValue()));
    }

    @Override
    public Primary visit(GEq expr, Void context) {
        Int lhs = expr.getLhs().accept(this, context).getValue();
        Int rhs = expr.getRhs().accept(this, context).getValue();
        return new Primary(new Bool(lhs.getValue() >= rhs.getValue()));
    }

    @Override
    public Primary visit(Eq expr, Void context) {
        Val lhs = expr.getLhs().accept(this, context).getValue();
        Val rhs = expr.getRhs().accept(this, context).getValue();
        return new Primary(new Bool(lhs.getValue() == rhs.getValue()));
    }

    @Override
    public Primary visit(Div expr, Void context) {
        Int lhs = expr.getLhs().accept(this, context).getValue();
        Int rhs = expr.getRhs().accept(this, context).getValue();
        return new Primary(new Int(lhs.getValue() / rhs.getValue()));
    }

    @Override
    public Primary visit(And expr, Void context) {
        Bool lhs = expr.getLhs().accept(this, context).getValue();
        Bool rhs = expr.getRhs().accept(this, context).getValue();
        return new Primary(new Bool(lhs.getValue() && rhs.getValue()));
    }

    @Override
    public Primary visit(Add expr, Void context) {
        Int lhs = expr.getLhs().accept(this, context).getValue();
        Int rhs = expr.getRhs().accept(this, context).getValue();
        return new Primary(new Int(lhs.getValue() + rhs.getValue()));
    }

    @Override
    public Val visit(Var val, Void context) {
        Expr expr = decls.get(val);
        Primary value = (Primary) expr.accept(this,context);
        return value.getValue();
    }

    @Override
    public Bool visit(Bool val, Void context) {
        return val;
    }

    @Override
    public Int visit(Int val, Void context) {
        return val;
    }

}
