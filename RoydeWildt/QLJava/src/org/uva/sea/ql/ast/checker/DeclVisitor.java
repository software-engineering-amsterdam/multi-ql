package org.uva.sea.ql.ast.checker;

import org.uva.sea.ql.ast.expr.Expr;
import org.uva.sea.ql.ast.form.Form;
import org.uva.sea.ql.ast.stat.Stat;
import org.uva.sea.ql.ast.val.Val;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by roy on 5-2-16.
 */
public class DeclVisitor implements Visitor{
    @Override
    public List<String> visit(Form form) {

        List<String> result = new ArrayList<>();
        Visitor v = new DeclVisitor();

        for (Stat s: form.getStms()) {
            result.addAll(s.accept(v));
        }

        return result;
    }

    @Override
    public List<String> visit(Stat stat) {

        List<String> result = new ArrayList<>();
        Visitor v = new DeclVisitor();

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

        if(stat.getVarname() != null){
            result.add(stat.getVarname());
        }
        return result;
    }

    @Override
    public List<String> visit(Expr expr) {
        return null;
    }

    @Override
    public List<String> visit(Val val) {
        return null;
    }
}
