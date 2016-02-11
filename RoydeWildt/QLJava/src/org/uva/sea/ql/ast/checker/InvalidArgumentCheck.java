package org.uva.sea.ql.ast.checker;

import org.uva.sea.ql.ast.tree.Node;
import org.uva.sea.ql.ast.tree.expr.Expr;
import org.uva.sea.ql.ast.tree.expr.binary.*;
import org.uva.sea.ql.ast.tree.expr.unary.Neg;
import org.uva.sea.ql.ast.tree.expr.unary.Not;
import org.uva.sea.ql.ast.tree.expr.unary.Pos;
import org.uva.sea.ql.ast.tree.form.Form;
import org.uva.sea.ql.ast.tree.stat.AssQuestion;
import org.uva.sea.ql.ast.tree.val.Bool;
import org.uva.sea.ql.ast.tree.val.Int;
import org.uva.sea.ql.ast.tree.var.Var;
import org.uva.sea.ql.ast.visitor.BaseVisitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by roydewildt on 11/02/16.
 */
public class InvalidArgumentCheck extends BaseVisitor{
    Map<Var, Expr> vars;
    List<Node> invalidArgs;

    public InvalidArgumentCheck(Form f){
        this.vars = new HashMap<>();
        this.invalidArgs = new ArrayList<>();
        f.accept(this);
    }

    @Override
    public <T> T visit(AssQuestion stat) {
        vars.put(stat.getVarname(), stat.getExpr());
        return super.visit(stat);
    }

    @Override
    public <T> T visit(Add expr) {

        super.visit(expr);
        return null;
    }

    @Override
    public <T> T visit(And expr) {
        return super.visit(expr);
    }

    @Override
    public <T> T visit(Div expr) {
        return super.visit(expr);
    }

    @Override
    public <T> T visit(Eq expr) {
        return super.visit(expr);
    }

    @Override
    public <T> T visit(GEq expr) {
        return super.visit(expr);
    }

    @Override
    public <T> T visit(GT expr) {
        return super.visit(expr);
    }

    @Override
    public <T> T visit(LEq expr) {
        return super.visit(expr);
    }

    @Override
    public <T> T visit(LT expr) {
        return super.visit(expr);
    }

    @Override
    public <T> T visit(Mul expr) {
        return super.visit(expr);
    }

    @Override
    public <T> T visit(NEq expr) {
        return super.visit(expr);
    }

    @Override
    public <T> T visit(Or expr) {
        return super.visit(expr);
    }

    @Override
    public <T> T visit(Sub expr) {
        return super.visit(expr);
    }

    @Override
    public <T> T visit(Neg expr) {
        return super.visit(expr);
    }

    @Override
    public <T> T visit(Not expr) {
        return super.visit(expr);
    }

    @Override
    public <T> T visit(Pos expr) {
        return super.visit(expr);
    }

    @Override
    public <T> T visit(Bool val) {
        return (T) val.getValue();
    }

    @Override
    public <T> T visit(Int val) {
        return (T) val.getValue();
    }

    @Override
    public <T> T visit(Var var) {
        if(vars.containsKey(var))
            return (T) vars.get(var);
        else return null;
    }
}
