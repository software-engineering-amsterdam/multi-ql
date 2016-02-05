package org.uva.sea.ql.ast.checker;

import org.uva.sea.ql.ast.form.Form;
import org.uva.sea.ql.ast.stat.Stat;
import org.uva.sea.ql.ast.expr.*;
import org.uva.sea.ql.ast.val.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by roy on 5-2-16.
 */
public class VarsVisitor implements Visitor{

    @Override
    public List<String> visit(Form form) {
        Visitor v = new VarsVisitor();
        List<String> result = new ArrayList<>();

        for (Stat s: form.getStms()) {
            result.addAll(s.accept(v));
        }

        return result;
    }

    @Override
    public List<String> visit(Stat stat) {

        List<String> result = new ArrayList<>();
        Visitor v = new VarsVisitor();

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
        Visitor v = new VarsVisitor();

        //get all vars in expressions
        Expr e = expr.getLhs();
        if (e != null)
            result.addAll(e.accept(v));

        //get all vars in expressions
        e = expr.getRhs();
        if (e != null)
            result.addAll(e.accept(v));

        //get var from primary
        if (expr instanceof Prim) {
            Val val = ((Prim) expr).getV();
            result.addAll((val.accept(v)));
        }

        return result;
    }

    @Override
    public List<String> visit(Val val) {

        List<String> result = new ArrayList<>();

        if (val instanceof Var)
            result.add(val.getValue());

        return result;
    }
}
