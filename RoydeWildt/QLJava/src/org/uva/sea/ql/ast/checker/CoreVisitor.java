package org.uva.sea.ql.ast.checker;

import org.uva.sea.ql.ast.Node;
import org.uva.sea.ql.ast.expr.Expr;
import org.uva.sea.ql.ast.expr.binary.*;
import org.uva.sea.ql.ast.expr.unary.Neg;
import org.uva.sea.ql.ast.expr.unary.Not;
import org.uva.sea.ql.ast.expr.unary.Pos;
import org.uva.sea.ql.ast.form.Form;
import org.uva.sea.ql.ast.stat.*;
import org.uva.sea.ql.ast.val.Bool;
import org.uva.sea.ql.ast.val.Int;
import org.uva.sea.ql.ast.var.Var;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by roydewildt on 10/02/16.
 */
public class CoreVisitor implements Visitor {

    Visitor v;

    public CoreVisitor(){
        v =this;
    }

    @Override
    public List<? extends Node> visit(Form form) {
        List<Node> result = new ArrayList<>();

        if (form.getStms() != null){
            for (Stat s: form.getStms()) {
                result.addAll(s.accept(v));
            }
        }

        return result;
    }

    @Override
    public List<? extends Node> visit(If stat) {
        List<Node> result = new ArrayList<>();


        //get all vars in loop bodies
            Map<Expr, List<Stat>> stmsList = stat.getStmsList();
            for (Map.Entry<Expr, List<Stat>> stms : stmsList.entrySet()) {
                result.addAll(stms.getKey().accept(v));
                for(Stat s : stms.getValue()){
                    result.addAll(s.accept(v));
                }
            }

        return result;
    }

    @Override
    public List<? extends Node> visit(IfElse stat) {
        List<Node> result = new ArrayList<>();

        //get all vars in loop bodies
        Map<Expr, List<Stat>> stmsList = stat.getStmsList();
        for (Map.Entry<Expr, List<Stat>> stms : stmsList.entrySet()) {
            result.addAll(stms.getKey().accept(v));
            for(Stat s : stms.getValue()){
                result.addAll(s.accept(v));
            }
        }

        return result;
    }

    @Override
    public List<? extends Node> visit(Question stat) {
        List<Node> result = new ArrayList<>();
        result.add(stat);

        return result;
    }

    @Override
    public List<? extends Node> visit(Add expr) {
        List<Node> result = new ArrayList<>();

        result.addAll(expr.getLhs().accept(v));
        result.addAll(expr.getRhs().accept(v));

        return result;
    }

    @Override
    public List<? extends Node> visit(And expr) {

        List<Node> result = new ArrayList<>();

        result.addAll(expr.getLhs().accept(v));
        result.addAll(expr.getRhs().accept(v));

        return result;
    }

    @Override
    public List<? extends Node> visit(Div expr) {

        List<Node> result = new ArrayList<>();

        result.addAll(expr.getLhs().accept(v));
        result.addAll(expr.getRhs().accept(v));

        return result;
    }

    @Override
    public List<? extends Node> visit(Eq expr) {

        List<Node> result = new ArrayList<>();

        result.addAll(expr.getLhs().accept(v));
        result.addAll(expr.getRhs().accept(v));

        return result;
    }

    @Override
    public List<? extends Node> visit(GEq expr) {

        List<Node> result = new ArrayList<>();

        result.addAll(expr.getLhs().accept(v));
        result.addAll(expr.getRhs().accept(v));

        return result;
    }

    @Override
    public List<? extends Node> visit(GT expr) {

        List<Node> result = new ArrayList<>();

        result.addAll(expr.getLhs().accept(v));
        result.addAll(expr.getRhs().accept(v));

        return result;
    }

    @Override
    public List<? extends Node> visit(LEq expr) {

        List<Node> result = new ArrayList<>();

        result.addAll(expr.getLhs().accept(v));
        result.addAll(expr.getRhs().accept(v));

        return result;
    }

    @Override
    public List<? extends Node> visit(LT expr) {

        List<Node> result = new ArrayList<>();

        result.addAll(expr.getLhs().accept(v));
        result.addAll(expr.getRhs().accept(v));

        return result;
    }

    @Override
    public List<? extends Node> visit(Mul expr) {

        List<Node> result = new ArrayList<>();

        result.addAll(expr.getLhs().accept(v));
        result.addAll(expr.getRhs().accept(v));

        return result;
    }

    @Override
    public List<? extends Node> visit(NEq expr) {

        List<Node> result = new ArrayList<>();

        result.addAll(expr.getLhs().accept(v));
        result.addAll(expr.getRhs().accept(v));

        return result;
    }

    @Override
    public List<? extends Node> visit(Or expr) {

        List<Node> result = new ArrayList<>();

        result.addAll(expr.getLhs().accept(v));
        result.addAll(expr.getRhs().accept(v));

        return result;
    }

    @Override
    public List<? extends Node> visit(Sub expr) {

        List<Node> result = new ArrayList<>();

        result.addAll(expr.getLhs().accept(v));
        result.addAll(expr.getRhs().accept(v));

        return result;
    }

    @Override
    public List<? extends Node> visit(Neg expr) {

        List<Node> result = new ArrayList<>();

        result.addAll(expr.getVal().accept(v));

        return result;
    }

    @Override
    public List<? extends Node> visit(Not expr) {

        List<Node> result = new ArrayList<>();

        result.addAll(expr.getVal().accept(v));

        return result;
    }

    @Override
    public List<? extends Node> visit(Pos expr) {

        List<Node> result = new ArrayList<>();

        result.addAll(expr.getVal().accept(v));

        return result;
    }

    @Override
    public List<? extends Node> visit(Bool val) {
        List<Node> result = new ArrayList<>();

        result.add(val);

        return result;
    }

    @Override
    public List<? extends Node> visit(Int val) {
        List<Node> result = new ArrayList<>();

        result.add(val);

        return result;
    }

    @Override
    public List<? extends Node> visit(Var var) {
        List<Node> result = new ArrayList<>();

        result.add(var);

        return result;
    }
}
