package org.uva.sea.ql.ast.visitor;

import org.uva.sea.ql.ast.tree.atom.val.Bool;
import org.uva.sea.ql.ast.tree.atom.val.Double;
import org.uva.sea.ql.ast.tree.atom.val.Int;
import org.uva.sea.ql.ast.tree.atom.val.Str;
import org.uva.sea.ql.ast.tree.atom.var.Var;
import org.uva.sea.ql.ast.tree.expr.binary.*;
import org.uva.sea.ql.ast.tree.expr.unary.Neg;
import org.uva.sea.ql.ast.tree.expr.unary.Not;
import org.uva.sea.ql.ast.tree.expr.unary.Pos;
import org.uva.sea.ql.ast.tree.expr.unary.Primary;
import org.uva.sea.ql.ast.tree.form.Form;
import org.uva.sea.ql.ast.tree.stat.*;
import org.uva.sea.ql.ast.tree.stat.block.If;
import org.uva.sea.ql.ast.tree.stat.block.IfElse;
import org.uva.sea.ql.ast.tree.stat.decl.Computed;
import org.uva.sea.ql.ast.tree.stat.decl.Question;
import org.uva.sea.ql.ast.tree.type.Boolean;
import org.uva.sea.ql.ast.tree.type.Money;
import org.uva.sea.ql.ast.tree.type.Number;
import org.uva.sea.ql.ast.tree.type.Text;
import org.uva.sea.ql.ast.visitor.interfaces.*;
import org.uva.sea.ql.ast.visitor.interfaces.TypeVisitor;

/**
 * Created by roydewildt on 10/02/16.
 */
public abstract class BaseVisitor<FORM,STAT,EXPR,TYPE,ATOM,CONTEXT> implements
        FormVisitor<FORM,CONTEXT>, StatVisitor<STAT,CONTEXT>, ExprVisitor<EXPR,CONTEXT>,
        TypeVisitor<TYPE,CONTEXT>, AtomVisitor<ATOM,CONTEXT>
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

        return null;
    }

    @Override
    public STAT visit(Computed stat, CONTEXT c) {

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
    public TYPE visit(Number type, CONTEXT context) {
        return null;
    }

    @Override
    public TYPE visit(Text type, CONTEXT context) {
        return null;
    }

    @Override
    public ATOM visit(Bool val, CONTEXT c) {
        return null;
    }

    @Override
    public ATOM visit(Int val, CONTEXT c) {
        return null;
    }

    @Override
    public ATOM visit(Double atom, CONTEXT context) {
        return null;
    }

    @Override
    public ATOM visit(Str val, CONTEXT c) {
        return null;
    }

    @Override
    public ATOM visit(Var val, CONTEXT c) {
        return null;
    }

}
