package org.uva.sea.ql.ast.visitor;

import org.uva.sea.ql.ast.tree.expr.Expr;
import org.uva.sea.ql.ast.tree.expr.binary.*;
import org.uva.sea.ql.ast.tree.expr.unary.*;
import org.uva.sea.ql.ast.tree.form.Form;
import org.uva.sea.ql.ast.tree.stat.If;
import org.uva.sea.ql.ast.tree.stat.IfElse;
import org.uva.sea.ql.ast.tree.stat.Question;
import org.uva.sea.ql.ast.tree.stat.Stat;
import org.uva.sea.ql.ast.tree.type.Boolean;
import org.uva.sea.ql.ast.tree.type.Money;
import org.uva.sea.ql.ast.tree.type.Type;
import org.uva.sea.ql.ast.tree.val.Bool;
import org.uva.sea.ql.ast.tree.val.Int;
import org.uva.sea.ql.ast.tree.val.Val;
import org.uva.sea.ql.ast.tree.val.Var;
import org.uva.sea.ql.ast.visitor.context.NodeUpdate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by roy on 28-2-16.
 */
public class UpdateVisitor extends BaseVisitor<Form, Stat, Expr, Type, Val, NodeUpdate> {
    @Override
    public Form visit(Form form, NodeUpdate context) {

        List<Stat> stms = new ArrayList<>();
        if (form.getStms() != null){
            for (Stat s: form.getStms()) {
                stms.add(s.accept(this,context));
            }
        }

        return new Form(form.getLine(), form.getId(), stms);
    }

    @Override
    public Stat visit(If stat, NodeUpdate context) {

        Expr c = stat.getCond().accept(this,context);
        List<Stat> stms = new ArrayList<>();
        for(Stat s : stat.getStms())
            stms.add(s.accept(this,context));

        return new If(stat.getLine(), c, stms);
    }

    @Override
    public Stat visit(IfElse stat, NodeUpdate context) {

        Expr c = stat.getCond().accept(this,context);
        List<Stat> ifStms = new ArrayList<>();
        List<Stat> elseStms = new ArrayList<>();
        for(Stat s : stat.getIfStms())
            ifStms.add(s.accept(this,context));
        for(Stat s : stat.getElseStms())
            elseStms.add(s.accept(this,context));

        return new IfElse(stat.getLine(), c, ifStms, elseStms);
    }

    @Override
    public Stat visit(Question stat, NodeUpdate context) {

        Type t = stat.getType().accept(this,context);
        Var v = (Var) stat.getVarname().accept(this,context);
        Expr e = stat.getExpr().accept(this,context);
        return new Question(stat.getLine(), stat.getLabel(), v, t, e);
    }

    @Override
    public Expr visit(Add expr, NodeUpdate context) {

        Expr lhs = expr.getLhs().accept(this,context);
        Expr rhs = expr.getRhs().accept(this,context);
        return new Add(expr.getLine(), lhs, rhs);
    }

    @Override
    public Expr visit(And expr, NodeUpdate context) {

        Expr lhs = expr.getLhs().accept(this,context);
        Expr rhs = expr.getRhs().accept(this,context);
        return new And(expr.getLine(), lhs, rhs);
    }

    @Override
    public Expr visit(Div expr, NodeUpdate context) {

        Expr lhs = expr.getLhs().accept(this,context);
        Expr rhs = expr.getRhs().accept(this,context);
        return new Div(expr.getLine(), lhs, rhs);
    }

    @Override
    public Expr visit(Eq expr, NodeUpdate context) {

        Expr lhs = expr.getLhs().accept(this,context);
        Expr rhs = expr.getRhs().accept(this,context);
        return new Eq(expr.getLine(), lhs, rhs);
    }

    @Override
    public Expr visit(GEq expr, NodeUpdate context) {

        Expr lhs = expr.getLhs().accept(this,context);
        Expr rhs = expr.getRhs().accept(this,context);
        return new GEq(expr.getLine(), lhs, rhs);
    }

    @Override
    public Expr visit(GT expr, NodeUpdate context) {

        Expr lhs = expr.getLhs().accept(this,context);
        Expr rhs = expr.getRhs().accept(this,context);
        return new GT(expr.getLine(), lhs, rhs);
    }

    @Override
    public Expr visit(LEq expr, NodeUpdate context) {

        Expr lhs = expr.getLhs().accept(this,context);
        Expr rhs = expr.getRhs().accept(this,context);
        return new LEq(expr.getLine(), lhs, rhs);
    }

    @Override
    public Expr visit(LT expr, NodeUpdate context) {

        Expr lhs = expr.getLhs().accept(this,context);
        Expr rhs = expr.getRhs().accept(this,context);
        return new LT(expr.getLine(), lhs, rhs);
    }

    @Override
    public Expr visit(Mul expr, NodeUpdate context) {

        Expr lhs = expr.getLhs().accept(this,context);
        Expr rhs = expr.getRhs().accept(this,context);
        return new Mul(expr.getLine(), lhs, rhs);
    }

    @Override
    public Expr visit(NEq expr, NodeUpdate context) {

        Expr lhs = expr.getLhs().accept(this,context);
        Expr rhs = expr.getRhs().accept(this,context);
        return new NEq(expr.getLine(), lhs, rhs);
    }

    @Override
    public Expr visit(Or expr, NodeUpdate context) {

        Expr lhs = expr.getLhs().accept(this,context);
        Expr rhs = expr.getRhs().accept(this,context);
        return new Or(expr.getLine(), lhs, rhs);
    }

    @Override
    public Expr visit(Sub expr, NodeUpdate context) {

        Expr lhs = expr.getLhs().accept(this,context);
        Expr rhs = expr.getRhs().accept(this,context);
        return new Sub(expr.getLine(), lhs, rhs);
    }

    @Override
    public Expr visit(Neg expr, NodeUpdate context) {

        Expr e = expr.getValue().accept(this,context);
        return new Neg(expr.getLine(), e);
    }

    @Override
    public Expr visit(Not expr, NodeUpdate context) {

        Expr e = expr.getValue().accept(this,context);
        return new Not(expr.getLine(), e);
    }

    @Override
    public Expr visit(Pos expr, NodeUpdate context) {

        Expr e = expr.getValue().accept(this,context);
        return new Pos(expr.getLine(), e);
    }

    @Override
    public Expr visit(Primary expr, NodeUpdate context) {

        Val v = expr.getValue().accept(this,context);
        return new Primary(expr.getLine(),v);
    }

    @Override
    public Type visit(Boolean type, NodeUpdate context) {
        return type;
    }

    @Override
    public Type visit(Money type, NodeUpdate context) {
        return type;
    }

    @Override
    public Val visit(Bool val, NodeUpdate context) {
        if(val.equals(context.getOldNode()))
            val = (Bool) context.getNewNode();
        return val;
    }

    @Override
    public Val visit(Int val, NodeUpdate context) {
        if(val.equals(context.getOldNode()))
            val = (Int) context.getNewNode();
        return val;
    }

    @Override
    public Val visit(Var val, NodeUpdate context) {
        return val;
    }
}
