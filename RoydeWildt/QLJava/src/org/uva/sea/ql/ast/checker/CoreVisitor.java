package org.uva.sea.ql.ast.checker;

import org.uva.sea.ql.ast.Node;
import org.uva.sea.ql.ast.expr.Expr;
import org.uva.sea.ql.ast.expr.binary.*;
import org.uva.sea.ql.ast.expr.unary.*;
import org.uva.sea.ql.ast.form.Form;
import org.uva.sea.ql.ast.stat.*;
import org.uva.sea.ql.ast.val.*;
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
    public <T> T visit(Form form) {

        if (form.getStms() != null){
            for (Stat s: form.getStms()) {
                s.accept(v);
            }
        }

        return null;
    }

    @Override
    public <T> T visit(If stat) {

        //get all vars in loop bodies
            Map<Expr, List<Stat>> stmsList = stat.getStmsList();
            for (Map.Entry<Expr, List<Stat>> stms : stmsList.entrySet()) {
                stms.getKey().accept(v);
                for(Stat s : stms.getValue()){
                    s.accept(v);
                }
            }

        return null;
    }

    @Override
    public <T> T visit(IfElse stat) {

        //get all vars in loop bodies
        Map<Expr, List<Stat>> stmsList = stat.getStmsList();
        for (Map.Entry<Expr, List<Stat>> stms : stmsList.entrySet()) {
            stms.getKey().accept(v);
            for(Stat s : stms.getValue()){
                s.accept(v);
            }
        }

        return null;
    }

    @Override
    public <T> T visit(Question stat) {
        List<Node> result = new ArrayList<>();
        result.add(stat);

        return null;
    }

    @Override
    public <T> T visit(Add expr) {

        expr.getLhs().accept(v);
        expr.getRhs().accept(v);

        return null;
    }

    @Override
    public <T> T visit(And expr) {

        expr.getLhs().accept(v);
        expr.getRhs().accept(v);

        return null;
    }

    @Override
    public <T> T visit(Div expr) {

        expr.getLhs().accept(v);
        expr.getRhs().accept(v);

        return null;
    }

    @Override
    public <T> T visit(Eq expr) {

        expr.getLhs().accept(v);
        expr.getRhs().accept(v);

        return null;
    }

    @Override
    public <T> T visit(GEq expr) {

        expr.getLhs().accept(v);
        expr.getRhs().accept(v);

        return null;
    }

    @Override
    public <T> T visit(GT expr) {

        expr.getLhs().accept(v);
        expr.getRhs().accept(v);

        return null;
    }

    @Override
    public <T> T visit(LEq expr) {

        expr.getLhs().accept(v);
        expr.getRhs().accept(v);

        return null;
    }

    @Override
    public <T> T visit(LT expr) {

        expr.getLhs().accept(v);
        expr.getRhs().accept(v);

        return null;
    }

    @Override
    public <T> T visit(Mul expr) {

        expr.getLhs().accept(v);
        expr.getRhs().accept(v);

        return null;
    }

    @Override
    public <T> T visit(NEq expr) {

        expr.getLhs().accept(v);
        expr.getRhs().accept(v);

        return null;
    }

    @Override
    public <T> T visit(Or expr) {

        expr.getLhs().accept(v);
        expr.getRhs().accept(v);

        return null;
    }

    @Override
    public <T> T visit(Sub expr) {

        expr.getLhs().accept(v);
        expr.getRhs().accept(v);

        return null;
    }

    @Override
    public <T> T visit(Neg expr) {

        expr.getVal().accept(v);

        return null;
    }

    @Override
    public <T> T visit(Not expr) {

        expr.getVal().accept(v);

        return null;
    }

    @Override
    public <T> T visit(Pos expr) {

        expr.getVal().accept(v);

        return null;
    }

    @Override
    public <T> T visit(Bool val) {
        return null;
    }

    @Override
    public <T> T visit(Int val) {
        return null;
    }

    @Override
    public <T> T visit(Var var) {
        return null;
    }
}
