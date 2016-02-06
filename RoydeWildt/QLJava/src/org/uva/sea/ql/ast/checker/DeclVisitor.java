package org.uva.sea.ql.ast.checker;

import org.uva.sea.ql.ast.expr.Expr;
import org.uva.sea.ql.ast.form.Form;
import org.uva.sea.ql.ast.stat.Stat;
import org.uva.sea.ql.ast.val.Val;

import java.util.List;

/**
 * Created by roy on 5-2-16.
 */
public class DeclVisitor extends BaseVisitor{

    @Override
    public List<String> visit(Form form) {
        v = new DeclVisitor();
        return super.visit(form);
    }

    @Override
    public List<String> visit(Stat stat) {
        v = new DeclVisitor();
        List<String> result = super.visit(stat);


        if(stat.getVarname() != null){
            result.add(stat.getVarname());
        }
        return result;
    }

    @Override
    public List<String> visit(Expr expr) {
        v = new DeclVisitor();
        return super.visit(expr);
    }

    @Override
    public List<String> visit(Val val) {
        v = new DeclVisitor();
        return super.visit(val);
    }
}
