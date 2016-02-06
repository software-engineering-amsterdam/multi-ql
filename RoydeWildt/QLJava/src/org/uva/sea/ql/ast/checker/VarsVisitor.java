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
public class VarsVisitor extends BaseVisitor{

    @Override
    public List<String> visit(Form form) {
        v = new VarsVisitor();
        return super.visit(form);
    }

    @Override
    public List<String> visit(Stat stat) {
        v = new VarsVisitor();
        return super.visit(stat);
    }

    @Override
    public List<String> visit(Expr expr) {
        v = new VarsVisitor();
        List<String> result = super.visit(expr);

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

        v = new VarsVisitor();

        if (val instanceof Var)
            result.add(val.getValue());

        return result;
    }
}
