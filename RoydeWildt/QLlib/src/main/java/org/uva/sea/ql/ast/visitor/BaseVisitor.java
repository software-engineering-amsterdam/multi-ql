package org.uva.sea.ql.ast.visitor;

import org.uva.sea.ql.ast.tree.expr.binary.*;
import org.uva.sea.ql.ast.tree.expr.unary.*;
import org.uva.sea.ql.ast.tree.form.Form;
import org.uva.sea.ql.ast.tree.stat.*;
import org.uva.sea.ql.ast.tree.type.Boolean;
import org.uva.sea.ql.ast.tree.type.Money;
import org.uva.sea.ql.ast.tree.val.*;
import org.uva.sea.ql.ast.tree.var.Var;
import org.uva.sea.ql.ast.visitor.interfaces.ExprVisitor;
import org.uva.sea.ql.ast.visitor.interfaces.FormVisitor;
import org.uva.sea.ql.ast.visitor.interfaces.StatVisitor;
import org.uva.sea.ql.ast.visitor.interfaces.TypeVisitor;
import org.uva.sea.ql.ast.visitor.interfaces.ValVisitor;
import org.uva.sea.ql.ast.visitor.interfaces.VarVisitor;

/**
 * Created by roydewildt on 10/02/16.
 */
public abstract class BaseVisitor<FORM,STAT,EXPR,TYPE,VAL,VAR,CONTEXT> implements
        FormVisitor<FORM,CONTEXT>, StatVisitor<STAT,CONTEXT>, ExprVisitor<EXPR,CONTEXT>,
        TypeVisitor<TYPE,CONTEXT>, ValVisitor<VAL,CONTEXT>, VarVisitor<VAR,CONTEXT>
{

    @Override
    public FORM visit(Form form, CONTEXT c) {

        if (form.getStms() != null){
            for (Stat s: form.getStms()) {
                s.accept(this,c);
            }
        }

        return null;
    }

    @Override
    public STAT visit(If stat, CONTEXT c) {

        stat.getCond().accept(this,c);
        for(Stat s : stat.getStms())
            s.accept(this,c);

        return null;
    }

    @Override
    public STAT visit(IfElse stat, CONTEXT c) {

        stat.getCond().accept(this,c);
        for(Stat s : stat.getIfStms())
            s.accept(this,c);
        for(Stat s : stat.getElseStms())
            s.accept(this,c);

        return null;
    }

    @Override
    public STAT visit(Question stat, CONTEXT c) {

        stat.getType().accept(this,c);
        stat.getVarname().accept(this,c);
        stat.getExpr().accept(this,c);

        return null;
    }

    @Override
    public EXPR visit(Add expr, CONTEXT c) {

        expr.getLhs().accept(this,c);
        expr.getRhs().accept(this,c);

        return null;
    }

    @Override
    public EXPR visit(And expr, CONTEXT c) {

        expr.getLhs().accept(this,c);
        expr.getRhs().accept(this,c);

        return null;
    }

    @Override
    public EXPR visit(Div expr, CONTEXT c) {

        expr.getLhs().accept(this,c);
        expr.getRhs().accept(this,c);

        return null;
    }

    @Override
    public EXPR visit(Eq expr, CONTEXT c) {

        expr.getLhs().accept(this,c);
        expr.getRhs().accept(this,c);

        return null;
    }

    @Override
    public EXPR visit(GEq expr, CONTEXT c) {

        expr.getLhs().accept(this,c);
        expr.getRhs().accept(this,c);

        return null;
    }

    @Override
    public EXPR visit(GT expr, CONTEXT c) {

        expr.getLhs().accept(this,c);
        expr.getRhs().accept(this,c);

        return null;
    }

    @Override
    public EXPR visit(LEq expr, CONTEXT c) {

        expr.getLhs().accept(this,c);
        expr.getRhs().accept(this,c);

        return null;
    }

    @Override
    public EXPR visit(LT expr, CONTEXT c) {

        expr.getLhs().accept(this,c);
        expr.getRhs().accept(this,c);

        return null;
    }

    @Override
    public EXPR visit(Mul expr, CONTEXT c) {

        expr.getLhs().accept(this,c);
        expr.getRhs().accept(this,c);

        return null;
    }

    @Override
    public EXPR visit(NEq expr, CONTEXT c) {

        expr.getLhs().accept(this,c);
        expr.getRhs().accept(this,c);

        return null;
    }

    @Override
    public EXPR visit(Or expr, CONTEXT c) {

        expr.getLhs().accept(this,c);
        expr.getRhs().accept(this,c);

        return null;
    }

    @Override
    public EXPR visit(Sub expr, CONTEXT c) {

        expr.getLhs().accept(this,c);
        expr.getRhs().accept(this,c);

        return null;
    }

    @Override
    public EXPR visit(Neg expr, CONTEXT c) {

        expr.getValue().accept(this,c);

        return null;
    }

    @Override
    public EXPR visit(Not expr, CONTEXT c) {

        expr.getValue().accept(this,c);

        return null;
    }

    @Override
    public EXPR visit(Pos expr, CONTEXT c) {

        expr.getValue().accept(this,c);

        return null;
    }

    @Override
    public EXPR visit(Primary expr, CONTEXT c) {

        expr.getValue().accept(this,c);

        return null;
    }

    @Override
    public TYPE visit(Boolean type, CONTEXT c) {
        return null;
    }

    @Override
    public TYPE visit(Money type, CONTEXT c) {
        return null;
    }

    @Override
    public VAL visit(Bool val, CONTEXT c) {
        return null;
    }

    @Override
    public VAL visit(Int val, CONTEXT c) {
        return null;
    }

    @Override
    public VAL visit(Str val, CONTEXT c) {
        return null;
    }

    @Override
    public VAR visit(Var val, CONTEXT c) {
        return null;
    }

}
