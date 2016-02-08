package org.uva.sea.ql.ast.checker;

import org.uva.sea.ql.ast.Node;
import org.uva.sea.ql.ast.expr.Expr;
import org.uva.sea.ql.ast.form.Form;
import org.uva.sea.ql.ast.stat.Stat;
import org.uva.sea.ql.ast.val.Val;
import org.uva.sea.ql.ast.var.Var;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by roy on 6-2-16.
 */
public class BaseVisitor implements Visitor {

    Visitor v;

    public BaseVisitor(){
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
    public List<? extends Node> visit(Stat stat) {
        List<Node> result = new ArrayList<>();

        //get all vars in expressions
        if (stat.getCond() != null){
            Expr e = stat.getCond();
            result.addAll(e.accept(v));

        }

        //get all vars in loop body
        if (stat.getStms() != null) {
            for (Stat s: stat.getStms()) {
                result.addAll(s.accept(v));
            }
        }

        //get all vars in alternative loop body
        if (stat.getAltStms() != null) {
            for (Stat s: stat.getAltStms()) {
                result.addAll(s.accept(v));
            }
        }

        return result;
    }

    @Override
    public List<? extends Node> visit(Expr expr) {
        List<Node> result = new ArrayList<>();

        //get all vars in expressions
        Expr e = expr.getLhs();
        if (e != null)
            result.addAll(e.accept(v));

        //get all vars in expressions
        e = expr.getRhs();
        if (e != null)
            result.addAll(e.accept(v));

        return result;
    }

    @Override
    public List<? extends Node> visit(Val val) {
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
