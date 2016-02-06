package org.uva.sea.ql.ast.checker;

import org.uva.sea.ql.ast.expr.Expr;
import org.uva.sea.ql.ast.form.Form;
import org.uva.sea.ql.ast.stat.Stat;
import org.uva.sea.ql.ast.val.Val;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by roy on 6-2-16.
 */
public class BaseVisitor implements Visitor {

    Visitor v;

    @Override
    public List<String> visit(Form form) {
        List<String> result = new ArrayList<>();

        if (form.getStms() != null){
            for (Stat s: form.getStms()) {
                result.addAll(s.accept(v));
            }
        }

        return result;
    }

    @Override
    public List<String> visit(Stat stat) {
        List<String> result = new ArrayList<>();

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
    public List<String> visit(Expr expr) {
        List<String> result = new ArrayList<>();

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
    public List<String> visit(Val val) {
        List<String> result = new ArrayList<>();

         result.add(val.getValue());

        return result;
    }
}
